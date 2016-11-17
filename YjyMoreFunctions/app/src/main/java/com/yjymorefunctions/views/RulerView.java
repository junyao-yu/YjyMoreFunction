package com.yjymorefunctions.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
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
    /**刻度改变回调*/
    private OnValueChangeListener onValueChangeListener = null;
    /**选中刻度位置*/
    private int choosedScaleIndex = 0;


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

        setScaleIndex(0);
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
            calculateScale();
            invalidate();
        }else {
            if(bFling) {
                bFling = false;
                adjustPosition();
            }
        }
    }

    //归整位置
    private void adjustPosition() {
        int scroll = getScrollX();
        float distance = choosedScaleIndex * scaleIntervalDistance - scroll;
        if(distance == 0) {
            return;
        }
        mScroller.startScroll(scroll, 0, (int) distance, 0);
        postInvalidate();
    }

    //计算刻度
    /**
     * scrollBy在原先偏移的基础上再发生偏移
     * scrollTo每次在原始位置进行偏移
      */
    private void calculateScale() {
        int offset = getScrollX();
        int tempIndex = Math.round(offset / scaleIntervalDistance);
        choosedScaleIndex = protectScaleIndex(tempIndex);
        if(onValueChangeListener != null) {
            onValueChangeListener.changeValue(this, choosedScaleIndex * rulerUnitValue);
        }
    }

    //防止越界
    private int protectScaleIndex(int index) {
        int selectedIndex = index;
        if(selectedIndex < 0) {
            selectedIndex = 0;
        }else if (selectedIndex > rulerCount){
            selectedIndex = rulerCount - 1;
        }
        return selectedIndex;
    }

    //初始化刻度
    public void setScaleIndex(int index) {
        choosedScaleIndex = protectScaleIndex(index);
        post(new Runnable() {
            @Override
            public void run() {
                int moveDistance = (int) (choosedScaleIndex * scaleIntervalDistance);
                scrollTo(moveDistance, 0);
                calculateScale();
                invalidate();
            }
        });
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
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    //测量宽度：处理MeasureSpec.UNSPECIFIED的情况
    private int measureWidth(int widthMeasureSpec) {

        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);

        //View的最小值与背景最小值两者中的最大值（宽度）
        int result = getSuggestedMinimumWidth();
        switch (measureMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = measureSize;
                break;
            default:
                break;
        }
        return result;
    }

    //测量高度
    private int measureHeight(int heightMeasure) {
        int measureMode = MeasureSpec.getMode(heightMeasure);
        int measureSize = MeasureSpec.getSize(heightMeasure);
        int result  = (int) (textSize) * 4;
        switch (measureMode) {
            //设置了确切的高度
            case MeasureSpec.EXACTLY:
                result = Math.max(result, measureSize);
                break;
            //没有设置了确切的高度
            case MeasureSpec.AT_MOST:
                result = Math.min(result, measureSize);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w != oldw || h != oldh) {
            rulerStartX = w / 2.0f;
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

        calculateScale();
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

        bFling = true;

        flingAction((int) -velocity / 2);

        return true;
    }



    private void flingAction(int velocity) {
        //minX 能够滚动的最小距离
        //maxX 能够滚动的最大距离
        mScroller.fling(getScrollX(), 0, velocity, 0,  0, (int) contentLengthX, 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public interface OnValueChangeListener {
        void changeValue(RulerView rulerView, float value);
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        onValueChangeListener = listener;
    }

}
