package cn.fizzo.watch.sdksample.entity.net;

import java.io.Serializable;
import java.util.List;

/**
 * @author Raul.Fan
 * @email 35686324@qq.com
 * @date 2018/9/12 13:59
 */
public class GetDeviceListRE {


    /**
     * count : 49
     * hrdevices : [{"id":2200,"hrdevicenumber":1,"name":"CRA775FFMCEWD","serialno":"CRA775FFMCEWD","macaddr":"DD:D7:E4:80:37:E8","antplus_serialno":"5F77","antplus_sn_1025":"5F77"},{"id":2066,"hrdevicenumber":2,"name":"CRAC7F0PQ286B","serialno":"CRAC7F0PQ286B","macaddr":"F1:FC:37:5A:2E:2A","antplus_serialno":"F0C7","antplus_sn_1025":"F0C7"},{"id":1280,"hrdevicenumber":3,"name":"CRA1656DTAR69","serialno":"CRA1656DTAR69","macaddr":"C5:95:A0:41:8C:BA","antplus_serialno":"5616","antplus_sn_1025":"5616"},{"id":2163,"hrdevicenumber":4,"name":"CRA57905HC112","serialno":"CRA57905HC112","macaddr":"D5:E4:0C:E4:04:A6","antplus_serialno":"9057","antplus_sn_1025":"9057"},{"id":2685,"hrdevicenumber":5,"name":"CRAF548A4T4CZ","serialno":"CRAF548A4T4CZ","macaddr":"FD:52:17:AA:D9:39","antplus_serialno":"48F5","antplus_sn_1025":"48F5"},{"id":2170,"hrdevicenumber":6,"name":"CRA6A0BG6A0LX","serialno":"CRA6A0BG6A0LX","macaddr":"DA:82:E5:C4:9B:92","antplus_serialno":"0B6A","antplus_sn_1025":"0B6A"},{"id":2826,"hrdevicenumber":7,"name":"CRA97BENTGBKB","serialno":"CRA97BENTGBKB","macaddr":"E5:EF:B5:43:0A:CA","antplus_serialno":"BE97","antplus_sn_1025":"BE97"},{"id":1275,"hrdevicenumber":8,"name":"CRACF4DKZM1Y3","serialno":"CRACF4DKZM1Y3","macaddr":"F3:D3:6E:A0:A0:4B","antplus_serialno":"4DCF","antplus_sn_1025":"4DCF"},{"id":2458,"hrdevicenumber":9,"name":"CRA65F7LVMFGS","serialno":"CRA65F7LVMFGS","macaddr":"D9:7D:F0:AD:A0:9E","antplus_serialno":"F765","antplus_sn_1025":"F765"},{"id":2839,"hrdevicenumber":10,"name":"CRA471352TL02","serialno":"CRA471352TL02","macaddr":"D1:C4:CB:DD:1F:78","antplus_serialno":"1347","antplus_sn_1025":"1347"},{"id":1283,"hrdevicenumber":11,"name":"CRA6089DEZNT4","serialno":"CRA6089DEZNT4","macaddr":"D8:22:5F:62:54:70","antplus_serialno":"8960","antplus_sn_1025":"8960"},{"id":1373,"hrdevicenumber":12,"name":"CRAE64AQYSLHY","serialno":"CRAE64AQYSLHY","macaddr":"F9:92:BA:3B:5F:F5","antplus_serialno":"4AE6","antplus_sn_1025":"4AE6"},{"id":2101,"hrdevicenumber":13,"name":"CRA695FRXYQLN","serialno":"CRA695FRXYQLN","macaddr":"DA:57:FC:81:3F:58","antplus_serialno":"5F69","antplus_sn_1025":"5F69"},{"id":1210,"hrdevicenumber":14,"name":"CRAA4FF3D2TLX","serialno":"CRAA4FF3D2TLX","macaddr":"E9:3F:C7:EC:46:BF","antplus_serialno":"FFA4","antplus_sn_1025":"FFA4"},{"id":2104,"hrdevicenumber":15,"name":"CRAC9BF71YY9J","serialno":"CRAC9BF71YY9J","macaddr":"F2:6F:D0:77:B9:C1","antplus_serialno":"BFC9","antplus_sn_1025":"BFC9"},{"id":2781,"hrdevicenumber":16,"name":"CRAD4FE2126LD","serialno":"CRAD4FE2126LD","macaddr":"F5:3F:84:BD:9E:AC","antplus_serialno":"FED4","antplus_sn_1025":"FED4"},{"id":2380,"hrdevicenumber":17,"name":"CRAF13DN87SAC","serialno":"CRAF13DN87SAC","macaddr":"FC:4F:73:E6:88:75","antplus_serialno":"3DF1","antplus_sn_1025":"3DF1"},{"id":2892,"hrdevicenumber":18,"name":"CRA5F24GRA3ZE","serialno":"CRA5F24GRA3ZE","macaddr":"D7:C9:27:1C:7B:64","antplus_serialno":"245F","antplus_sn_1025":"245F"},{"id":2714,"hrdevicenumber":19,"name":"CRA1DF7CCL6R4","serialno":"CRA1DF7CCL6R4","macaddr":"C7:7D:DC:E2:21:EF","antplus_serialno":"F71D","antplus_sn_1025":"F71D"},{"id":1305,"hrdevicenumber":20,"name":"CRAEC39B6LZF6","serialno":"CRAEC39B6LZF6","macaddr":"FB:0E:5A:20:D3:9A","antplus_serialno":"39EC","antplus_sn_1025":"39EC"},{"id":2623,"hrdevicenumber":21,"name":"CRAD1DF6SLS6M","serialno":"CRAD1DF6SLS6M","macaddr":"F4:77:CF:E0:D7:E9","antplus_serialno":"DFD1","antplus_sn_1025":"DFD1"},{"id":1995,"hrdevicenumber":22,"name":"CRA1A5B4VJMMS","serialno":"CRA1A5B4VJMMS","macaddr":"C6:96:CB:59:8B:96","antplus_serialno":"5B1A","antplus_sn_1025":"5B1A"},{"id":2144,"hrdevicenumber":23,"name":"CRA1086MR6R4Y","serialno":"CRA1086MR6R4Y","macaddr":"C4:21:B2:C4:6F:10","antplus_serialno":"8610","antplus_sn_1025":"8610"},{"id":2250,"hrdevicenumber":24,"name":"CRAF6F1T2Z0JF","serialno":"CRAF6F1T2Z0JF","macaddr":"FD:BC:7F:31:08:7E","antplus_serialno":"F1F6","antplus_sn_1025":"F1F6"},{"id":2806,"hrdevicenumber":25,"name":"CRABAE9KB2J8B","serialno":"CRABAE9KB2J8B","macaddr":"EE:BA:6D:1A:77:E5","antplus_serialno":"E9BA","antplus_sn_1025":"E9BA"},{"id":2837,"hrdevicenumber":26,"name":"CRAE0C12M2MF6","serialno":"CRAE0C12M2MF6","macaddr":"F8:30:46:27:C7:E3","antplus_serialno":"C1E0","antplus_sn_1025":"C1E0"},{"id":2898,"hrdevicenumber":27,"name":"CRA3AADS9K56Y","serialno":"CRA3AADS9K56Y","macaddr":"CE:AB:7D:53:7E:40","antplus_serialno":"AD3A","antplus_sn_1025":"AD3A"},{"id":2247,"hrdevicenumber":28,"name":"CRA5B24F22MTW","serialno":"CRA5B24F22MTW","macaddr":"D6:C9:23:23:08:20","antplus_serialno":"245B","antplus_sn_1025":"245B"},{"id":2243,"hrdevicenumber":29,"name":"CRA63BCHFD90Y","serialno":"CRA63BCHFD90Y","macaddr":"D8:EF:28:BE:69:95","antplus_serialno":"BC63","antplus_sn_1025":"BC63"},{"id":2036,"hrdevicenumber":30,"name":"CRAF022T8R8G0","serialno":"CRAF022T8R8G0","macaddr":"FC:08:BF:99:E6:94","antplus_serialno":"22F0","antplus_sn_1025":"22F0"},{"id":2197,"hrdevicenumber":31,"name":"CRA10997L7BJL","serialno":"CRA10997L7BJL","macaddr":"C4:26:51:C2:0E:53","antplus_serialno":"9910","antplus_sn_1025":"9910"},{"id":2786,"hrdevicenumber":32,"name":"CRA807633X0JA","serialno":"CRA807633X0JA","macaddr":"E0:1D:87:46:39:20","antplus_serialno":"7680","antplus_sn_1025":"7680"},{"id":1370,"hrdevicenumber":33,"name":"CRA89B1909MC3","serialno":"CRA89B1909MC3","macaddr":"E2:6C:55:03:B7:F6","antplus_serialno":"B189","antplus_sn_1025":"B189"},{"id":2815,"hrdevicenumber":34,"name":"CRA34B1HD16GK","serialno":"CRA34B1HD16GK","macaddr":"CD:2C:68:93:99:48","antplus_serialno":"B134","antplus_sn_1025":"B134"},{"id":993,"hrdevicenumber":35,"name":"CRA319263M9DX","serialno":"CRA319263M9DX","macaddr":"CC:64:8E:40:E9:52","antplus_serialno":"9231","antplus_sn_1025":"9231"},{"id":1356,"hrdevicenumber":36,"name":"CRAD24BB750L3","serialno":"CRAD24BB750L3","macaddr":"F4:92:DA:2A:2A:EE","antplus_serialno":"4BD2","antplus_sn_1025":"4BD2"},{"id":2276,"hrdevicenumber":37,"name":"CRAF73D24GX89","serialno":"CRAF73D24GX89","macaddr":"FD:CF:44:FB:FA:05","antplus_serialno":"3DF7","antplus_sn_1025":"3DF7"},{"id":2785,"hrdevicenumber":38,"name":"CRA41A0RHF3P8","serialno":"CRA41A0RHF3P8","macaddr":"D0:68:3B:8C:E1:BB","antplus_serialno":"A041","antplus_sn_1025":"A041"},{"id":2817,"hrdevicenumber":39,"name":"CRA1F0FLCL90X","serialno":"CRA1F0FLCL90X","macaddr":"C7:C3:EF:8B:70:9B","antplus_serialno":"0F1F","antplus_sn_1025":"0F1F"},{"id":2538,"hrdevicenumber":40,"name":"CRAA23DP5HH71","serialno":"CRAA23DP5HH71","macaddr":"E8:8F:76:0A:BC:A6","antplus_serialno":"3DA2","antplus_sn_1025":"3DA2"},{"id":815,"hrdevicenumber":41,"name":"CRAC348HGWPTF","serialno":"CRAC348HGWPTF","macaddr":"F0:D2:28:D9:87:1F","antplus_serialno":"48C3","antplus_sn_1025":"48C3"},{"id":1294,"hrdevicenumber":42,"name":"CRAAA97FN6L3T","serialno":"CRAAA97FN6L3T","macaddr":"EA:A5:E4:8F:1C:5D","antplus_serialno":"97AA","antplus_sn_1025":"97AA"},{"id":942,"hrdevicenumber":43,"name":"CRA5746G0DFCS","serialno":"CRA5746G0DFCS","macaddr":"D5:D1:A5:59:EC:72","antplus_serialno":"4657","antplus_sn_1025":"4657"},{"id":1122,"hrdevicenumber":44,"name":"CRAE152NQDACT","serialno":"CRAE152NQDACT","macaddr":"F8:54:B5:0B:17:0C","antplus_serialno":"52E1","antplus_sn_1025":"52E1"},{"id":2128,"hrdevicenumber":45,"name":"CRA2FB1RQCXL2","serialno":"CRA2FB1RQCXL2","macaddr":"CB:EC:7C:0A:5A:91","antplus_serialno":"B12F","antplus_sn_1025":"B12F"},{"id":2929,"hrdevicenumber":46,"name":"CRA1773ANSVSH","serialno":"CRA1773ANSVSH","macaddr":"C5:DC:D8:F0:6D:C1","antplus_serialno":"7317","antplus_sn_1025":"7317"},{"id":817,"hrdevicenumber":47,"name":"CRA7E0FD06Z1Z","serialno":"CRA7E0FD06Z1Z","macaddr":"DF:83:DE:56:E2:D4","antplus_serialno":"0F7E","antplus_sn_1025":"0F7E"},{"id":764,"hrdevicenumber":48,"name":"CRA74384DXSAY","serialno":"CRA74384DXSAY","macaddr":"DD:0E:0A:50:C4:72","antplus_serialno":"3874","antplus_sn_1025":"3874"},{"id":1347,"hrdevicenumber":49,"name":"CRAC24ABQB39C","serialno":"CRAC24ABQB39C","macaddr":"F0:92:9B:61:21:26","antplus_serialno":"4AC2","antplus_sn_1025":"4AC2"}]
     */

