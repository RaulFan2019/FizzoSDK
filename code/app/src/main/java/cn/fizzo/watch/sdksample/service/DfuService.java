package cn.fizzo.watch.sdksample.service;

import android.app.Activity;

import cn.fizzo.watch.sdksample.activity.NotificationActivity;
import no.nordicsemi.android.dfu.DfuBaseService;

/**
 * Created by Raul.Fan on 2017/1/3.
 */

public class DfuService extends DfuBaseService {

    @Override
    protected Class<? extends Activity> getNotificationTarget() {
        return NotificationActivity.class;
    }
}
