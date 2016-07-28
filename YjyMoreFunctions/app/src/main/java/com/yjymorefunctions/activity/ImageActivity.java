package com.yjymorefunctions.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        //picasso可以对多个加载请求设置相同的tag
        Picasso.with(this)
                .load("http://f.hiphotos.baidu.com/zhidao/pic/item/a8ec8a13632762d0aac65c45a2ec08fa503dc654.jpg")
                .transform(transformation)//可以自定义图片显示的大小
                .config(Bitmap.Config.RGB_565)//比ARGB_8888图片内存占用少一半
                .tag(this)
                //NO_CACHE是指图片加载时放弃在内存缓存中查找，NO_STORE是指图片加载完不缓存在内存中。但可以从磁盘查找如有，无网络也可显示
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(image)
        ;

    }

    @Override
    protected void onClickView(View view) {

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
