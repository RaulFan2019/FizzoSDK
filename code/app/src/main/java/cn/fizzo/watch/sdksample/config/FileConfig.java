package cn.fizzo.watch.sdksample.config;

import android.os.Environment;

import java.io.File;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 17:27
 */
public class FileConfig {

    /**
     * 文件总目录
     */
    public final static String DEFAULT_PATH = Environment.getExternalStorageDirectory() + File.separator
            + "fizzoSDK" + File.separator;

    public final static String DOWNLOAD_PATH = DEFAULT_PATH + "download" + File.separator;
    public final static String DOWNLOAD_UPDATE_DEVICE_ZIP = DOWNLOAD_PATH + "update.zip";

}
