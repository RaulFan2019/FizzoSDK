package cn.fizzo.watch.subject;

import cn.fizzo.watch.observer.NotifyActiveListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public interface NotifyActiveSubject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(NotifyActiveListener observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(NotifyActiveListener observer);

    /**
     * 通知订阅者更新激活消息
     */
    public void notifyActive();

    /**
     * 清除数据
     */
    public void notifyClearFlash();


    /**
     * 准备好升级
     */
    public void notifyReadyUpdate();

}
