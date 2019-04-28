package cn.fizzo.watch.array;

import java.util.UUID;

/**
 * Created by Raul.Fan on 2017/3/28.
 * Gatt 相关的UUID
 */
public class GattUUIDs {

    public static final String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    //电量
    public static final String BATTERY_SERVICE = "0000180f-0000-1000-8000-00805f9b34fb";
    public static final String BATTERY = "00002A19-0000-1000-8000-00805f9b34fb";
    public final static UUID UUID_BATTERY_SERVICE = UUID
            .fromString(BATTERY_SERVICE);
    public final static UUID UUID_BATTERY = UUID
            .fromString(BATTERY);

    /* 心率相关 */
    public static final String HEART_RATE_SERVICE = "0000180d-0000-1000-8000-00805f9b34fb";
    public static final String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public final static UUID UUID_HEART_RATE_SERVICE = UUID
            .fromString(HEART_RATE_SERVICE);
    public final static UUID UUID_HEART_RATE_MEASUREMENT = UUID
            .fromString(HEART_RATE_MEASUREMENT);

    /* Device Info */
    public static final String DEVICE_INFO_SERVICE = "0000180a-0000-1000-8000-00805f9b34fb";
    //制造商
    public static final String MANUFACTURER_NAME = "00002a29-0000-1000-8000-00805f9b34fb";
    //model
    public static final String MODEL = "00002a24-0000-1000-8000-00805f9b34fb";
    //Firmware 版本
    public static final String FIRMWARE_REVISION = "00002a26-0000-1000-8000-00805f9b34fb";

    public final static UUID UUID_DEVICE_INFO_SERVICE = UUID
            .fromString(DEVICE_INFO_SERVICE);
    public final static UUID UUID_MANUFACTURER_NAME = UUID
            .fromString(MANUFACTURER_NAME);
    public final static UUID UUID_FIRMWARE_REVISION = UUID
            .fromString(FIRMWARE_REVISION);
    public final static UUID UUID_MODEL = UUID
            .fromString(MODEL);


    /* private Fizzo service */
    public static final String FIZZO_SERVICE = "0000c0d0-0000-1000-8000-00805f9b34fb";    //Fizzo私有服务
    public static final String FIZZO_CMD_C = "0000c0d1-0000-1000-8000-00805f9b34fb";      //写入命令的特征值
    public static final String FIZZO_NOTIFY_C = "0000c0d2-0000-1000-8000-00805f9b34fb";   //notify命令结果的特征值

    public final static UUID UUID_FIZZO_SERVICE = UUID
            .fromString(FIZZO_SERVICE);
    public final static UUID UUID_FIZZO_CMD_C = UUID
            .fromString(FIZZO_CMD_C);
    public final static UUID UUID_FIZZO_NOTIFY_C = UUID
            .fromString(FIZZO_NOTIFY_C);


}
