package cn.fizzo.watch.utils;

import android.os.Handler;
import android.os.Message;

import cn.fizzo.watch.Fw;
import cn.fizzo.watch.array.NotifyActions;
import cn.fizzo.watch.entity.HrEntity;
import cn.fizzo.watch.entity.SyncHrDataEntity;
import cn.fizzo.watch.entity.SyncHrDataProgressEntity;

/**
 * Created by Raul.Fan on 2017/3/31.
 */

public class NotifyManager {


    private static NotifyManager instance;//唯一实例

    private NotifyManager() {

    }

    /**
     * 获取堆栈管理的单一实例
     */
    public static NotifyManager getManager() {
        if (instance == null) {
            instance = new NotifyManager();
        }
        return instance;
    }

    Handler mNotifyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                //发布连接状态变化
                case NotifyActions.CONNECT_STATE:
                    Fw.getManager().notifyStateChange((Integer) msg.obj);
                    break;
                //心率变化
                case NotifyActions.NOTIFY_HR:
                    Fw.getManager().notifyHr((HrEntity) msg.obj);
                    break;
                //接收到激活状态
                case NotifyActions.NOTIFY_ACTIVE:
                    Fw.getManager().notifyActive();
                    break;
                //同步进度发生变化
                case NotifyActions.NOTIFY_SYNC_HR_DATA_PROGRESS:
                    SyncHrDataProgressEntity entity = (SyncHrDataProgressEntity) msg.obj;
                    Fw.getManager().notifySyncHrProgressActive(entity);
                    break;
                //同步结束
                case NotifyActions.NOTIFY_SYNC_HR_DATA_FINISH:
                    Fw.getManager().notifySyncFinish((int) msg.obj);
                    break;
                //同步完成一条记录
                case NotifyActions.NOTIFY_SYNC_HR_DATA_HISTORY:
                    Fw.getManager().notifyCompleteOneHrHistory((SyncHrDataEntity) msg.obj);
                    break;
                //清除Flash数据
                case NotifyActions.NOTIFY_CLEAR_FLASH:
                    Fw.getManager().notifyClearFlash();
                    break;
                //获取电量
                case NotifyActions.NOTIFY_BATTERY:
                    Fw.getManager().notifyBattery((int) msg.obj);
                    break;
                //设置运动模式
                case NotifyActions.NOTIFY_SET_SPORT_TYPE:
                    Fw.getManager().notifySetSportType((boolean) msg.obj);
                    break;
                //准备完毕
                case NotifyActions.NOTIFY_READY_UPDATE:
                    Fw.getManager().notifyReadyUpdate();
                    break;
            }
        }
    };

    /**
     * 发布连接状态变化
     *
     * @param state
     */
    public synchronized void notifyStateChange(final int state) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.CONNECT_STATE);
        msg.obj = state;
        mNotifyHandler.sendMessage(msg);
    }

    /**
     * 发布实时心率数据
     */
    public synchronized void notifyRealTimeHr(final HrEntity hrEntity) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_HR);
        msg.obj = hrEntity;
        mNotifyHandler.sendMessage(msg);
    }

    /**
     * 发布电量数据
     */
    public synchronized void notifyBattery(final int battery) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_BATTERY);
        msg.obj = battery;
        mNotifyHandler.sendMessage(msg);
    }


    /**
     * 接收到激活按键
     */
    public synchronized void notifyActive() {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_ACTIVE);
        mNotifyHandler.sendMessage(msg);
    }

    /**
     * 通知设置运动模式结果
     */
    public synchronized void notifySetSportType(boolean res) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_SET_SPORT_TYPE);
        msg.obj = res;
        mNotifyHandler.sendMessage(msg);
    }


    public synchronized void notifyClearFlash() {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_CLEAR_FLASH);
        mNotifyHandler.sendMessage(msg);
    }

    public synchronized void notifyReadyUpdate() {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_READY_UPDATE);
        mNotifyHandler.sendMessage(msg);
    }

    /**
     * 心率同步数据发生变化
     *
     * @param entity
     */
    public synchronized void notifySyncHrDataProgress(SyncHrDataProgressEntity entity) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_SYNC_HR_DATA_PROGRESS);
        msg.obj = entity;
        mNotifyHandler.sendMessage(msg);
    }

    /**
     * 发送同步完成消息
     *
     * @param state
     */
    public synchronized void notifySyncHrDataFinish(int state) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_SYNC_HR_DATA_FINISH);
        msg.obj = state;
        mNotifyHandler.sendMessage(msg);
    }


    /**
     * 发送完成一条数据记录的消息
     *
     * @param entity
     */
    public synchronized void notifySyncHrDataHistory(SyncHrDataEntity entity) {
        Message msg = mNotifyHandler.obtainMessage(NotifyActions.NOTIFY_SYNC_HR_DATA_HISTORY);
        msg.obj = entity;
        mNotifyHandler.sendMessage(msg);
    }
}
