package cc.biglong.bigandroid.entity;

/**
 * Created by biglong on 2017/3/29.
 */

public class WidgetInfo {

    private int resId;

    private String widgetName;

    public WidgetInfo(int resId, String widgetName) {
        this.resId = resId;
        this.widgetName = widgetName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }
}
