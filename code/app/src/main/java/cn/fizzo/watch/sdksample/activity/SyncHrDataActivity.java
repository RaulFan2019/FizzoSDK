package cn.fizzo.watch.sdksample.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import cn.fizzo.watch.Fw;
import cn.fizzo.watch.array.SyncHrDataStates;
import cn.fizzo.watch.entity.SyncHrDataEntity;
import cn.fizzo.watch.entity.SyncHrDataProgressEntity;
import cn.fizzo.watch.observer.SyncHrDataListener;
import cn.fizzo.watch.sdksample.R;
import cn.fizzo.watch.sdksample.utils.TimeU;

/**
 * Created by Raul.fan on 2017/6/20 0020.
 */

public class SyncHrDataActivity extends BaseActivity implements SyncHrDataListener {

    private static final String TAG = "SyncHrDataActivity";

    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_log)
    TextView tvLog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sync_hr_data;
    }

    @Override
    protected void myHandleMsg(Message msg) {

    }


    /**
     * 心率同步数据进度变化
     *
     * @param entity
     */
    @Override
    public void syncProgressChange(SyncHrDataProgressEntity entity) {
        tvProgress.setText("历史数量：" + entity.HistoryFinishSize + "/" + entity.HistorySize
                + "\n" + " 数据量:" + entity.ItemsFinishSize + "/" + entity.ItemsSize);
    }

    /**
     * 同步结束
     *
     * @param state
     */
    @Override
    public void syncFinish(int state) {
        //同步正常结束
        if (state == SyncHrDataStates.SYNC_HR_STATE_FINISH_SUCCESS) {
            tvState.setText("正常结束");
        }
    }

    @Override
    public void completeOneHrHistory(SyncHrDataEntity entity) {
        tvLog.append("\n" + "同步完一条新记录：" + TimeU.formatDateToStr(new Date(entity.startTime * 1000), TimeU.FORMAT_TYPE_1));
        tvLog.append("\n" + "本条记录心率数量：" + entity.itemEntities.size());
        Fw.getManager().deleteOneHrHistory(entity.startTime);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }


    @Override
    protected void doMyCreate() {
        Fw.getManager().registerSyncHrDataListener(this);
        startSyncHrData();
    }

    @Override
    protected void causeGC() {

    }


    /**
     * 开始同步心率数据
     */
    private void startSyncHrData() {
        Fw.getManager().syncHrData();
    }


}
