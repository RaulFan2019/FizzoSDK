package cn.fizzo.watch.sdksample.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import cn.fizzo.watch.Fw;
import cn.fizzo.watch.sdksample.R;
import io.reactivex.functions.Consumer;

/**
 * Created by Raul.Fan on 2017/5/4.
 */

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.tv_anim)
    TextView tvAnim;

    private boolean firstIn = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
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

        //申请权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE

                ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                firstIn = false;
                animLaunch();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstIn){
            animLaunch();
        }
    }

    @Override
    protected void causeGC() {

    }

    /**
     * 页面启动动画
     */
    private void animLaunch() {
        AlphaAnimation lAnim = new AlphaAnimation(0.1f, 1.0f);
        lAnim.setDuration(1000);

        if (tvAnim == null || lAnim == null) {
            startActivity(ScanActivity.class);
            return;
        }
        tvAnim.startAnimation(lAnim);
        lAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                startActivity(ScanActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

}
