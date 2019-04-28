package cn.fizzo.watch.sdksample.entity;

import android.bluetooth.BluetoothDevice;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/9/20 9:51
 */
public class BleScanAE {

    public BluetoothDevice device;
    public int rssi;

    public BleScanAE(BluetoothDevice device, int rssi) {
        this.device = device;
        this.rssi = rssi;
    }

}
