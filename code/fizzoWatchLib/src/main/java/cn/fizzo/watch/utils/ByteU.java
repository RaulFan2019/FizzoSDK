package cn.fizzo.watch.utils;

/**
 * Created by Raul.fan on 2017/6/20 0020.
 */

public class ByteU {


    /**
     * byte 数组转化成16进制的字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(final byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * @param b
     * @return
     */
    public static long bytesToLong(byte[] b) {

        int mask = 0xff;
        int temp = 0;
        long n = 0;
        for (int i = 0; i < b.length; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }


    /**
     * long 转成byte数组
     * @param startTime
     * @return
     */
    public static byte[] longToBytes(long startTime) {
        byte[] buf = new byte[8];

        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (startTime & 0x00000000000000ff);
            startTime >>= 8;
        }
        return buf;
    }
}
