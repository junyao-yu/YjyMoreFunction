package com.yjymorefunctions.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.improve.utility.utils.LogUtil;
import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Auth：yujunyao
 * Since: 2016/8/31 14:27
 * Email：yujunyao@yonglibao.com
 *
 * http://gank.io/post/560e15be2dca930e00da1083
 */
public class RxJavaAndroidActivity extends BaseActivity {
    private static final String TAG = "RxJavaAndroidActivity";
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.img2)
    ImageView img2;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rxjava_android);
        ButterKnife.bind(this);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                LogUtil.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                LogUtil.i(TAG, s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtil.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                LogUtil.i(TAG, s);
            }
        };

//        Observable observable = Observable.create(new Observable.OnSubscribe<String>(){
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });

        String[] words = new String[]{"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);

//        Observable observable = Observable.just("Hello", "Hi", "Aloha");

//        observable.subscribe(observer);
//        observable.subscribe(subscriber);

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("onNextAction--->", s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.i("onErrorAction--->", throwable.getMessage());
            }
        };
        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                Log.i("onCompleteAction--->", "");
            }
        };
//        observable.subscribe(onNextAction);
//        observable.subscribe(onNextAction, onErrorAction);
        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);


        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Drawable drawable =  getDrawable(R.drawable.free_success_image);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())//// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())//指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxJavaAndroidActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable drawable) {
                img.setImageDrawable(drawable);
            }
        });

        /**
         * map() 方法将参数中的 String 对象转换成一个 Bitmap 对象后返回，而在经过 map() 方法后，
         * 事件的参数类型也由 String 转为了 Bitmap。这种直接变换对象并返回的，是最常见的也最容易理解的变换
         */
        Observable.just(R.drawable.pic_teacher)
                .map(new Func1<Integer, Bitmap>() {

                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapFactory.decodeResource(getResources(), integer);
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                img2.setImageBitmap(bitmap);
            }
        });

        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<5;i++) {
            Student student = new Student();
            student.cause = new Cause("yujunyao", 28);
            studentList.add(student);
        }
        Subscriber<Cause> subscriberStu = new Subscriber<Cause>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Cause cause) {
                Log.i("subscriberStu--->", cause.name + "|" + cause.age);
            }
        };
        Observable.from(studentList).flatMap(new Func1<Student, Observable<Cause>>() {
            @Override
            public Observable<Cause> call(Student student) {
                return Observable.from(new Cause[]{student.cause});
            }
        }).subscribe(subscriberStu);

    }

    @Override
    protected void onClickView(View view) {

    }

    public class Student {

        public Cause cause;

    }

    public class Cause {

        public Cause(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String name;
        public int age;
    }
}
