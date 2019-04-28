package cn.fizzo.watch.array;

/**
 * Created by Raul.Fan on 2017/4/1.
 * Fizzo 自定义协议
 */
public class FizzoCMDs {


    //* 设置相关
    public static final byte ACTION_TAG_SETTING = (byte) 0xdd;//TAG
    public static final byte CMD_SETTING_UTC = (byte) 0xfa;//CMD  设置手环UTC
    public static final byte CMD_SETTING_HR_RANGE = (byte) 0xfb;//CMD  设置用户个人心率区间
    public static final byte CMD_SETTING_LIGHT_CONTROL = (byte) 0xfc;//CMD 光管控制
    public static final byte DATA_SETTING_LIGHT_OFF = (byte)0x00;//data 关光管
    public static final byte DATA_SETTING_LIGHT_ON = (byte)0x01;//data 开光管
    public static final byte CMD_SETTING_SPORT_TYPE = (byte) 0xfd;//设置运动模式

    //* 激活
    public static final byte ACTION_TAG_ACTIVE = (byte) 0xaa;//TAG
    public static final byte CMD_SETTING_VIBRATE = (byte) 0xfe;//发送振动请求


    //*  清除
    public static final byte ACTION_TAG_SYSTEM = (byte) 0xbb;//TAG
    public static final byte CMD_CLEAR_FLASH = (byte) 0xfd;//发送清除数据请求
    public static final byte CMD_READY_UPDATE = (byte) 0xfc;//CMD 准备升级

    //同步心率数据
    public static final byte ACTION_TAG_SYNC_HR_DATA = (byte)0xee;//TAG
    public static final byte CMD_SYNC_HR_DATA_GET_TOTAL = (byte) 0xfc;//获取需要同步的记录总数量
    public static final byte CMD_SYNC_HR_DATA_GET_HEADER = (byte) 0xfa;//获取同步记录的头信息
    public static final byte CMD_SYNC_HR_DATA_GET_HEADER_ERROR = (byte) 0xbb;//获取头信息错误
    public static final byte CMD_SYNC_HR_DATA_NOTIFY = (byte) 0xfb;//获取心率数据
    public static final byte CMD_SYNC_HR_DATA_NOTIFY_FINISH = (byte) 0xee;//获取心率数据结束
    public static final byte CMD_DELETE_ONE_HR_DATA = (byte) 0xfd;//删除一条数据


    public final static byte STATUS_SETTING_SUCCESS = 0;
}
