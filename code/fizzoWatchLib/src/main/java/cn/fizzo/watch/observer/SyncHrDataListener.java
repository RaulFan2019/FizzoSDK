package cn.fizzo.watch.observer;

import cn.fizzo.watch.entity.SyncHrDataEntity;
import cn.fizzo.watch.entity.SyncHrDataProgressEntity;

/**
 * Created by Raul.Fan on 2017/3/29.
 */

public interface SyncHrDataListener {

    public void syncProgressChange(SyncHrDataProgressEntity entity);

    public void syncFinish(int state);

    public void completeOneHrHistory(SyncHrDataEntity entity);
}
