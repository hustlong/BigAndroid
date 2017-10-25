package cc.biglong.bigandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cc.biglong.bigandroid.R;


/**
 * Created by biglong on 2017/9/17.
 */

public class SlideVerticalBar extends View implements View.OnTouchListener {

    /**
     * 进度条选中、未选中时的状态
     */
    private int unchecked, checked;

    /**
     * 圆角半径
     */
    private float radius;

    /**
     * 进度
     */
    private float progress;

    /**
     * 绘制画笔
     */
    private Paint mPaint;

    /**
     * 组件的宽高
     */
    private int W, H;

    /**
     * 明亮图层范围
     * 未选中图层范围
     */
    private RectF mLTRectF, mDKRectF;

    /**
     * S、D、图层的相交模式
     */
    private Xfermode xfermode;

    /**
     * 触摸点y坐标
     */
    private float y;

    /**
     * 明亮图层的高度
     */
    private int top;

    private OnListener mOnListener;


    public SlideVerticalBar(Context context) {
        this(context, null);
    }

    public SlideVerticalBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideVerticalBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int attr) {
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlideVerticalBar, attr, 0);
        unchecked = array.getColor(R.styleable.SlideVerticalBar_slide_vertical_bar_uncheckedColor, 0xff52443C);
        checked = array.getColor(R.styleable.SlideVerticalBar_slide_vertical_bar_checkedColor, 0xffFFFFFF);
        progress = array.getFloat(R.styleable.SlideVerticalBar_slide_vertical_bar_progress, 0.3f);
        radius = array.getDimension(R.styleable.SlideVerticalBar_slide_vertical_bar_radius, 15);

        array.recycle();

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        setOnTouchListener(this);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        W = MeasureSpec.getSize(widthMeasureSpec);
        H = MeasureSpec.getSize(heightMeasureSpec);

        top = (int) ((1 - progress) * H);
        calculateTop(0);

        mDKRectF = new RectF(0, 0, W, H);
        mLTRectF = new RectF(0, 0, W, H);

        setMeasuredDimension(W, H);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(mDKRectF, mPaint, Canvas.ALL_SAVE_FLAG);

        //绘制目标图
        mPaint.setColor(unchecked);
        canvas.drawRoundRect(mDKRectF, radius, radius, mPaint);

        //绘制源图
        mPaint.setColor(checked);
        mPaint.setXfermode(xfermode);
        mLTRectF.top = top;
        canvas.drawRect(mLTRectF, mPaint);
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(saveCount);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = event.getY() - y;
                this.y = event.getY();
                calculateTop(deltaY);
                invalidate();

                final float progress = 1 - (float)top/H;
                if (this.progress != progress && mOnListener != null) {
                    this.progress = progress;
                    mOnListener.onSlideVerticalBarListener(this, event, this.progress);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void calculateTop(float deltaY) {
        top += deltaY;

        if (top < 0)
            top = 0;
        else if (top > H)
            top = H;
    }

    public void setOnListener(OnListener onListener) {
        mOnListener = onListener;
    }

    public void setProgress(float progress) {
        setProgress(progress, false);
    }

    public void setProgress(float progress, boolean callback) {
        if (progress != this.progress) {
            this.progress = progress;
            top = (int) ((1 - progress) * H);
            calculateTop(0);
            invalidate();
        }

        if (callback) {
            if (mOnListener != null)
                mOnListener.onSlideVerticalBarListener(this, null, progress);
        }

    }

    public void setRadius(float radius) {
        if (radius <= 0)
            return;
        this.radius = radius;
        invalidate();
    }

    public interface OnListener {
        void onSlideVerticalBarListener(SlideVerticalBar bar, MotionEvent event, float progress);
    }
}
