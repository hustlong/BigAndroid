package cc.biglong.bigandroid.presenter;

import cc.biglong.bigandroid.entity.GithubUser;
import cc.biglong.bigandroid.model.GithubUserModel;
import cc.biglong.bigandroid.view.GithubUserView;

/**
 * Created by biglong on 2017/3/29.
 */

public class GithubUserPresenter implements GithubUserPresenterI<GithubUserView> {

    private GithubUserView mView;
    private GithubUserModel mModel;

    public GithubUserPresenter(GithubUserView view) {
        attachView(view);
        mModel = new GithubUserModel(this);
    }

    public void getUserInfo(String user) {
        mModel.getUserInfo(user);
    }





    //wait for callback from model.
    @Override
    public void attachView(GithubUserView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void onError(String msg) {
        if (mView != null)
            mView.showError(msg);
    }

    @Override
    public void getUserInfoComplete(GithubUser user) {
        if (mView != null)
            mView.showGithubUserInfo(user);
    }
}
