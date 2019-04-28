package cn.fizzo.watch.sdksample.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fizzo.watch.Fw;
import cn.fizzo.watch.array.ConnectStates;
import cn.fizzo.watch.entity.HrEntity;
import cn.fizzo.watch.observer.ConnectListener;
import cn.fizzo.watch.observer.NotifyActiveListener;
import cn.fizzo.watch.observer.NotifyHrListener;
import cn.fizzo.watch.observer.SetSportTypeListener;
import cn.fizzo.watch.sdksample.R;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/9/20 10:49
 */
public class ConnectActivity extends BaseActivity implements ConnectListener, NotifyHrListener,
        NotifyActiveListener ,SetSportTypeListener{

    /* local view */
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;//设备名称
    @BindView(R.id.tv_device_address)
    TextView tvDeviceAddress;//设备mac地址
    @BindView(R.id.tv_connect_state)
    TextView tvConnectState;//设备连接状态文本
    @BindView(R.id.tv_check_manufacturer)
    TextView tvCheckManufacturer;//设备制造商
    @BindView(R.id.tv_check_private_service)
    TextView tvCheckPrivateService;//检查私有服务
    @BindView(R.id.tv_check_write_utc)
    TextView tvCheckWriteUtc;//写入UTC
    @BindView(R.id.tv_check_notify_hr)
    TextView tvCheckNotifyHr;//监听心率状态
    @BindView(R.id.tv_check_firmware)
    TextView tvCheckFirmware;//版本号文本


    @BindView(R.id.tv_data_hr)
    TextView tvDataHr;//心率数据
    @BindView(R.id.tv_data_cadence)
    TextView tvDataCadence;//步频数据
    @BindView(R.id.tv_data_step_light)
    TextView tvDataStepLight;//步数（灯光开时）
    @BindView(R.id.ll_data)
    LinearLayout llData;//数据布局
    @BindView(R.id.ll_function)
    LinearLayout llFunction;
    @BindView(R.id.et_sport_type)
    EditText etSportType;

    /* local data */
    private String mDeviceName;//设备名称
    private String mDeviceAddress;//设备地址


    @Override
    protected int getLayoutId() {
        return R.layout.activity_connect;
    }

    @OnClick({R.id.btn_light_off, R.id.btn_light_on, R.id.btn_vibrate, R.id.btn_disconnect,
            R.id.btn_clear, R.id.btn_read_history_step, R.id.btn_sync_hr_data, R.id.btn_clear_watch
            ,R.id.btn_set_sport_type})
    public void onClick(View view) {
        switch (view.getId()) {
            //关灯管
            case R.id.btn_light_off:
                Fw.getManager().controlLight(false);
                break;
            //开灯管
            case R.id.btn_light_on:
                Fw.getManager().controlLight(true);
                break;
            //振动
            case R.id.btn_vibrate:
                Fw.getManager().vibrate();
                break;
            //断开连接并退出
            case R.id.btn_disconnect:
                Fw.getManager().disConnectDevice();
                finish();
                break;
            //清除蓝牙缓存
            case R.id.btn_clear:
                Fw.getManager().getConnectDevice().refreshDeviceCache();
                break;
            case R.id.btn_read_history_step:
                startActivity(ReadHistoryStepActivity.class);
                break;
            //同步心率数据
            case R.id.btn_sync_hr_data:
                startActivity(SyncHrDataActivity.class);
                break;
            //手环清除数据
            case R.id.btn_clear_watch:
                Fw.getManager().clearFlash();
                break;
            case R.id.btn_set_sport_type:
                int type = Integer.parseInt(etSportType.getText().toString());
                Fw.getManager().writeSportType(type);
                break;
        }
    }

    @Override
    protected void myHandleMsg(Message msg) {

    }


    /**
     * Fizzo SDK 连接状态变化
     *
     * @param state
     */
    @Override
    public void connectStateChange(int state) {
        switch (state) {
            //连接中
            case ConnectStates.CONNECTING:
                tvConnectState.setText("连接中..");
                break;
            //市区连接
            case ConnectStates.DISCONNECT:
                tvConnectState.setText("失去连接");
                tvCheckManufacturer.setText("");
                tvCheckWriteUtc.setText("");
                tvCheckNotifyHr.setText("");
                tvCheckFirmware.setText("");
                tvCheckPrivateService.setText("");
                llData.setVisibility(View.GONE);
                llFunction.setVisibility(View.GONE);
                break;
            //连接失败
            case ConnectStates.CONNECT_FAIL:
                tvConnectState.setText("连接失败");
                break;
            //已连接
            case ConnectStates.CONNECTED:
                tvConnectState.setText("已连接");
                break;
            //验证制造商通过
            case ConnectStates.CHECK_MANUFACTURER_OK:
                tvCheckManufacturer.setText("通过");
                break;
            //验证制造商失败
            case ConnectStates.CHECK_MANUFACTURER_FAIL:
                tvCheckManufacturer.setText("错误");
                break;
            //写入UTC时间成功
            case ConnectStates.WRITE_UTC_OK:
                tvCheckWriteUtc.setText("成功");
                break;
            //监听心率变化成功
            case ConnectStates.NOTIFY_HR_OK:
                tvCheckNotifyHr.setText("成功");
                break;
            //获取手环版本信息成功
            case ConnectStates.READ_FIRMWARE_OK:
                tvCheckFirmware.setText(Fw.getManager().getConnectDevice().getFirmwareVersion());
                break;
            //监听私有服务是否成功
            case ConnectStates.NOTIFY_PRIVATE_SERVICE_OK:
                tvCheckPrivateService.setText("成功");
                llData.setVisibility(View.VISIBLE);
                llFunction.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 心率数据变化
     *
     * @param hrEntity
     */
    @Override
    public void notifyHr(HrEntity hrEntity) {
        tvDataHr.setText(hrEntity.getHr() + "");
        tvDataCadence.setText(hrEntity.getSportSize() + "");
        tvDataStepLight.setText(hrEntity.getStepCount() + "");
    }

    /**
     * 激活状态发生变化
     */
    @Override
    public void notifyActive() {
        Toast.makeText(ConnectActivity.this, "收到短按键回馈", Toast.LENGTH_SHORT).show();
    }

    /**
     * 清除Flash成功
     */
    @Override
    public void notifyClearFlash() {
        Toast.makeText(ConnectActivity.this, "清除Flash成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyReadyUpdate() {

    }

    /**
     * 设置运动模式成功
     * @param success
     */
    @Override
    public void setSportTypeSuccessful(boolean success) {
        if (success){
            Toast.makeText(ConnectActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ConnectActivity.this,"设置失败",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void initData() {
        mDeviceAddress = getIntent().getExtras().getString("address");
        mDeviceName = getIntent().getExtras().getString("name");
    }

    @Override
    protected void initViews() {
        tvDeviceName.setText(mDeviceName);
        tvDeviceAddress.setText(mDeviceAddress);
    }

    @Override
    protected void doMyCreate() {
        Fw.getManager().addNewConnect(mDeviceAddress);
        Fw.getManager().registerConnectListener(this);
        Fw.getManager().registerNotifyHrListener(this);
        Fw.getManager().registerNotifyActiveListener(this);
        Fw.getManager().registerNotifySetSportTypeListener(this);
    }

    @Override
    protected void causeGC() {
        Fw.getManager().unRegisterConnectListener(this);
        Fw.getManager().unRegisterNotifyHrListener(this);
        Fw.getManager().unRegisterNotifyActiveListener(this);
        Fw.getManager().unRegisterNotifySetSportTypeListener(this);
    }

}
