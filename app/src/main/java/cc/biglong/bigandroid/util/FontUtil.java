package cc.biglong.bigandroid.util;

import android.graphics.Paint;

/**
 * Created by biglong on 2017/3/13.
 */

public class FontUtil {
    /**
     * @return 返回指定笔和指定字符串的长度
     */
    public static float getFontLength(Paint paint, String str) {
        return paint.measureText(str);
    }
    /**
     * @return 返回指定笔的文字高度
     */
    public static float getFontHeight(Paint paint)  {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
}
