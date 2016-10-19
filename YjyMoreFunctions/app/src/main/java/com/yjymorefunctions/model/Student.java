package com.yjymorefunctions.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Auth：yujunyao
 * Since: 2016/10/19 16:42
 * Email：yujunyao@yonglibao.com
 */
@Entity(nameInDb = "student")
public class Student {

    @Id(autoincrement = true)
    private long id;

    private String name;

    private int age;

    private String birthday;

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Generated(hash = 1233361681)
    public Student(long id, String name, int age, String birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

}
