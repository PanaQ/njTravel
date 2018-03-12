package travel.nanjing.com.travel.business.api.model.bo;

import java.util.List;

/**
 * Created by Jingly on 2018/3/10.
 */
public class TrainListBo {

    /**
     * reason : 查询成功
     * result : {"list":[{"train_no":"D2198","train_type":"D","start_station":"汉口","start_station_type":"始","end_station":"杭州东","end_station_type":"过","start_time":"06:43","end_time":"11:14","run_time":"4小时31分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"G584","train_type":"G","start_station":"武汉","start_station_type":"始","end_station":"杭州东","end_station_type":"过","start_time":"09:00","end_time":"13:59","run_time":"4小时59分","price_list":[{"price_type":"二等座","price":"286.0"},{"price_type":"一等座","price":"403.5"},{"price_type":"商务座","price":"876.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D2190","train_type":"D","start_station":"汉口","start_station_type":"始","end_station":"杭州东","end_station_type":"终","start_time":"09:12","end_time":"14:31","run_time":"5小时19分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D2194","train_type":"D","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"终","start_time":"10:30","end_time":"15:50","run_time":"5小时20分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"G592","train_type":"G","start_station":"汉口","start_station_type":"始","end_station":"杭州东","end_station_type":"过","start_time":"12:37","end_time":"17:26","run_time":"4小时49分","price_list":[{"price_type":"二等座","price":"283.5"},{"price_type":"一等座","price":"396.0"},{"price_type":"商务座","price":"866.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D658","train_type":"D","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"14:04","end_time":"19:29","run_time":"5小时25分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"G596","train_type":"G","start_station":"武汉","start_station_type":"始","end_station":"杭州东","end_station_type":"终","start_time":"14:20","end_time":"19:22","run_time":"5小时2分","price_list":[{"price_type":"二等座","price":"286.0"},{"price_type":"一等座","price":"403.5"},{"price_type":"商务座","price":"876.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D2248","train_type":"D","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"终","start_time":"15:25","end_time":"20:27","run_time":"5小时2分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D2224","train_type":"D","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"终","start_time":"16:47","end_time":"22:08","run_time":"5小时21分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"K531","train_type":"K","start_station":"武昌","start_station_type":"过","end_station":"杭州","end_station_type":"终","start_time":"17:07","end_time":"05:04","run_time":"11小时57分","price_list":[{"price_type":"硬座","price":"115.0"},{"price_type":"硬卧","price":"231"},{"price_type":"软卧","price":"353.5"},{"price_type":"无座","price":"115.0"}],"run_distance":"","m_chaxun_url":""},{"train_no":"D2264","train_type":"D","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"终","start_time":"18:00","end_time":"22:46","run_time":"4小时46分","price_list":[{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"K1125","train_type":"K","start_station":"武昌","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"18:13","end_time":"05:55","run_time":"11小时42分","price_list":[{"price_type":"硬座","price":"115.0"},{"price_type":"硬卧","price":"214"},{"price_type":"软卧","price":"353.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"K121","train_type":"K","start_station":"武昌","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"18:21","end_time":"09:03","run_time":"14小时42分","price_list":[{"price_type":"硬座","price":"128.5"},{"price_type":"硬卧","price":"237.5"},{"price_type":"软卧","price":"394.5"},{"price_type":"无座","price":"128.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"K353","train_type":"K","start_station":"武昌","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"19:48","end_time":"10:01","run_time":"14小时13分","price_list":[{"price_type":"硬座","price":"128.5"},{"price_type":"硬卧","price":"257"},{"price_type":"软卧","price":"394.5"},{"price_type":"无座","price":"128.5"}],"run_distance":"","m_chaxun_url":""},{"train_no":"K254","train_type":"K","start_station":"汉口","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"19:56","end_time":"10:37","run_time":"14小时41分","price_list":[{"price_type":"硬座","price":"141.5"},{"price_type":"硬卧","price":"261.5"},{"price_type":"软卧","price":"432"}],"run_distance":"","m_chaxun_url":""},{"train_no":"Z45","train_type":"Z","start_station":"武昌","start_station_type":"始","end_station":"杭州","end_station_type":"终","start_time":"22:40","end_time":"07:03","run_time":"8小时23分","price_list":[{"price_type":"硬座","price":"115.0"},{"price_type":"硬卧","price":"214"},{"price_type":"软卧","price":"325"},{"price_type":"无座","price":"115.0"}],"run_distance":"","m_chaxun_url":""},{"train_no":"Z255","train_type":"Z","start_station":"武昌","start_station_type":"过","end_station":"杭州东","end_station_type":"过","start_time":"23:17","end_time":"07:59","run_time":"8小时42分","price_list":[{"price_type":"硬卧","price":"257"},{"price_type":"软卧","price":"394.5"}],"run_distance":"","m_chaxun_url":""}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * train_no : D2198
             * train_type : D
             * start_station : 汉口
             * start_station_type : 始
             * end_station : 杭州东
             * end_station_type : 过
             * start_time : 06:43
             * end_time : 11:14
             * run_time : 4小时31分
             * price_list : [{"price_type":"二等座","price":"245.0"},{"price_type":"一等座","price":"324.5"}]
             * run_distance :
             * m_chaxun_url :
             */

            private String train_no;
            private String train_type;
            private String start_station;
            private String start_station_type;
            private String end_station;
            private String end_station_type;
            private String start_time;
            private String end_time;
            private String run_time;
            private String run_distance;
            private String m_chaxun_url;
            private List<PriceListBean> price_list;

            public String getTrain_no() {
                return train_no;
            }

            public void setTrain_no(String train_no) {
                this.train_no = train_no;
            }

            public String getTrain_type() {
                return train_type;
            }

            public void setTrain_type(String train_type) {
                this.train_type = train_type;
            }

            public String getStart_station() {
                return start_station;
            }

            public void setStart_station(String start_station) {
                this.start_station = start_station;
            }

            public String getStart_station_type() {
                return start_station_type;
            }

            public void setStart_station_type(String start_station_type) {
                this.start_station_type = start_station_type;
            }

            public String getEnd_station() {
                return end_station;
            }

            public void setEnd_station(String end_station) {
                this.end_station = end_station;
            }

            public String getEnd_station_type() {
                return end_station_type;
            }

            public void setEnd_station_type(String end_station_type) {
                this.end_station_type = end_station_type;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getRun_time() {
                return run_time;
            }

            public void setRun_time(String run_time) {
                this.run_time = run_time;
            }

            public String getRun_distance() {
                return run_distance;
            }

            public void setRun_distance(String run_distance) {
                this.run_distance = run_distance;
            }

            public String getM_chaxun_url() {
                return m_chaxun_url;
            }

            public void setM_chaxun_url(String m_chaxun_url) {
                this.m_chaxun_url = m_chaxun_url;
            }

            public List<PriceListBean> getPrice_list() {
                return price_list;
            }

            public void setPrice_list(List<PriceListBean> price_list) {
                this.price_list = price_list;
            }

            public static class PriceListBean {
                /**
                 * price_type : 二等座
                 * price : 245.0
                 */

                private String price_type;
                private String price;

                public String getPrice_type() {
                    return price_type;
                }

                public void setPrice_type(String price_type) {
                    this.price_type = price_type;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
