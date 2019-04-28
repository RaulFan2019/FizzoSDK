package cn.fizzo.watch.sdksample.activity;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fizzo.watch.Fw;
import cn.fizzo.watch.array.ConnectStates;
import cn.fizzo.watch.observer.ConnectListener;
import cn.fizzo.watch.observer.NotifyActiveListener;
import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.adapter.UpdateDeviceListAdapter;
import cn.fizzo.watch.sdksample.config.FileConfig;
import cn.fizzo.watch.sdksample.entity.DeviceUpdateEntity;
import cn.fizzo.watch.sdksample.entity.net.GetDeviceListRE;
import cn.fizzo.watch.sdksample.entity.net.VersionRE;
import cn.fizzo.watch.sdksample.service.DfuService;
import cn.fizzo.watch.sdksample.ssh.ConnectionStatusListener;
import cn.fizzo.watch.sdksample.ssh.SessionController;
import cn.fizzo.watch.sdksample.ssh.SessionUserInfo;
import cn.fizzo.watch.sdksample.utils.LogU;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceController;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 16:42
 */
public class UpdateTestTodoActivity extends BaseActivity implements ConnectListener, NotifyActiveListener {

    private static final String TAG = "UpdateTestTodoActivity";

    private static final int MSG_CONNECT_SSH_OK = 0x00;
    private static final int MSG_DOWNLOAD_VERSION_PROGRESS = 0x01;
    private static final int MSG_DOWNLOAD_VERSION_OK = 0x02;
    private static final int MSG_DOWNLOAD_VERSION_ERROR = 0x03;
    private static final int MSG_DFU_UPDATE_COMPLETE = 0x04;
    private static final int MSG_DFU_PROGRESS_NOT_UPDATE = 0x05;
    private static final int MSG_DFU_UPDATE_ERROR = 0x06;
    private static final int MSG_CONNECTING_OUT_OF_TIME = 0x07;
    private static final int MSG_UPDATE_READY_OUT_OF_TIME = 0x08;
    private static final int MSG_OPEN_BLUETOOTH = 0x09;//打开蓝牙
    private static final int MSG_CLEAR_FLASH_OUT_OF_TIME = 0x10;

    private static final long INTERVAL_PROGRESS_NOT_UPDATE = 5000;

