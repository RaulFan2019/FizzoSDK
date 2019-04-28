package cn.fizzo.watch.subject;

import java.util.ArrayList;
import java.util.List;

import cn.fizzo.watch.entity.HrEntity;
import cn.fizzo.watch.observer.NotifyHrListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public class NotifyHrSubjectImp implements NotifyHrSubject {


    private List<NotifyHrListener> mConnectObservers = new ArrayList<NotifyHrListener>();

    @Override
    public void attach(NotifyHrListener observer) {
        mConnectObservers.add(observer);
    }

    @Override
    public void detach(NotifyHrListener observer) {
        mConnectObservers.remove(observer);
    }

    @Override
    public void notify(HrEntity hrEntity) {
        for (NotifyHrListener observer : mConnectObservers) {
            observer.notifyHr(hrEntity);
        }
    }
}
