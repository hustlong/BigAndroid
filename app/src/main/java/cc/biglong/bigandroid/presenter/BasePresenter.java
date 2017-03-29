package cc.biglong.bigandroid.presenter;

/**
 * Created by biglong on 2017/3/29.
 */

public interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
    void onError(String msg);
}
