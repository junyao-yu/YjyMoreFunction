package com.yjymorefunctions.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth：yujunyao
 * Since: 2016/11/30 17:42
 * Email：yujunyao@yonglibao.com
 */

public class InvestList {

    public Data data = new Data();

    public static class Data {
        public int current_page;
        public int next_page;
        public int total_page;

        public List<Info> list = new ArrayList<>();
    }

    public static class Info {
        public String date;

        public List<DateList> date_list = new ArrayList<>();
    }

    public static class DateList {
        public String invest_id;
        public int type;
        public String money;
        public String rate;
        public String title;
        public int status;
        public String date;
        public String tempDate;//日期(YYYYMMDD)
        public DateList(String tempDate) {
            this.tempDate = tempDate;
        }
    }

}
