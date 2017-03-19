package cc.biglong.bigandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.util.FontUtil;

/**
 * Created by biglong on 2017/3/16.
 */

public class CircleProgress extends View {

    /**
     * 圆环的范围最大值
     */
    private int max;

    /**
     * 选中的进度值
     */
    private int progress;

    /**
     *圆环的未选中的部分颜色和已经选中的颜色;
     */
    private int hintColor, progressColor;

    /**
     * 圆环中间的指示文本、字体颜色、字体大小;
     */
    private String centerText;
    private int textColor;
    private float textSize;

    /**
     * 圆环的厚度
     */
    private float thickness;


    /** 绘图 */
    private Paint hintPaint;
    private Paint progressPaint;
    private Paint textPaint;
    private RectF ringRectF = new RectF();
    private int minWidth;
    private float textWidth,textHeight;


    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        /** 获取XML中定义的参数 */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgress);
        if (typedArray !=null) {
            max = typedArray.getInteger(R.styleable.CircleProgress_max, 100);
            progress = typedArray.getInteger(R.styleable.CircleProgress_progress, 25);
            hintColor = typedArray.getColor(R.styleable.CircleProgress_progress_color, Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.CircleProgress_hint_color, Color.GREEN);
            textColor = typedArray.getColor(R.styleable.CircleProgress_text_color,Color.RED);
            textSize = typedArray.getDimension(R.styleable.CircleProgress_text_size,18.0f);
            centerText = typedArray.getString(R.styleable.CircleProgress_center_text);
            thickness = typedArray.getDimension(R.styleable.CircleProgress_thickness, 10);
            typedArray.recycle();

            if(centerText == null)
                centerText = "ABCD";
        }
        init();
    }

    private void init() {
        hintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hintPaint.setColor(hintColor);
        hintPaint.setStyle(Paint.Style.STROKE);
        hintPaint.setStrokeWidth(thickness);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(thickness);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(minWidth*0.5f,minWidth*0.5f);
        canvas.drawOval(ringRectF, hintPaint);
        canvas.drawArc(ringRectF,-90.0f,360.0f* progress /max,false, progressPaint);
        canvas.drawText(centerText,-textWidth/2,textHeight/2,textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        minWidth = Math.min(widthSize, heightSize);
        setMeasuredDimension(minWidth, minWidth);

        int ringRadius = (minWidth- (int) thickness)/2;
        ringRectF.set(-ringRadius,-ringRadius,ringRadius,ringRadius);

        textWidth = FontUtil.getFontLength(textPaint,centerText);
        textHeight = FontUtil.getFontHeight(textPaint);
    }


    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public void setProgress(int progress) {
        if (progress<0)
            progress = 0;
        this.progress = progress > max ? max : progress;
        invalidate();
    }

    public void setCenterText(String text) {
        this.centerText = text;
        if(centerText == null)
            centerText = "ABCD";
        invalidate();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        invalidate();
    }

    public void setTextSize(float size) {
        this.textSize = size;
        invalidate();
    }

    public void setHintColor(int color) {
        this.hintColor = color;
        invalidate();
    }

    public void setProgressColor(int color) {
        this.progressColor = color;
        invalidate();
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
        invalidate();
    }

}