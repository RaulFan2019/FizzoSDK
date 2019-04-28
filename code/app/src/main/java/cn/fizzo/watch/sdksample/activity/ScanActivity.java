package cn.fizzo.watch.sdksample.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.entity.BleScanAE;
import io.reactivex.functions.Consumer;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/9/20 9:49
 */
public class ScanActivity extends BaseActivity {


    private static final String TAG = "ScanActivity";

    private static final int REQUEST_ENABLE_BT = 0x01;


    private static final int MSG_REFRESH_LIST = 0x01;//刷新列表
    private static final int MSG_AUTO_STOP_SCAN = 0x02;//停止扫描


    private static final int INTERVAL_REFRESH_LIST = 1000;//刷新列表间隔
    private static final int INTERVAL_AUTO_STOP_SCAN = 10 * 1000;//自动停止扫描


    /* local view */
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.lv_scan)
    ListView lvScan;


    /* local data */
    private ArrayAdapter mAdapter;
    private List<String> listScanInfo = new ArrayList<>();
    private List<BleScanAE> listScan = new ArrayList<>(); //扫描蓝牙列表

    private BluetoothAdapter mBluetoothAdapter;//蓝牙适配器
    private boolean mScanning = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.btn_scan,R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_scan:
                scanLeDevice(true);
                break;
            case R.id.btn_update:
                startActivity(UpdateTestSelectActivity.class);
                break;
        }

    }


    @Override
    protected void myHandleMsg(Message msg) {
        switch (msg.what){
            case MSG_AUTO_STOP_SCAN:
                scanLeDevice(false);
                break;
            case MSG_REFRESH_LIST:
                listScanInfo.clear();
                for (BleScanAE le : listScan) {
                    listScanInfo.add(le.device.getName() + "\n" + le.device.getAddress() + ",rssi:" + le.rssi);
                }
                mAdapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_LIST, INTERVAL_REFRESH_LIST);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mAdapter = new ArrayAdapter<String>(ScanActivity.this, android.R.layout.simple_expandable_list_item_1, listScanInfo);
        lvScan.setAdapter(mAdapter);


        lvScan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                scanLeDevice(false);
                Bundle bundle = new Bundle();
                BleScanAE bleScanAE = listScan.get(i);
                bundle.putString("address", bleScanAE.device.getAddress());
                bundle.putString("name", bleScanAE.device.getName());
                startActivity(ConnectActivity.class, bundle);
                listScanInfo.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void doMyCreate() {
        //申请权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION

                ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkBLEFeature();
    }

    @Override
    protected void causeGC() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "requestCode:" + requestCode + ",resultCode:" + resultCode);

    }

    /**
     * 检查BLE是否起作用
     */
    private void checkBLEFeature() {
        //判断是否支持蓝牙4.0
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "本设备不支持蓝牙4.0", Toast.LENGTH_SHORT).show();
            finish();
        }
        //获取蓝牙适配器
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        //判断是否支持蓝牙
        if (mBluetoothAdapter == null) {
            //不支持
            Toast.makeText(this, "本设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            finish();
            return;
        } else {
            //打开蓝牙
            if (!mBluetoothAdapter.isEnabled()) {//判断是否已经打开
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }


    //扫描BLE设备
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            if (mBluetoothAdapter.isEnabled()) {
                if (mScanning){
                    return;
                }
                mScanning = true;
                listScan.clear();
                btnScan.setVisibility(View.GONE);
                //10秒后停止扫描
                mHandler.sendEmptyMessageDelayed(MSG_AUTO_STOP_SCAN,INTERVAL_AUTO_STOP_SCAN);
                //1秒后刷新列表
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_LIST, INTERVAL_REFRESH_LIST);
                mBluetoothAdapter.startLeScan(mLeScanCallback);
            } else {
                Toast.makeText(ScanActivity.this,"扫描未打开",Toast.LENGTH_SHORT).show();
            }
        } else {
            btnScan.setVisibility(View.VISIBLE);
            mHandler.removeMessages(MSG_REFRESH_LIST);
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            mScanning = false;
        }
    }


    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        /**
         * 简单说一下这三个参数的含义：
         * @param device：识别的远程设备
         * @param rssi：  RSSI的值作为对远程蓝牙设备的报告; 0代表没有蓝牙设备;
         * @param scanRecord：远程设备提供的配对号(公告)
         */
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            Log.v(TAG,"onLeScan:" + "rssi:" + rssi);
            for (BleScanAE le : listScan) {
                if ((le.device.getAddress().equals(device.getAddress()))) {
                    le.rssi = rssi;
                    return;
                }
            }
            BleScanAE leBleScan = new BleScanAE(device, rssi);
            listScan.add(leBleScan);
        }
    };

}
