package travel.nanjing.com.travel.business.api.helper;

/**
 * Created by wang on 2018/3/12.
 */

public class TrainQuery {
    /**
     * 出发站
     */
    private String startStation;
    /**
     * 终点站
     */
    private String endStation;
    /**
     * 出发时间 yyyy-mm-dd
     */
    private String date;

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
