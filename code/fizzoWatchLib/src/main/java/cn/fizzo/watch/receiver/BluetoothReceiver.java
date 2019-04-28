package cn.fizzo.watch.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;

import cn.fizzo.watch.Fw;
import cn.fizzo.watch.utils.Log;

/**
 * Created by Raul.Fan on 2017/3/23.
 */
public class BluetoothReceiver extends BroadcastReceiver {

    private static final String TAG = "SystemTaskReceiver";

    private static final int MSG_BLE_OFF = 0x01;
    private static final int MSG_BLE_ON = 0x02;

    //若蓝牙开关打开后，马上连接会出错，延迟3秒
    private static final long DELAY_BLE_TURN_ON = 3000;

    
    Handler mLocalHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //蓝牙断开消息
                case MSG_BLE_OFF:
                    Fw.getManager().disConnectDevice();
                    break;
                //监测到蓝牙开
                case MSG_BLE_ON:
                    Fw.getManager().recovery();
                    break;
            }
        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {
            //蓝牙开关发生变化
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                switch (blueState) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.e(TAG, "BluetoothAdapter STATE CHANGED:STATE_TURNING_ON");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.e(TAG, "BluetoothAdapter STATE CHANGED:STATE_ON");
                        mLocalHandler.sendEmptyMessageDelayed(MSG_BLE_ON,DELAY_BLE_TURN_ON);
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.e(TAG, "BluetoothAdapter STATE CHANGED:STATE_TURNING_OFF");
                        mLocalHandler.sendEmptyMessage(MSG_BLE_OFF);
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Log.e(TAG, "BluetoothAdapter STATE CHANGED:STATE_OFF");
                        break;
                }
                break;
            //网络发生变化
            case ConnectivityManager.CONNECTIVITY_ACTION:
                Log.e(TAG, "ConnectivityManager STATE CHANGED");
                break;
        }
    }
}
