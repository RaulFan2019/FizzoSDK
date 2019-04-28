package cn.fizzo.watch.subject;

import cn.fizzo.watch.entity.SyncHrDataEntity;
import cn.fizzo.watch.entity.SyncHrDataProgressEntity;
import cn.fizzo.watch.observer.ConnectListener;
import cn.fizzo.watch.observer.SyncHrDataListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public interface SyncHrDataSubject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(SyncHrDataListener observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(SyncHrDataListener observer);

    /**
     * 通知订阅者同步进度发生变化
     */
    public void notifySyncProgress(SyncHrDataProgressEntity entity);

    /**
     * 通知订阅者同步进度发生变化
     */
    public void notifySyncFinish(int state);


    /**
     * 通知订阅者完成一条记录解析
     * @param entity
     */
    public void completeOneHrHistory(SyncHrDataEntity entity);
}
