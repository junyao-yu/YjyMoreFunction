package com.yjymorefunctions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Auth：yujunyao
 * Since: 2016/7/14 10:02
 * Email：yujunyao@yonglibao.com
 */
public class TestModel implements Serializable {

    private boolean status;

    private List<Info> tngou = new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Info> getTngou() {
        return tngou;
    }

    public void setTngou(List<Info> tngou) {
        this.tngou = tngou;
    }

    private static class Info implements Serializable {
        private String description;
        private int id;
        private String keywords;
        private String name;
        private int seq;
        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
