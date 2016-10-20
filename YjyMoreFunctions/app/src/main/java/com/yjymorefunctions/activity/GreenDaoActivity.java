package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.model.Student;
import com.yjymorefunctions.model.Teacher;
import com.yjymorefunctions.utils.DbHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/10/18 16:53
 * Email：yujunyao@yonglibao.com
 * http://www.cnblogs.com/dsxniubility/archive/2016/07/28/5699543.html   GreenDao3.0新特性解析（配置、注解、加密）
 *
 * http://blog.csdn.net/ghsy121/article/details/52132148  数据库增加表与更新字段语法 GREENDAO
 *
 * http://blog.csdn.net/HHcoco/article/details/52384125?locationNum=2  GreenDao Encrypt
 *
 *
 *
 */
public class GreenDaoActivity extends BaseActivity {
    @Bind(R.id.display)
    TextView display;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.insert, R.id.insert_array, R.id.delete, R.id.update, R.id.query})
    @Override
    protected void onClickView(View view) {
        String content = "";
        switch (view.getId()) {
            case R.id.insert:
//                DbHelper.getInstance(this).insertStudent(getStudent());

                DbHelper.getInstance(this).insertTeacher(getTeacher());
                break;
            case R.id.query:
//                List<Student> studentList = DbHelper.getInstance(this).queryStudentList();
//                for(int i=0;i<studentList.size();i++) {
//                    content += studentList.get(i).getId() + "," +  studentList.get(i).getName() + "," + studentList.get(i).getAge() + "," + studentList.get(i).getBirthday() + "\n";
//                }
//                display.setText(content);

                List<Teacher> studentList = DbHelper.getInstance(this).queryTeacherList();
                for(int i=0;i<studentList.size();i++) {
                    content += studentList.get(i).getId() + "," +  studentList.get(i).getSubject() + "," + studentList.get(i).getSalary() +  "\n";
                }
                display.setText(content);
                break;
//            case R.id.insert:
//                DBManager.getInstance(this).insertUser(new User(1, "yjy", 28));
//                break;
//            case R.id.insert_array:
//                List<User> list = new ArrayList<>();
//                for(int i=2;i<7;i++) {
//                    list.add(new User(i, "lalala", 27));
//                }
//                DBManager.getInstance(this).insertUserList(list);
//                break;
//            case R.id.delete:
////                DBManager.getInstance(this).deleteUser(new User(4, "lalala", 27));
//                DBManager.getInstance(this).deleteCondition(4);
//                break;
//            case R.id.update:
//                DBManager.getInstance(this).updateUser(new User(3, "hahaha", 27));
//                break;
//            case R.id.query:
//                List<User> userList = DBManager.getInstance(this).queryUserList();
//                for(int i=0;i<userList.size();i++) {
//                    content += userList.get(i).getId() + "," +  userList.get(i).getName() + "," + userList.get(i).getAge() + "\n";
//                }
//                display.setText(content);
//                break;
        }
    }

    private Teacher getTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSubject("英语");
        teacher.setSalary(6500);
        return teacher;
    }

    private Student getStudent() {
        Student student = new Student();
        student.setName("sjb");
        student.setAge(27);
        student.setBirthday("1125");
        return student;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_greendao;
    }


}
