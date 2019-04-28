package cn.fizzo.watch.subject;

import java.util.ArrayList;
import java.util.List;

import cn.fizzo.watch.observer.BatteryListener;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/8/24 14:41
 */
public class BatterySubjectImp implements BatterySubject {


    private List<BatteryListener> mBatteryObservers = new ArrayList<BatteryListener>();

    @Override
    public void attach(BatteryListener observer) {
        mBatteryObservers.add(observer);
    }

    @Override
    public void detach(BatteryListener observer) {
        mBatteryObservers.remove(observer);
    }

    @Override
    public void notify(int battery) {
        for (BatteryListener observer : mBatteryObservers) {
            observer.readBattery(battery);
        }
    }
}
