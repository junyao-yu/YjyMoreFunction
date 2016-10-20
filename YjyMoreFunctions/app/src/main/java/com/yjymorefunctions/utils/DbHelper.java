package com.yjymorefunctions.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yjymorefunctions.model.DaoMaster;
import com.yjymorefunctions.model.DaoSession;
import com.yjymorefunctions.model.Student;
import com.yjymorefunctions.model.StudentDao;
import com.yjymorefunctions.model.Teacher;
import com.yjymorefunctions.model.TeacherDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Auth：yujunyao
 * Since: 2016/10/19 16:41
 * Email：yujunyao@yonglibao.com
 */
public class DbHelper extends DaoMaster.OpenHelper {

    private static final String DB_NAME = "test_db";
    public static volatile DbHelper instance;
    private static final String KEY = "12345678912345678912345678912345";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        DaoMaster.createAllTables(db, true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if(oldVersion < newVersion) {
            //db upgrade
            if(newVersion < 6) {
                MigrationHelper.migrate(db, StudentDao.class, TeacherDao.class);
            }
        }
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if(oldVersion < newVersion) {
            if(newVersion == 14) {
                TeacherDao.createTable(db, false);
            }
        }
    }

    public static DbHelper getInstance(Context context) {
        if(instance == null) {
            synchronized (DbHelper.class) {
                if(instance == null) {
                    instance = new DbHelper(context, DB_NAME, null);
                }
            }
        }
        return instance;
    }

    /**
     * 插入一条记录
     *
     * @param student
     */
    public void insertStudent(Student student) {
//        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
//        DaoSession daoSession = daoMaster.newSession();
//        StudentDao userDao = daoSession.getStudentDao();
//        userDao.insert(student);
        String insertString = "INSERT INTO student (NAME, AGE, BIRTHDAY) VALUES (?, ?, ?)";
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        daoSession.getDatabase().execSQL(insertString, new Object[]{student.getName(), student.getAge(), student.getBirthday()});
    }

    /**
     * 查询用户列表
     */
    public List<Student> queryStudentList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        StudentDao userDao = daoSession.getStudentDao();
        QueryBuilder<Student> qb = userDao.queryBuilder();
        List<Student> list = qb.list();
        return list;
    }

    public void insertTeacher(Teacher teacher) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TeacherDao userDao = daoSession.getTeacherDao();
        userDao.insert(teacher);
    }

    public List<Teacher> queryTeacherList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TeacherDao userDao = daoSession.getTeacherDao();
        Log.d("可以取数据",userDao.loadAll().toString());
        QueryBuilder<Teacher> qb = userDao.queryBuilder();
        List<Teacher> list = qb.list();
        return list;
    }

}
