package com.yjymorefunctions.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Auth：yujunyao
 * Since: 2016/10/19 17:11
 * Email：yujunyao@yonglibao.com
 */
@Entity(nameInDb = "teacher")
public class Teacher {

    @Id(autoincrement = true)
    @Property(nameInDb = "autoId")
    private Long id = null;//这个要设置Long且为null,否则报错UNIQUE constraint failed: teacher.autoId

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 193902963)
    public Teacher(Long id, String subject, int salary) {
        this.id = id;
        this.subject = subject;
        this.salary = salary;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }


}
