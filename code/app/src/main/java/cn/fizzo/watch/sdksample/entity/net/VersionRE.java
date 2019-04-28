package cn.fizzo.watch.sdksample.entity.net;

import java.io.Serializable;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 15:59
 */
public class VersionRE implements Serializable{


    /**
     * id : 115
     * name : 1.2.82sc
     * description : Upload step count through ant+
     * ftpurl : /home/sftp/upload/2018-11-06/1541469166_hrm_1_2_82sc.zip
     * size : 80362
     * date : 2018-11-06 09:52:46
     * md5sum : a8b6e12a01da0c1ffe2d9793078776c3
     * type : 2
     */

    public String id;
    public String name;
    public String description;
    public String ftpurl;
    public String size;
    public String date;
    public String md5sum;
    public String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFtpurl() {
        return ftpurl;
    }

    public void setFtpurl(String ftpurl) {
        this.ftpurl = ftpurl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
