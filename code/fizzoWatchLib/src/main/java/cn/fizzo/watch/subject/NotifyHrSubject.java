package cn.fizzo.watch.subject;

import cn.fizzo.watch.entity.HrEntity;
import cn.fizzo.watch.observer.NotifyHrListener;

/**
 * Created by Raul.Fan on 2017/3/29.
 */
public interface NotifyHrSubject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(NotifyHrListener observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(NotifyHrListener observer);
    /**
     * 通知订阅者更新消息
     */
    public void notify(HrEntity hrEntity);

}
