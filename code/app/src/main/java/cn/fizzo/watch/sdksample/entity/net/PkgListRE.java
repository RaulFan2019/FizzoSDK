package cn.fizzo.watch.sdksample.entity.net;

import java.io.Serializable;
import java.util.List;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 16:35
 */
public class PkgListRE {


    /**
     * count : 2
     * hrpackages : [{"id":3,"name":"MS手环包","hrdevice_count":49,"lessonid":901,"lessonstatus":0,"lessonreminder":1,"lessonstarttime":"2018-09-21 11:39:09","lessonclassid":88,"lessonclassname":"1年级4班","lessonteacherid":23075,"lessonteachername":"夏贤月"},{"id":17,"name":"PN手环包","hrdevice_count":47,"lessonid":0,"lessonstatus":0,"lessonreminder":0,"lessonstarttime":"","lessonclassid":88,"lessonclassname":"1年级4班","lessonteacherid":23075,"lessonteachername":"夏贤月"}]
     */

    public int count;
    public List<HrpackagesBean> hrpackages;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HrpackagesBean> getHrpackages() {
        return hrpackages;
    }

    public void setHrpackages(List<HrpackagesBean> hrpackages) {
        this.hrpackages = hrpackages;
    }

    public static class HrpackagesBean implements Serializable{
        /**
         * id : 3
         * name : MS手环包
         * hrdevice_count : 49
         * lessonid : 901
         * lessonstatus : 0
         * lessonreminder : 1
         * lessonstarttime : 2018-09-21 11:39:09
         * lessonclassid : 88
         * lessonclassname : 1年级4班
         * lessonteacherid : 23075
         * lessonteachername : 夏贤月
         */

        public int id;
        public String name;
        public int hrdevice_count;
        public int lessonid;
        public int lessonstatus;
        public int lessonreminder;
        public String lessonstarttime;
        public int lessonclassid;
        public String lessonclassname;
        public int lessonteacherid;
        public String lessonteachername;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHrdevice_count() {
            return hrdevice_count;
        }

        public void setHrdevice_count(int hrdevice_count) {
            this.hrdevice_count = hrdevice_count;
        }

        public int getLessonid() {
            return lessonid;
        }

        public void setLessonid(int lessonid) {
            this.lessonid = lessonid;
        }

        public int getLessonstatus() {
            return lessonstatus;
        }

        public void setLessonstatus(int lessonstatus) {
            this.lessonstatus = lessonstatus;
        }

        public int getLessonreminder() {
            return lessonreminder;
        }

        public void setLessonreminder(int lessonreminder) {
            this.lessonreminder = lessonreminder;
        }

        public String getLessonstarttime() {
            return lessonstarttime;
        }

        public void setLessonstarttime(String lessonstarttime) {
            this.lessonstarttime = lessonstarttime;
        }

        public int getLessonclassid() {
            return lessonclassid;
        }

        public void setLessonclassid(int lessonclassid) {
            this.lessonclassid = lessonclassid;
        }

        public String getLessonclassname() {
            return lessonclassname;
        }

        public void setLessonclassname(String lessonclassname) {
            this.lessonclassname = lessonclassname;
        }

        public int getLessonteacherid() {
            return lessonteacherid;
        }

        public void setLessonteacherid(int lessonteacherid) {
            this.lessonteacherid = lessonteacherid;
        }

        public String getLessonteachername() {
            return lessonteachername;
        }

        public void setLessonteachername(String lessonteachername) {
            this.lessonteachername = lessonteachername;
        }
    }
}
