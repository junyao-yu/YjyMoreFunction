package com.yjymorefunctions.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Auth：yujunyao
 * Since: 2016/10/19 17:11
 * Email：yujunyao@yonglibao.com
 */
@Entity(nameInDb = "teacher")
public class Teacher {

    @Id(autoincrement = true)
    @Property(nameInDb = "autoId")
    private long id;

    private String subject;

    private int salary;

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 30419342)
    public Teacher(long id, String subject, int salary) {
        this.id = id;
        this.subject = subject;
        this.salary = salary;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }
    

}
