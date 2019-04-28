package cn.fizzo.watch.subject;

import cn.fizzo.watch.observer.BatteryListener;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/8/24 14:40
 */
public interface BatterySubject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(BatteryListener observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(BatteryListener observer);

    /**
     * 通知订阅者更新消息
     */
    public void notify(int battery);
}
