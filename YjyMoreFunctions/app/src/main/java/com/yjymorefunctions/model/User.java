package com.yjymorefunctions.model;

import javax.inject.Inject;

/**
 * Auth：yujunyao
 * Since: 2017/11/14 下午2:02
 * Email：yujunyao@ylb.net
 */


public class User {

    private String name = "yujunyao";

    @Inject
    public User(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