    private static final long INTERVAL_CONNECTING_OUT_OF_TIME = 15 * 1000;
    private static final long INTERVAL_UPDATE_READY_OUT_OF_TIME = 60 * 1000;
    private static final long INTERVAL_CLEAR_FLASH_OUT_OF_TIME = 5 * 1000;

    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_version_status)
    TextView tvVersionStatus;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_device_status)
    TextView tvDeviceStatus;

    private VersionRE mSelectVersion;
    private List<GetDeviceListRE.HrdevicesBean> listDevice = new ArrayList<>();
    private List<DeviceUpdateEntity> listUpdate = new ArrayList<>();
    private UpdateDeviceListAdapter adapter;

    private int mWorkIndex = 0;
    private int mDoneSize = 0;
    private int mAllSize = 0;
    private boolean updateNow = false;

    SessionUserInfo mSUI;

    /* dfu */
    DfuServiceController controller;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_test_todo;
    }

    @Override
    protected void myHandleMsg(Message msg) {
        switch (msg.what) {
            //连接服务器成功
            case MSG_CONNECT_SSH_OK:
                tvVersionStatus.setText("连接服务器成功");
                downloadUpdateFile();
                break;
            //下载升级文件
            case MSG_DOWNLOAD_VERSION_PROGRESS:
                tvVersionStatus.setText("下载：" + msg.arg1 + "%");
                break;
            //下载升级文件成功
            case MSG_DOWNLOAD_VERSION_OK:
                tvVersionStatus.setText("成功");
                tvVersionStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                connectNextDevice();
                break;
            //下载文件错误
            case MSG_DOWNLOAD_VERSION_ERROR:
                tvVersionStatus.setText("错误");
                Toast.makeText(UpdateTestTodoActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            //DFU升级结束
            case MSG_DFU_UPDATE_COMPLETE:
                updateNow = false;
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_UPDATE_OVER;
                adapter.notifyDataSetChanged();
                connectNextDevice();
                tvDeviceStatus.setText("升级完成，重新连接验证版本");
                break;
            // dfu升级中断
            case MSG_DFU_PROGRESS_NOT_UPDATE:
                updateNow = false;
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_UPDATE_ERROR;
                adapter.notifyDataSetChanged();
                mWorkIndex ++;
                connectNextDevice();
                Toast.makeText(UpdateTestTodoActivity.this, "DFU升级无响应", Toast.LENGTH_SHORT).show();
                break;
            //dfu升级错误
            case MSG_DFU_UPDATE_ERROR:
                updateNow = false;
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_UPDATE_ERROR;
                adapter.notifyDataSetChanged();
                mWorkIndex++;
                connectNextDevice();
                Toast.makeText(UpdateTestTodoActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                break;
                //超时
            case MSG_CONNECTING_OUT_OF_TIME:
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CONNECT_OUT_OF_TIME;
                adapter.notifyDataSetChanged();
                Fw.getManager().disConnectDevice();
                mWorkIndex++;
                connectNextDevice();
                break;
                //准备升级超时
            case MSG_UPDATE_READY_OUT_OF_TIME:
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_UPDATE_READY_OUT_OF_TIME;
                adapter.notifyDataSetChanged();
                if (controller != null){
                    controller.pause();
                    controller.abort();
                }
                Fw.getManager().disConnectDevice();
                BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
//                blueAdapter.disable();
//                blueAdapter.enable();
                mWorkIndex++;
                connectNextDevice();
//                //蓝牙关闭
//                BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
//                blueAdapter.disable();
//                tvDeviceStatus.setText("关闭蓝牙");
//                mHandler.sendEmptyMessageDelayed(MSG_OPEN_BLUETOOTH,10 *1000);
                break;
                //打开蓝牙
            case MSG_OPEN_BLUETOOTH:
                tvDeviceStatus.setText("打开蓝牙");
                BluetoothAdapter blueOpenAdapter = BluetoothAdapter.getDefaultAdapter();
                blueOpenAdapter.enable();
                mWorkIndex++;
                connectNextDevice();
                break;
            case MSG_CLEAR_FLASH_OUT_OF_TIME:
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CLEAR_FLASH_OUT_OF_TIME;
                adapter.notifyDataSetChanged();
                Fw.getManager().disConnectDevice();
                mWorkIndex++;
                connectNextDevice();
                break;

        }
    }

    @Override
    public void connectStateChange(int state) {
        switch (state) {
            //连接中
            case ConnectStates.CONNECTING:
                tvDeviceStatus.setText("连接中..");
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CONNECTING;
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(MSG_CONNECTING_OUT_OF_TIME,INTERVAL_CONNECTING_OUT_OF_TIME);
                break;
            //失去连接
            case ConnectStates.DISCONNECT:
                if (!updateNow){
                    tvDeviceStatus.setText("失去连接");
                    listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CONNECT_FAIL;
                    adapter.notifyDataSetChanged();
                    Fw.getManager().disConnectDevice();
                    mWorkIndex++;
                    connectNextDevice();
                }
                break;
            //已连接
            case ConnectStates.CONNECTED:
                mHandler.removeMessages(MSG_CONNECTING_OUT_OF_TIME);
                tvDeviceStatus.setText("已连接");
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CONNECTED;
                adapter.notifyDataSetChanged();
                break;
            //验证制造商通过
            case ConnectStates.CHECK_MANUFACTURER_OK:
                tvDeviceStatus.setText("制造商验证通过");
                break;
            //验证制造商失败
            case ConnectStates.CHECK_MANUFACTURER_FAIL:
                tvDeviceStatus.setText("制造商验证失败");
                listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_MANUFACTURER_FAIL;
                adapter.notifyDataSetChanged();
                Fw.getManager().disConnectDevice();
                mWorkIndex++;
                connectNextDevice();
                break;
            //获取手环版本信息成功
            case ConnectStates.READ_FIRMWARE_OK:
                listUpdate.get(mWorkIndex).version = Fw.getManager().getConnectDevice().getFirmwareVersion();
                adapter.notifyDataSetChanged();
                tvDeviceStatus.setText("版本号：" + Fw.getManager().getConnectDevice().getFirmwareVersion());
                break;
            //监听私有服务是否成功
            case ConnectStates.NOTIFY_PRIVATE_SERVICE_OK:
                tvDeviceStatus.setText("连接成功");
                if (Fw.getManager().getConnectDevice().getFirmwareVersion().equals(mSelectVersion.name)) {
                    tvDeviceStatus.setText("清除FLASH...");
                    listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_CLEAR_FLASH;
                    adapter.notifyDataSetChanged();
                    Fw.getManager().clearFlash();
                    mHandler.sendEmptyMessageDelayed(MSG_CLEAR_FLASH_OUT_OF_TIME,INTERVAL_CLEAR_FLASH_OUT_OF_TIME);
                } else {
                    listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_UPDATING;
                    adapter.notifyDataSetChanged();
                    tvDeviceStatus.setText("准备升级...");
                    Fw.getManager().readyUpdate();
                }
                break;
        }
    }


    /**
     * 清除FLASH成功
     */
    @Override
    public void notifyClearFlash() {
        mHandler.removeMessages(MSG_CLEAR_FLASH_OUT_OF_TIME);
        Fw.getManager().getConnectDevice().refreshDeviceCache();
        Fw.getManager().disConnectDevice();
//        listUpdate.get(mWorkIndex).updateStaus = DeviceUpdateEntity.STATUS_FINISH;
        listUpdate.remove(mWorkIndex);
        adapter.notifyDataSetChanged();
        mDoneSize++;
//        mWorkIndex ++;
        connectNextDevice();
    }

    @Override
    public void notifyReadyUpdate() {
        Fw.getManager().disConnectDevice();
        updateNow();
    }

    @Override
    protected void initData() {
        mSelectVersion = (VersionRE) getIntent().getExtras().getSerializable("version");
        listDevice = (List<GetDeviceListRE.HrdevicesBean>) getIntent().getExtras().getSerializable("device");
        for (GetDeviceListRE.HrdevicesBean device : listDevice) {
            listUpdate.add(new DeviceUpdateEntity(device, DeviceUpdateEntity.STATUS_INIT));
        }
        mAllSize = listUpdate.size();
    }

    @Override
    protected void initViews() {
        tvVersion.setText(mSelectVersion.name);
        adapter = new UpdateDeviceListAdapter(UpdateTestTodoActivity.this, listUpdate);
        lv.setAdapter(adapter);
    }

    @Override
    protected void doMyCreate() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Fw.getManager().registerConnectListener(this);
        Fw.getManager().registerNotifyActiveListener(this);
        DfuServiceListenerHelper.registerProgressListener(this, mDfuProgressListener);
        startConnectSSH();
    }


    @Override
    protected void causeGC() {
        Fw.getManager().unRegisterConnectListener(this);
        Fw.getManager().unRegisterNotifyActiveListener(this);
        if (controller != null){
            controller.pause();
            controller.abort();
        }
        DfuServiceListenerHelper.unregisterProgressListener(this, mDfuProgressListener);
        Fw.getManager().disConnectDevice();

    }

    /**
     * 准备连接设备
     */
    private void connectNextDevice() {
        if (controller != null){
            controller.pause();
            controller.abort();
        }

        if (mDoneSize == mAllSize) {
            Toast.makeText(UpdateTestTodoActivity.this, "升级结束", Toast.LENGTH_SHORT);
            tvDeviceStatus.setText("升级结束");
            tvDeviceName.setText("");
            return;
        }
        //若循环到尾了
        if (mWorkIndex > (listUpdate.size() - 1)) {
            mWorkIndex = 0;
            for (;mWorkIndex < listUpdate.size();mWorkIndex ++){
                if (listUpdate.get(mWorkIndex).updateStaus != DeviceUpdateEntity.STATUS_FINISH){
                    break;
                }
            }
        }
        LogU.v("connectNextDevice" , "mWorkIndex:" + mWorkIndex);

        tvDeviceName.setText(listUpdate.get(mWorkIndex).device.serialno);
        tvDeviceStatus.setText("正在连接...");
        Fw.getManager().addNewConnect(listUpdate.get(mWorkIndex).device.macaddr);
    }

    /**
     * 开始连接SSH服务器
     */
    private void startConnectSSH() {
        String url = "120.26.217.228";

        mSUI = new SessionUserInfo("iosssh", url, "Gogogo123!Abery#", 22);

        SessionController.getSessionController().setUserInfo(mSUI);
        SessionController.getSessionController().connect();
        SessionController.getSessionController().setConnectionStatusListener(new ConnectionStatusListener() {
            @Override
            public void onDisconnected() {
                LogU.e(TAG, "ftps disconnected");
                Message msg = new Message();
                msg.what = MSG_DOWNLOAD_VERSION_ERROR;
                msg.obj = "文件服务器地址失去连接";
                mHandler.sendEmptyMessage(MSG_DOWNLOAD_VERSION_ERROR);
            }

            @Override
            public void onConnected() {
                LogU.e(TAG, "ftps connected");
                SessionController.getSessionController().setConnectionStatusListener(null);
                mHandler.sendEmptyMessage(MSG_CONNECT_SSH_OK);
            }
        });
    }


    /**
     * 下载更新文件包
     */
    private void downloadUpdateFile() {
        // sftp the file
        LogU.e(TAG, "mSelectVersion.ftpurl:" + mSelectVersion.ftpurl);
        try {
            SessionController.getSessionController().downloadFile(mSelectVersion.ftpurl, FileConfig.DOWNLOAD_UPDATE_DEVICE_ZIP,
                    new SftpProgressMonitor() {
                        long mSize = 0;
                        long mCount = 0;

                        @Override
                        public void init(int op, String src, String dest, long max) {
                            mSize = max;
                        }

                        @Override
                        public boolean count(long count) {
                            mCount += count;
                            int progress = (int) ((float) (mCount) / (float) (mSize) * (float) 100);
                            Message message = new Message();
                            message.what = MSG_DOWNLOAD_VERSION_PROGRESS;
                            message.arg1 = progress;
                            mHandler.sendMessage(message);
                            return true;
                        }

                        @Override
                        public void end() {
                            mHandler.sendEmptyMessage(MSG_DOWNLOAD_VERSION_OK);
                        }
                    });
        } catch (JSchException je) {
            LogU.e(TAG, "JschException " + je.getMessage());
            Message msg = new Message();
            msg.what = MSG_DOWNLOAD_VERSION_ERROR;
            msg.obj = je.getMessage();
            mHandler.sendMessage(msg);
        } catch (SftpException se) {
            LogU.e(TAG, "SftpException " + se.getMessage());
            Message msg = new Message();
            msg.what = MSG_DOWNLOAD_VERSION_ERROR;
            msg.obj = se.getMessage();
            mHandler.sendMessage(msg);
        }
    }


    private void updateNow() {
        updateNow = true;
        tvDeviceStatus.setText("升级中...");
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_READY_OUT_OF_TIME,INTERVAL_UPDATE_READY_OUT_OF_TIME);

        final boolean keepBond = false;
        final DfuServiceInitiator starter = new DfuServiceInitiator(listUpdate.get(mWorkIndex).device.macaddr)
                .setDeviceName(listUpdate.get(mWorkIndex).device.name)
                .setKeepBond(keepBond);
        // If you want to have experimental buttonless DFU feature supported call additionally:
        starter.setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
        // but be aware of this: https://devzone.nordicsemi.com/question/100609/sdk-12-bootloader-erased-after-programming/
        // and other issues related to this experimental service.

        // Init packet is required by Bootloader/DFU from SDK 7.0+ if HEX or BIN file is given above.
        // In case of a ZIP file, the init packet (a DAT file) must be included inside the ZIP file.
        final String mFilePath = FileConfig.DOWNLOAD_UPDATE_DEVICE_ZIP;
//        final String mFilePath = FileConfig.DOWNLOAD_PATH + "test.zip";//Test

        final Uri mFileStreamUri = getImageContentUri(UpdateTestTodoActivity.this, new File(mFilePath));
        starter.setZip(mFileStreamUri, mFilePath);
        controller = starter.start(this, DfuService.class);

        // You may use the controller to pause, resume or abort the DFU process.
    }


    /**
     * Gets the content:// URI  from the given corresponding path to a file
     *
     * @param context
     * @param imageFile
     * @return content Uri
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        @Override
        public void onDeviceConnecting(final String deviceAddress) {
            LogU.e(TAG, "onDeviceConnecting:" + "Connecting…");
            mHandler.removeMessages(MSG_UPDATE_READY_OUT_OF_TIME);
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE_READY_OUT_OF_TIME,INTERVAL_UPDATE_READY_OUT_OF_TIME);
            tvDeviceStatus.setText("Dfu Connecting");
        }

        @Override
        public void onDfuProcessStarting(final String deviceAddress) {
            LogU.e(TAG, "onDfuProcessStarting:" + "Starting DFU…");
            mHandler.removeMessages(MSG_UPDATE_READY_OUT_OF_TIME);
            tvDeviceStatus.setText("Starting DFU…");
        }

        @Override
        public void onEnablingDfuMode(final String deviceAddress) {
            LogU.e(TAG, "onEnablingDfuMode:" + "Starting bootloader…");
            tvDeviceStatus.setText("Starting bootloader…");
        }

        @Override
        public void onFirmwareValidating(final String deviceAddress) {
            LogU.e(TAG, "onFirmwareValidating:" + "Validating…");
            tvDeviceStatus.setText("Validating…");
        }

        @Override
        public void onDeviceDisconnecting(final String deviceAddress) {
            LogU.e(TAG, "onDeviceDisconnecting:" + "Disconnecting…");
            mHandler.removeMessages(MSG_DFU_PROGRESS_NOT_UPDATE);
            tvDeviceStatus.setText("Disconnecting…");
        }

        @Override
        public void onDfuCompleted(final String deviceAddress) {
            LogU.e(TAG, "onDfuCompleted:" + "Done");
            mHandler.removeMessages(MSG_DFU_UPDATE_COMPLETE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.cancel(DfuService.NOTIFICATION_ID);
                    mHandler.sendEmptyMessage(MSG_DFU_UPDATE_COMPLETE);
                }
            }, 200);
        }

        @Override
        public void onDfuAborted(final String deviceAddress) {
            LogU.e(TAG, "onDfuAborted:" + "Aborted");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.cancel(DfuService.NOTIFICATION_ID);
                }
            }, 200);
        }

        @Override
        public void onProgressChanged(final String deviceAddress, final int percent, final float speed, final float avgSpeed, final int currentPart, final int partsTotal) {
            LogU.e(TAG, "onProgressChanged " + "percent:" + percent + ",speed:" + speed);
            mHandler.removeMessages(MSG_DFU_PROGRESS_NOT_UPDATE);
            mHandler.sendEmptyMessageDelayed(MSG_DFU_PROGRESS_NOT_UPDATE, INTERVAL_PROGRESS_NOT_UPDATE);
            tvDeviceStatus.setText("DFU 升级进度：" + percent + "%");
        }

        @Override
        public void onError(final String deviceAddress, final int error, final int errorType, final String message) {
            LogU.e(TAG, "onError " + "deviceAddress:" + deviceAddress + ",msg:" + message);
            mHandler.removeMessages(MSG_UPDATE_READY_OUT_OF_TIME);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.cancel(DfuService.NOTIFICATION_ID);
                    Message msg = new Message();
                    msg.what = MSG_DFU_UPDATE_ERROR;
                    msg.obj = message;
                    mHandler.sendMessage(msg);
                }
            }, 200);
        }
    };

    @Override
    public void notifyActive() {

    }

}
