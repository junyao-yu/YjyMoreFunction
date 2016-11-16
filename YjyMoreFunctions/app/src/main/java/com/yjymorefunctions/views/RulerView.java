package com.yjymorefunctions.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.yjymorefunctions.R;

/**
 * Auth：yujunyao
 * Since: 2016/11/16 10:37
 * Email：yujunyao@yonglibao.com
 */

public class RulerView extends View implements GestureDetector.OnGestureListener{

    private static final String TAG = RulerView.class.getSimpleName();

    /**用于画尺子的刻度*/
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**画文字*/
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**字体大小*/
    private float textSize = 0.0f;
    /**尺子刻度默认色*/
    private int rulerScaleColor = Color.WHITE;
    /**文字默认色*/
    private int rulerTextColor = Color.WHITE;
    /**尺子背景默认色*/
    private int rulerBackgroundColor = Color.GRAY;
    /**尺子的最大值*/
    private float rulerMaxValue = 0.0f;
    /**尺子的最小值*/
    private float rulerMinValue = 0.0f;
    /**尺子的计量单位*/
    private float rulerUnitValue = 0.0f;
    /**尺子的总共刻度*/
    private int rulerCount = 0;
    /**是否显示特殊刻度*/
    private boolean bDisplaySpecific = true;
    /**显示的特殊刻度宽度*/
    private float specificWidth = 0.0f;
    /**显示的正常刻度宽度*/
    private float specificHeight = 0.0f;
    /**于某数的倍数显示特殊刻度宽度例如5、10等*/
    private int specificUnitDependency = 5;
    /**普通刻度宽度*/
    private float commonWidth = 0.0f;
    /**普通刻度高度*/
    private float commonHeight = 0.0f;
    /**刻度间隔距离*/
    private float scaleIntervalDistance = 0.0f;
    /**字体显示的高度区域*/
    private float textRectHeight = 0.0f;
    /**是否快速滑动*/
    private boolean bFling = false;
    /**尺子起始半屏幕*/
    private float rulerStartX = 0.0f;
    /**尺子总长度*/
    private float contentLengthX = 0.0f;



    private Scroller mScroller;
    private GestureDetectorCompat mGestureDetectorCompat;

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RulerView);
        if(typedArray != null) {
            rulerBackgroundColor = typedArray.getColor(R.styleable.RulerView_rulerBackgroundColor, rulerBackgroundColor);
            rulerScaleColor = typedArray.getColor(R.styleable.RulerView_rulerScaleColor, rulerScaleColor);
            rulerTextColor = typedArray.getColor(R.styleable.RulerView_rulerTextColor, rulerTextColor);
            rulerMaxValue = typedArray.getFloat(R.styleable.RulerView_rulerMaxValue, rulerMaxValue);
            rulerMinValue = typedArray.getFloat(R.styleable.RulerView_rulerMinValue, rulerMinValue);
            rulerUnitValue = typedArray.getFloat(R.styleable.RulerView_rulerUnitValue, rulerUnitValue);
            bDisplaySpecific = typedArray.getBoolean(R.styleable.RulerView_bDisplaySpecific, bDisplaySpecific);
            specificWidth = typedArray.getDimension(R.styleable.RulerView_specificWidth, specificWidth);
            specificHeight = typedArray.getDimension(R.styleable.RulerView_specificHeight, specificHeight);
            specificUnitDependency = typedArray.getInt(R.styleable.RulerView_specificUnitDependency, specificUnitDependency);
            commonWidth = typedArray.getDimension(R.styleable.RulerView_commonWidth, commonWidth);
            commonHeight = typedArray.getDimension(R.styleable.RulerView_commonHeight, commonHeight);
            scaleIntervalDistance = typedArray.getDimension(R.styleable.RulerView_scaleIntervalDistance, scaleIntervalDistance);
            textSize = typedArray.getDimension(R.styleable.RulerView_textSize, textSize);
            textRectHeight = typedArray.getDimension(R.styleable.RulerView_textRectHeight, textRectHeight);

            typedArray.recycle();
        }

        mPaint.setColor(rulerScaleColor);

        mTextPaint.setColor(rulerTextColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mScroller = new Scroller(context);
        mGestureDetectorCompat = new GestureDetectorCompat(context, this);

        calculateRulerCount();
    }

    /**计算刻度总个数*/
    private void calculateRulerCount() {
        rulerCount = (int) ((rulerMaxValue - rulerMinValue) / rulerUnitValue) + 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(rulerBackgroundColor);

        drawScale(canvas);

        drawScaleText(canvas);
    }

    private void drawScale(Canvas canvas) {
        for(int i=0;i<rulerCount;i++) {
            float startX = i * scaleIntervalDistance + rulerStartX;
            if(bDisplaySpecific && i % specificUnitDependency == 0) {
                mPaint.setStrokeWidth(specificWidth);
                canvas.drawLine(startX, 0, startX, specificHeight, mPaint);
            }else {
                mPaint.setStrokeWidth(commonWidth);
                canvas.drawLine(startX, 0, startX, commonHeight, mPaint);
            }
        }
    }

    private void drawScaleText(Canvas canvas) {
        for(int i=0;i<rulerCount;i++) {
            if(i % specificUnitDependency == 0) {
                float startX = i * scaleIntervalDistance + rulerStartX;
                String text = i + "";
                canvas.drawText(text, 0, text.length(), startX, textRectHeight + specificHeight, mTextPaint);
            }
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }else {
            if(bFling) {
                bFling = false;
                adjustPosition();
            }
        }
    }

    private void adjustPosition() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean resolve = mGestureDetectorCompat.onTouchEvent(event);
        if (!bFling && MotionEvent.ACTION_UP == event.getAction()) {
            adjustPosition();
            resolve = true;
        }
        return resolve || super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w != oldw || h != oldh) {
            rulerStartX = w / 2;
            contentLengthX = (rulerMaxValue - rulerMinValue) / rulerUnitValue * scaleIntervalDistance;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if(!mScroller.isFinished()) {
            mScroller.forceFinished(false);
        }
        bFling = false;
        if(null != getParent()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float scroll = getScrollX();
        float distance = distanceX;

        if(distance == 0) {
            return true;
        }
        //屏蔽两端的边界
        if(scroll + distance <= 0 || scroll + distance >= contentLengthX) {
            return true;
        }

        scrollBy((int) distance, 0);

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float scroll = getScrollX();
        float velocity = velocityX;

        if (scroll < rulerStartX - contentLengthX || scroll > contentLengthX + rulerStartX) {
            return false;
        }
        Log.e(TAG, "onFling--->" + scroll);

        bFling = true;

        flingAction((int) -velocity / 2, (int) scroll);

        return true;
    }


    private void flingAction(int velocity, int scroll) {
        mScroller.fling(scroll, 0, velocity, 0,  (int) (rulerStartX - contentLengthX), (int) (contentLengthX + rulerStartX), 0, 0);

        ViewCompat.postInvalidateOnAnimation(this);
    }
}
