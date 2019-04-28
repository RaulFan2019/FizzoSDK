package cn.fizzo.watch.sdksample.ssh;

/**
 * Interface that must be implemented by any object wishing to Listen
 * to the Jsch connection status (either connected or disconnected).
 * Created by Jon Hough 7/31/14.
 */
public interface ConnectionStatusListener {

    /**
     * Handles event of Session not connected
     */
   public void onDisconnected();

    /**
     * Handles event of Session connected
     */
   public void onConnected();
}
