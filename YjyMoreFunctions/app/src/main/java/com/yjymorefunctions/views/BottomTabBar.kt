package com.yjymorefunctions.views

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.yjymorefunctions.R
import com.yjymorefunctions.utils.DbHelper

/**
 * Auth：yujunyao
 * Since: 2018/3/12 下午5:50
 * Email：yujunyao@ylb.net
 */

class BottomTabBar : LinearLayout, View.OnClickListener{

    interface SelectedCallBack {
        fun selectOther(tag: Int)
        fun selectRepeat(tag: Int)
    }

    private val count = 4
    private var selectTag: Int = 0
    private val diff = 500L//300毫秒
    private var lastClickTime: Long = 0L

    private val items: MutableList<RelativeLayout> = ArrayList()
    private val images: MutableList<ImageView> = ArrayList()
    private val labels: MutableList<TextView> = ArrayList()
    private var callBack: SelectedCallBack? = null

    constructor(ctx : Context) : this(ctx, null)

    constructor(ctx : Context, attrs: AttributeSet?) : this(ctx, attrs, 0)

    constructor(ctx : Context, attrs : AttributeSet?, defStyleAttr : Int) : super(ctx, attrs, defStyleAttr) {
        initView(ctx)
    }

    private fun initView(ctx: Context) {
        orientation = LinearLayout.HORIZONTAL

        var  params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DbHelper.dip2px(ctx, 50))
        params.width = DbHelper.screenWidth(ctx)[0] / count

        for (i in 0..count) {
            var item = LayoutInflater.from(ctx).inflate(R.layout.item_tabbar, this, false)
            item?.layoutParams = params
            items.add(item as RelativeLayout)

            var image = item.findViewById(R.id.image) as ImageView
            image.setImageResource(R.mipmap.ic_launcher)
            images.add(image)

            var title = item.findViewById(R.id.title) as TextView
            title.text = "测试"
            labels.add(title)

            item.tag = i
            item.setOnClickListener(this)
            addView(item)
        }

    }

    fun setData(callBack: SelectedCallBack?) {
        this.callBack = callBack
    }

    override fun onClick(v: View?) {
        var tag = v?.tag as Int
        if (isFastDoubleClick(tag, diff)) {
            return
        }
        if (tag == selectTag) {
            //TODO
            callBack?.selectRepeat(tag)
        } else {
            selectTag = tag
            for (image in images) {
                image.setImageResource(R.mipmap.ic_launcher)
            }
            images[tag].setImageResource(R.mipmap.ic_launcher_temp)
            startAnimation(images[tag])
            callBack?.selectOther(tag)
        }
    }

    private fun isFastDoubleClick(tag: Int, diff: Long): Boolean {
        val currTime = System.currentTimeMillis()
        val diffTime = currTime - lastClickTime
        var isFast = tag == selectTag && lastClickTime > 0 && diffTime < diff
        if (isFast) return true
        lastClickTime = currTime
        return false
    }


    private fun startAnimation(view: View) {
        var animatorSet = AnimatorSet()
        val animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f)
        val animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f)
        val animatorScaleXBack = ObjectAnimator.ofFloat(view, "scaleX", 1.2f, 1f)
        val animatorScaleYBack = ObjectAnimator.ofFloat(view, "scaleY", 1.2f, 1f)

        animatorSet.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                animatorSet.playTogether(animatorScaleXBack, animatorScaleYBack)
                animatorSet.start()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })

        animatorSet.duration = 100
        animatorSet.playTogether(animatorScaleX, animatorScaleY)
        animatorSet.start()
    }
}
