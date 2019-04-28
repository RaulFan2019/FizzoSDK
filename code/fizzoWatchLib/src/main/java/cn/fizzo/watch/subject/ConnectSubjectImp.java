package cn.fizzo.watch.subject;

import java.util.ArrayList;
import java.util.List;

import cn.fizzo.watch.observer.ConnectListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public class ConnectSubjectImp implements ConnectSubject {


    private List<ConnectListener> mConnectObservers = new ArrayList<ConnectListener>();

    @Override
    public void attach(ConnectListener observer) {
        mConnectObservers.add(observer);
    }

    @Override
    public void detach(ConnectListener observer) {
        mConnectObservers.remove(observer);
    }

    @Override
    public void notify(int connectState) {
        for (ConnectListener observer : mConnectObservers) {
            observer.connectStateChange(connectState);
        }
    }
}
