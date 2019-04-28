package cn.fizzo.watch.entity;

/**
 * Created by Raul.fan on 2017/6/21 0021.
 */
public class SyncHrItemEntity {

    public int bmp;//心率
    public long timeOffSet;//相对时间
    public int reserved;//保留字段

    public SyncHrItemEntity() {
    }

    public SyncHrItemEntity(int bmp, long timeOffSet,int reserved) {
        super();
        this.bmp = bmp;
        this.timeOffSet = timeOffSet;
        this.reserved = reserved;
    }
}
