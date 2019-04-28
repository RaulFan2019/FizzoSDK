package cn.fizzo.watch.array;

/**
 * Created by Raul.Fan on 2017/3/31.
 * 通知类型
 */
public class NotifyActions {

    //手环连接状态发生变化
    public static final int CONNECT_STATE = 0x01;

    //心率数据发生变化
    public static final int NOTIFY_HR = 0x02;

    //接收到激活信息
    public static final int NOTIFY_ACTIVE = 0x03;

    //同步心率数据发生变化
    public static final int NOTIFY_SYNC_HR_DATA_PROGRESS = 0x04;

    //同步心率结束
    public static final int NOTIFY_SYNC_HR_DATA_FINISH = 0x05;

    //同步一条心率数据结束
    public static final int NOTIFY_SYNC_HR_DATA_HISTORY = 0x06;

    //清除Flash
    public static final int NOTIFY_CLEAR_FLASH = 0x07;

    //电量的变化
    public static final int NOTIFY_BATTERY = 0x08;

    //设置运动模式
    public static final int NOTIFY_SET_SPORT_TYPE = 0x09;

    //准备更新
    public static final int NOTIFY_READY_UPDATE = 0x10;
}
