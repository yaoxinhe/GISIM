package com.example.bawei.basemodel.chartmodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/8 07:52
 * @Email 1151403054@qq.com
 */
public class MyAddressBean {

    /**
     * code : 200
     * data : {"id":3,"useraccount":"1","lon":3.1,"lat":4.1,"ctime":"2020/1/6 18:38:58"}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 3
         * useraccount : 1
         * lon : 3.1
         * lat : 4.1
         * ctime : 2020/1/6 18:38:58
         */

        private int id;
        private String useraccount;
        private double lon;
        private double lat;
        private String ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUseraccount() {
            return useraccount;
        }

        public void setUseraccount(String useraccount) {
            this.useraccount = useraccount;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}
