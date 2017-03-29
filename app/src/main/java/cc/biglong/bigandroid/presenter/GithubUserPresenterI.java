package cc.biglong.bigandroid.presenter;

import cc.biglong.bigandroid.entity.GithubUser;

/**
 * Created by biglong on 2017/3/29.
 */

public interface GithubUserPresenterI<V> extends BasePresenter<V>{
    void getUserInfoComplete(GithubUser user);
}
