package com.example.bawei.chartmodule.bean;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/7 19:28
 * @Email 1151403054@qq.com
 */
public class LocaltionEntity {

    /**
     * id : 1
     * useraccount : 2
     * lon : 3.1
     * lat : 4.1
     * ctime : 5
     */

    private int id;
    private String useraccount;
    private double lon;
    private double lat;
    private String ctime;

    public LocaltionEntity(int id, String useraccount, double lon, double lat, String ctime) {
        this.id = id;
        this.useraccount = useraccount;
        this.lon = lon;
        this.lat = lat;
        this.ctime = ctime;
    }

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
