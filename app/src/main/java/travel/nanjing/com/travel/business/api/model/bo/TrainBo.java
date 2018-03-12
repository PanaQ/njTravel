package travel.nanjing.com.travel.business.api.model.bo;

public class TrainBo {

    /**
     * 列次名称
     */
    private String trainNo;

    /**
     * 起点站
     */
    private String startStation;

    /**
     * 终点站
     */
    private String endStation;

    /**
     * 发车时间
     */
    private String startTime;

    /**
     * 到达时间
     */
    private String endTime;

    /**
     * 运行时间
     */
    private String runTime;


    /**
     * 价格
     */
    private double price;

    /**
     * 获取列次名称
     *
     * @return train_no - 列次名称
     */
    public String getTrainNo() {
        return trainNo;
    }

    /**
     * 设置列次名称
     *
     * @param trainNo 列次名称
     */
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo == null ? null : trainNo.trim();
    }

    /**
     * 获取起点站
     *
     * @return start_station - 起点站
     */
    public String getStartStation() {
        return startStation;
    }

    /**
     * 设置起点站
     *
     * @param startStation 起点站
     */
    public void setStartStation(String startStation) {
        this.startStation = startStation == null ? null : startStation.trim();
    }

    /**
     * 获取终点站
     *
     * @return end_station - 终点站
     */
    public String getEndStation() {
        return endStation;
    }

    /**
     * 设置终点站
     *
     * @param endStation 终点站
     */
    public void setEndStation(String endStation) {
        this.endStation = endStation == null ? null : endStation.trim();
    }

    /**
     * 获取发车时间
     *
     * @return start_time - 发车时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置发车时间
     *
     * @param startTime 发车时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * 获取到达时间
     *
     * @return end_time - 到达时间
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置到达时间
     *
     * @param endTime 到达时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    /**
     * 获取运行时间
     *
     * @return run_time - 运行时间
     */
    public String getRunTime() {
        return runTime;
    }

    /**
     * 设置运行时间
     *
     * @param runTime 运行时间
     */
    public void setRunTime(String runTime) {
        this.runTime = runTime == null ? null : runTime.trim();
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(double price) {
        this.price = price;
    }
}