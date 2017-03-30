package cc.biglong.bigandroid.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import cc.biglong.bigandroid.R;

/**
 * Created by biglong on 2017/3/29.
 */

public class ColorsFlowCircle extends View  {


    /**
     * {@code Paint} instance used to draw the color wheel.
     */
    private Paint mColorWheelPaint;

    /**
     * The width of the color wheel thickness.
     */
    private int mColorWheelThickness;

    /**
     * The radius of the color wheel.
     */
    private int mColorWheelRadius;
    private int mPreferredColorWheelRadius;

    /**
     * Number of pixels the origin of this view is moved in X- and Y-direction.
     *
     * <p>
     * We use the center of this (quadratic) View as origin of our internal
     * coordinate system. Android uses the upper left corner as origin for the
     * View-specific coordinate system. So this is the value we use to translate
     * from one coordinate system to the other.
     * </p>
     *
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     *
     * @see #onDraw(Canvas)
     */
    private float mTranslationOffset;

    /**
     * The rectangle enclosing the color wheel.
     */
    private RectF mColorWheelRectangle = new RectF();


    /**
     * 动画延时时间
     */
    private int animDuration = 6000;

    /**
     * Colors to construct the color wheel using {@link SweepGradient}.
     */
    private int[] colors = new int[] { 0xFFd50000, 0xFFf4511e,
            0xFFf6bf26, 0xFF0b8043, 0xFFd50000};




    public ColorsFlowCircle(Context context) {
        super(context);
        init(null, 0);
    }

    public ColorsFlowCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ColorsFlowCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.ColorsFlowCircle, defStyle, 0);
        final Resources b = getContext().getResources();

        mColorWheelThickness = a.getDimensionPixelSize(
                R.styleable.ColorsFlowCircle_color_wheel_thickness,
                b.getDimensionPixelSize(R.dimen.color_wheel_thickness));
        mColorWheelRadius = a.getDimensionPixelSize(
                R.styleable.ColorsFlowCircle_color_wheel_radius,
                b.getDimensionPixelSize(R.dimen.color_wheel_radius));
        mPreferredColorWheelRadius = mColorWheelRadius;

        a.recycle();
        Shader s = new SweepGradient(0, 0, colors, null);

        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setShader(s);
        mColorWheelPaint.setStyle(Paint.Style.STROKE);
        mColorWheelPaint.setStrokeWidth(mColorWheelThickness);

        startAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (animator.isRunning()) {
            // All of our positions are using our internal coordinate system.
            // Instead of translating
            // them we let Canvas do the work for us.
            canvas.translate(mTranslationOffset, mTranslationOffset);
            canvas.rotate((int)animator.getAnimatedValue());

            // Draw the color wheel.
            canvas.drawOval(mColorWheelRectangle, mColorWheelPaint);
            postInvalidateDelayed(50);
        }
    }

    ValueAnimator animator;
    void startAnimator() {
        animator = ValueAnimator.ofInt(0, 360);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(animDuration);
        animator.start();
        animator.setRepeatCount(Animation.INFINITE);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int intrinsicSize = 2 * (mPreferredColorWheelRadius);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(intrinsicSize, widthSize);
        } else {
            width = intrinsicSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(intrinsicSize, heightSize);
        } else {
            height = intrinsicSize;
        }

        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mTranslationOffset = min * 0.5f;

        // fill the rectangle instances.
        mColorWheelRadius = min / 2 - mColorWheelThickness;
        mColorWheelRectangle.set(-mColorWheelRadius, -mColorWheelRadius,
                mColorWheelRadius, mColorWheelRadius);
    }

    public int getAnimDuration() {
        return this.animDuration;
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
        animator.setDuration(animDuration);
        invalidate();
    }

    public void setColors(int[] colors) {
        int[] tmp = new int[colors.length + 1];
        System.arraycopy(colors,0,tmp,1,colors.length);
        tmp[0] = colors[colors.length-1];
        this.colors = tmp;
        Shader s = new SweepGradient(0, 0, this.colors, null);
        mColorWheelPaint.setShader(s);
        invalidate();
    }
}
