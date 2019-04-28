package cn.fizzo.watch.array;

/**
 * Created by Raul.fan on 2017/6/20 0020.
 */

public class SyncHrDataStates {

    public static final int SYNC_HR_STATE_FINISH_SUCCESS = 0x01;//正常结束同步
    public static final int SYNC_HR_STATE_FINISH_ERROR = 0x02;//异常结束同步
    public static final int SYNC_HR_STATE_FINISH_OUT_OF_TIME = 0x03;//同步超时退出
}
