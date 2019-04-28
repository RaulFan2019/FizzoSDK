package cn.fizzo.watch.subject;

import java.util.ArrayList;
import java.util.List;

import cn.fizzo.watch.entity.SyncHrDataEntity;
import cn.fizzo.watch.entity.SyncHrDataProgressEntity;
import cn.fizzo.watch.observer.ConnectListener;
import cn.fizzo.watch.observer.SyncHrDataListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public class SyncHrDataSubjectImp implements SyncHrDataSubject {


    private List<SyncHrDataListener> mObservers = new ArrayList<>();

    @Override
    public void attach(SyncHrDataListener observer) {
        mObservers.add(observer);
    }

    @Override
    public void detach(SyncHrDataListener observer) {
        mObservers.remove(observer);
    }


    @Override
    public void notifySyncProgress(SyncHrDataProgressEntity entity) {
        for (SyncHrDataListener observer : mObservers) {
            observer.syncProgressChange(entity);
        }
    }

    @Override
    public void notifySyncFinish(int state) {
        for (SyncHrDataListener observer : mObservers) {
            observer.syncFinish(state);
        }
    }

    @Override
    public void completeOneHrHistory(SyncHrDataEntity entity) {
        for (SyncHrDataListener observer : mObservers) {
            observer.completeOneHrHistory(entity);
        }
    }


}