    public int count;
    public List<HrdevicesBean> hrdevices;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HrdevicesBean> getHrdevices() {
        return hrdevices;
    }

    public void setHrdevices(List<HrdevicesBean> hrdevices) {
        this.hrdevices = hrdevices;
    }

    public static class HrdevicesBean implements Serializable{
        /**
         * id : 2200
         * hrdevicenumber : 1
         * name : CRA775FFMCEWD
         * serialno : CRA775FFMCEWD
         * macaddr : DD:D7:E4:80:37:E8
         * antplus_serialno : 5F77
         * antplus_sn_1025 : 5F77
         */

        public int id;
        public int hrdevicenumber;
        public String name;
        public String serialno;
        public String macaddr;
        public String antplus_serialno;
        public String antplus_sn_1025;
        public boolean select;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHrdevicenumber() {
            return hrdevicenumber;
        }

        public void setHrdevicenumber(int hrdevicenumber) {
            this.hrdevicenumber = hrdevicenumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSerialno() {
            return serialno;
        }

        public void setSerialno(String serialno) {
            this.serialno = serialno;
        }

        public String getMacaddr() {
            return macaddr;
        }

        public void setMacaddr(String macaddr) {
            this.macaddr = macaddr;
        }

        public String getAntplus_serialno() {
            return antplus_serialno;
        }

        public void setAntplus_serialno(String antplus_serialno) {
            this.antplus_serialno = antplus_serialno;
        }

        public String getAntplus_sn_1025() {
            return antplus_sn_1025;
        }

        public void setAntplus_sn_1025(String antplus_sn_1025) {
            this.antplus_sn_1025 = antplus_sn_1025;
        }
    }
}
