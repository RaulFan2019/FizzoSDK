package cn.fizzo.watch.array;

/**
 * Created by Raul.Fan on 2017/3/29.
 * 连接状态
 */
public class ConnectStates {

    //正在连接
    public static final int CONNECTING = 0x01;

    //断开连接
    public static final int DISCONNECT = 0x02;

    //尝试连接，但连接失败
    public static final int CONNECT_FAIL = 0x03;

    //已连接(仅Ble Gatt 连接成功，不代表已经能正常工作)
    public static final int CONNECTED = 0x04;

    //读取制造厂商
    public static final int CHECK_MANUFACTURER_OK = 0x05;

    //不是FIZZO手环
    public static final int CHECK_MANUFACTURER_FAIL = 0x06;

    //写入UTC成功
    public static final int WRITE_UTC_OK = 0x07;

    //notify 心率成功
    public static final int NOTIFY_HR_OK = 0x08;

    //获取手环 firmware 版本号成功
    public static final int READ_FIRMWARE_OK = 0x09;

    //监听私有服务成功
    public static final int NOTIFY_PRIVATE_SERVICE_OK = 0x10;
}
