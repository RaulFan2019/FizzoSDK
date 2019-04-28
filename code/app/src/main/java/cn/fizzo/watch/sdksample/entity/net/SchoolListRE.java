package cn.fizzo.watch.sdksample.entity.net;

import java.util.List;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/11/8 16:17
 */
public class SchoolListRE {


    public List<StoresBean> stores;

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class StoresBean {
        /**
         * id : 74
         * registertime : 2017-04-17 19:24:22
         * type : 2
         * name : 平南小学
         * logo : http://7xk0si.com1.z0.glb.clouddn.com/2017-11-20_5a12441a37af2.jpg
         * lesson_durations : 1200,2100,3600
         */

        public int id;
        public String registertime;
        public int type;
        public String name;
        public String logo;
        public String lesson_durations;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegistertime() {
            return registertime;
        }

        public void setRegistertime(String registertime) {
            this.registertime = registertime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLesson_durations() {
            return lesson_durations;
        }

        public void setLesson_durations(String lesson_durations) {
            this.lesson_durations = lesson_durations;
        }
    }
}
