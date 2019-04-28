package cn.fizzo.watch.sdksample.ssh;


public interface ExecTaskCallbackHandler {

    void onFail();

    void onComplete(String completeString);
}
