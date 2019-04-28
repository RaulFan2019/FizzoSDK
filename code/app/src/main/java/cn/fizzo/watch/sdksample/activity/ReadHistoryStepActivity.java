package cn.fizzo.watch.sdksample.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import butterknife.BindView;
import cn.fizzo.watch.Fw;
import cn.fizzo.watch.sdksample.R;

/**
 * Created by machenike on 2017/5/22 0022.
 */

public class ReadHistoryStepActivity extends BaseActivity {


    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read_history_step;
    }

    @Override
    protected void myHandleMsg(Message msg) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void doMyCreate() {
        Fw.getManager().readHistoryStep();
    }

    @Override
    protected void causeGC() {

    }

}
