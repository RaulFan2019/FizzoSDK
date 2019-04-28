package cn.fizzo.watch.subject;

import java.util.ArrayList;
import java.util.List;

import cn.fizzo.watch.observer.SetSportTypeListener;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/8/24 14:41
 */
public class SetSportTypeSubjectImp implements SetSportTypeSubject {


    private List<SetSportTypeListener> mObservers = new ArrayList<SetSportTypeListener>();

    @Override
    public void attach(SetSportTypeListener observer) {
        mObservers.add(observer);
    }

    @Override
    public void detach(SetSportTypeListener observer) {
        mObservers.remove(observer);
    }

    @Override
    public void notify(boolean success) {
        for (SetSportTypeListener observer : mObservers) {
            observer.setSportTypeSuccessful(success);
        }
    }
}
