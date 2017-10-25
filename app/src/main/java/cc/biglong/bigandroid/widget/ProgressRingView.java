
package cc.biglong.bigandroid.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import cc.biglong.bigandroid.R;


public class ProgressRingView extends View{

    /**
     * 绘制圆环，初始化变量：颜色，类型，宽度
     */
    private Paint mPaint;

    /**
     * 描述绘制范围
     */
    private RectF mColorWheelRectangle = new RectF();

    /**
     * 圆环的半径以及圆环的宽度
     */
    private int mRingRadius, mRingThickness;

    /**
     * 画布圆心偏移
     */
    private float mTranslationOffset;

    /**
     * 组件的长宽最小值
     */
    private int min;

    /**
     * 圆环颜色
     */
    private int color;

    /**
     * 旋转周期
     */
    private int duration;


    public ProgressRingView(Context context) {
        this(context, null);
    }

    public ProgressRingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int attr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.ProgressRingView, attr, 0);

        mRingRadius = a.getDimensionPixelSize(R.styleable.ProgressRingView_ProgressRingradius, 120);
        mRingThickness = a.getDimensionPixelSize(R.styleable.ProgressRingView_ProgressRingthickness, 6);
        color = a.getColor(R.styleable.ProgressRingView_ProgressRingcolor, 0xff11b4b2);
        duration = a.getInt(R.styleable.ProgressRingView_ProgressRingduration, 800);

        a.recycle();

        int color0 = color & 0x00ffffff;

        int[] colors = new int[] {color0, color};
        Shader s = new SweepGradient(0, 0, colors, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setShader(s);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mRingThickness);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

//        int width;
//        int height;
//
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthSize;
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            width = Math.min(intrinsicSize, widthSize);
//        } else {
//            width = intrinsicSize;
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            height = Math.min(intrinsicSize, heightSize);
//        } else {
//            height = intrinsicSize;
//        }

        min = Math.min(widthSize, heightSize);
        setMeasuredDimension(min, min);
        mTranslationOffset = min * 0.5f;

        // fill the rectangle instances.
        mRingRadius = min / 2 - mRingThickness;
        mColorWheelRectangle.set(-mRingRadius, -mRingRadius,
                mRingRadius, mRingRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (animator!= null && animator.isRunning()) {
            canvas.translate(mTranslationOffset, mTranslationOffset);
            canvas.rotate((int)animator.getAnimatedValue());
            canvas.drawArc(mColorWheelRectangle, 90, 360, false, mPaint);
            postInvalidateDelayed(30);
        }
    }

    ValueAnimator animator;

    public void startAnimator() {
        if (animator != null && animator.isRunning())
            return;

        animator = ValueAnimator.ofInt(0, 360);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration);
        animator.start();
        animator.setRepeatCount(Animation.INFINITE);
        invalidate();
    }


    public void endAnim() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }
    }


    public boolean isAniming() {
        return animator != null && animator.isRunning();
    }

    public void setRingThickness(int thickness) {
        if (thickness <=0 )
            return;
        this.mRingThickness = thickness;
        mPaint.setStrokeWidth(mRingThickness);
        mRingRadius = min / 2 - mRingThickness;
        mColorWheelRectangle.set(-mRingRadius, -mRingRadius,
                mRingRadius, mRingRadius);
        invalidate();
    }
}
