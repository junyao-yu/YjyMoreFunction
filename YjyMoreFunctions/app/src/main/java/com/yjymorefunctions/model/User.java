package com.yjymorefunctions.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Auth：yujunyao
 * Since: 2016/10/18 16:41
 * Email：yujunyao@yonglibao.com
 *
 * http://www.cnblogs.com/whoislcj/p/5651396.html
 * http://blog.csdn.net/kongxingxing/article/details/52052331
 * http://greenrobot.org/greendao/documentation/updating-to-greendao-3-and-annotations/
 * http://blog.csdn.net/shuitawuhen/article/details/52766472
 */
@Entity(nameInDb = "user_db")
public class User {

    @Id
    @Property(nameInDb="user_id")
    private long id;

    private String name;

    private int age;

    @Generated(hash = 446251977)
    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
