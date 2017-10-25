package cc.biglong.bigandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.util.PixelUtil;


/**
 * Created by biglong on 2017/9/18.
 */

public class DimmerIndicator extends View {

    private int primaryColor, primarycolordark;
    private float thickness, defThickness;

    private Paint mPaint;

    //icon 的尺寸
    private int W, H;

    //中心圆半径， 中心坐标
    private float centerCircleRadius, centerX;

    //进度条：0～1
    private float progress;

    //光线的起始Y坐标和结束Y坐标
    private float lightStartY, lightEndY;

    //外圆环的半径
    private float outCircleRadius;

    private RectF mOutRingRectF;

    public DimmerIndicator(Context context) {
        this(context, null);
    }

    public DimmerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DimmerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int attr) {
        defThickness = PixelUtil.dp2px(2, context);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DimmerIndicator, attr, 0);
        if (array != null) {
            primaryColor = array.getColor(R.styleable.DimmerIndicator_dimmer_color, Color.WHITE);
            primarycolordark = array.getColor(R.styleable.DimmerIndicator_dimmer_color_dark, Color.GRAY);
            thickness = array.getDimension(R.styleable.DimmerIndicator_dimmer_thickness, defThickness);
            array.recycle();
        } else {
            primaryColor = Color.WHITE;
            primarycolordark = Color.LTGRAY;
            thickness = defThickness;
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutRingRectF = new RectF();
        progress = 0.3f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        W = MeasureSpec.getSize(widthMeasureSpec);
        H = MeasureSpec.getSize(heightMeasureSpec);
        W = Math.min(W, H);
        H = W;
        setMeasuredDimension(W, H);

        centerCircleRadius = W/6;
        centerX = W/2;

        lightStartY = W*5/24;
        lightEndY = W*6/24;

        outCircleRadius = (W - thickness)/2;
        mOutRingRectF.left = thickness/2;
        mOutRingRectF.top = thickness/2;
        mOutRingRectF.right = W - thickness/2;
        mOutRingRectF.bottom = W - thickness/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //画中心园
        mPaint.setColor(progress == 0 ? primarycolordark : primaryColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerX, centerCircleRadius, mPaint);

        //画八条光线
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(thickness*1.4f);
        for (int i=0; i<8; i++) {
            canvas.drawLine(centerX, lightStartY, centerX, lightEndY, mPaint);
            canvas.rotate(45f, centerX, centerX);
        }

        //画外圆环
        mPaint.setStrokeWidth(thickness);
        mPaint.setColor(primarycolordark);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerX, outCircleRadius, mPaint);

        //画进度条
        if (progress > 0) {
            mPaint.setColor(primaryColor);
            canvas.drawArc(mOutRingRectF, -90, progress*360, false, mPaint);
        }
    }

    public void setProgress(float progress) {
        if (progress < 0)
            progress = 0;
        if (progress > 1)
            progress = 1;

        this.progress = progress;
        invalidate();
    }

    public void setThickness(float thickness) {
        if (thickness <= 0)
            return;
        this.thickness = thickness;

        outCircleRadius = (W - thickness)/2;
        mOutRingRectF.left = thickness/2;
        mOutRingRectF.top = thickness/2;
        mOutRingRectF.right = W - thickness/2;
        mOutRingRectF.bottom = W - thickness/2;
        invalidate();
    }
}
