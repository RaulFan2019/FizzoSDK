package cn.fizzo.watch.sdksample;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

import java.io.File;

import cn.fizzo.watch.Fw;
import cn.fizzo.watch.sdksample.config.FileConfig;

/**
 * Created by Raul.Fan on 2016/11/6.
 */
public class LocalApp extends Application {


    private static final String TAG = "LocalApplication";

    public static Context applicationContext;//整个APP的上下文
    private static LocalApp instance;//Application 对象


    @Override
    public void onCreate() {
        createFileSystem();
        super.onCreate();
        applicationContext = getApplicationContext();
        x.Ext.init(this);
        //初始化Fizzo SDK
        Fw.getManager().init(this);
        Fw.getManager().setDebug(false);
    }

    /**
     * 获取 LocalApplication
     *
     * @return
     */
    public static LocalApp getInstance() {
        if (instance == null) {
            instance = new LocalApp();
        }
        return instance;
    }

    /**
     * 创建私有文件目录
     */
    private void createFileSystem() {
        File downloadF = new File(FileConfig.DOWNLOAD_PATH);
        if (!downloadF.exists()) {
            downloadF.mkdirs();
        }
    }
}
