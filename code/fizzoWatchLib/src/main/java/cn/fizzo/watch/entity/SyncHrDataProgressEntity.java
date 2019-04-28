package cn.fizzo.watch.entity;

/**
 * Created by Raul.fan on 2017/6/21 0021.
 */

public class SyncHrDataProgressEntity {

    public int HistorySize;//本次同步需要同步的history数量
    public long ItemsSize;//本次同步需要同步的数据量
    public int HistoryFinishSize;//已完成history数量
    public long ItemsFinishSize;//已完成同步数据量


    public SyncHrDataProgressEntity() {
    }

    public SyncHrDataProgressEntity(int historySize,int historyFinishSize, long itemsSize,  long itemsFinishSize) {
        super();
        HistorySize = historySize;
        ItemsSize = itemsSize;
        HistoryFinishSize = historyFinishSize;
        ItemsFinishSize = itemsFinishSize;
    }
}
