package cn.fizzo.watch.subject;

import cn.fizzo.watch.observer.SetSportTypeListener;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/8/24 14:40
 */
public interface SetSportTypeSubject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(SetSportTypeListener observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(SetSportTypeListener observer);

    /**
     * 通知订阅者更新消息
     */
    public void notify(boolean success);
}
