package cn.fizzo.watch.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul.fan on 2017/6/21 0021.
 */
public class SyncHrDataEntity {

    public long startTime;
    public List<byte[]> mHistoryWatchData;
    public List<SyncHrItemEntity> itemEntities;

    public SyncHrDataEntity() {
    }

    public SyncHrDataEntity(long startTime) {
        super();
        this.startTime = startTime;
        mHistoryWatchData = new ArrayList<>();
        itemEntities = new ArrayList<>();
    }
}
