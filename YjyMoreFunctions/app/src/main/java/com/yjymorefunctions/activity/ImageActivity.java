package com.yjymorefunctions.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import java.io.File;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2016/7/28 10:56
 * Email：yujunyao@yonglibao.com
 *
 * http://blog.csdn.net/fancylovejava/article/details/43760119
 */
public class ImageActivity extends BaseActivity {
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_image);
//        ButterKnife.bind(this);

//        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(new File(getStoragePath()))).build();
//Picasso.setSingletonInstance(picasso);
//

        //picasso可以对多个加载请求设置相同的tag
        Picasso.with(this)
                .load("http://f.hiphotos.baidu.com/zhidao/pic/item/a8ec8a13632762d0aac65c45a2ec08fa503dc654.jpg")
                .transform(transformation)//可以自定义图片显示的大小
                .config(Bitmap.Config.RGB_565)//比ARGB_8888图片内存占用少一半
                .tag(this)
                //NO_CACHE是指图片加载时放弃在内存缓存中查找，NO_STORE是指图片加载完不缓存在内存中。但可以从磁盘查找如有，无网络也可显示
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(image)

        ;

//        String a = "哇哈哈哈[em:5]aa8888[em:7]";
//        String r = "\\[em:(\\d+)\\]";
//        a = a.replaceAll(r, "$1.gif");
//        System.out.println(a);// 哇哈哈哈5.gifaa88887.gif

//        String regexEmotion = "\\/+([\u4e00-\u9fa5]\\w)";//或者  \/+([^\x00-\xff]\w)//限定死斜杠后面两个中文
        String regexEmotion =  "\\/+([\u4e00-\u9fa5]{1,5})";
        String b = "我们今晚去吃鱼/你/你好/你真好/你真真好/你真真真好";
        b = b.replaceAll(regexEmotion, "$1.png");
        System.out.println(b);

        text.setText("纯代码写的框");
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius((int) getResources().getDimension(R.dimen.dp_5));
        drawable.setStroke((int) getResources().getDimension(R.dimen.dp_1), Color.parseColor("#cccccc"));
        drawable.setColor(Color.parseColor("#eeeeee"));
        text.setBackgroundDrawable(drawable);
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        GradientDrawable drawable = new GradientDrawable();
                        drawable.setCornerRadius((int) getResources().getDimension(R.dimen.dp_5));
                        drawable.setStroke((int) getResources().getDimension(R.dimen.dp_1), Color.parseColor("#aa0000"));
                        drawable.setColor(Color.parseColor("#00aa00"));
                        text.setBackgroundDrawable(drawable);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                            GradientDrawable drawable = new GradientDrawable();
                            drawable.setCornerRadius((int) getResources().getDimension(R.dimen.dp_5));
                            drawable.setStroke((int) getResources().getDimension(R.dimen.dp_1), Color.parseColor("#cccccc"));
                            drawable.setColor(Color.parseColor("#eeeeee"));
                            text.setBackgroundDrawable(drawable);
                            break;
                        }
                }
                return true;
            }
        });
    }

    public static String getStoragePath() {
        String storagePath = "";
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if(sdCardExist) {
            storagePath = Environment.getExternalStorageDirectory().getAbsolutePath() +File.separator+ "aaaaa";
            File file = new File(storagePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return storagePath;
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_image;
    }

    Transformation transformation = new Transformation() {
        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth = 600;
            if(source.getWidth()==0){
                return source;
            }
            //如果图片小于设置的宽度，则返回原图
            if(source.getWidth()<targetWidth){
                return source;
            }else{
                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight != 0 && targetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();//必须回收原图
                    }
                    return result;
                } else {
                    return source;
                }
            }
        }
        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };

    //列表滑动的时候
//    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
//        {
//            if (newState == RecyclerView.SCROLL_STATE_IDLE)
//            {
//                Picasso.with(context).resumeTag(tag);
//            }
//            else
//            {
//                Picasso.with(context).pauseTag(tag);
//            }
//        }
//    });

}
