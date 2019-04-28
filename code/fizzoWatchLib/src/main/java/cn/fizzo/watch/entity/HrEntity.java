package cn.fizzo.watch.entity;

/**
 * Created by Raul.Fan on 2017/2/8.
 * 心率数据模型
 */
public class HrEntity {

    public int hr;   //心率
    public int stepCount; //当前步数
    public int sportSize;  //运动数量

    public HrEntity(int hr, int stepCount, int sportSize) {
        this.hr = hr;
        this.stepCount = stepCount;
        this.sportSize = sportSize;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getSportSize() {
        return sportSize;
    }

    public void setSportSize(int sportSize) {
        this.sportSize = sportSize;
    }
}
