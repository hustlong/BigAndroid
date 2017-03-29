package cc.biglong.bigandroid.model;

import cc.biglong.bigandroid.api.ApiMethods;
import cc.biglong.bigandroid.entity.GithubUser;
import cc.biglong.bigandroid.presenter.GithubUserPresenterI;
import rx.Subscriber;

/**
 * Created by biglong on 2017/3/29.
 */

public class GithubUserModel {

    private GithubUserPresenterI mPresenterI;

    public GithubUserModel(GithubUserPresenterI presenterI) {
        this.mPresenterI = presenterI;
    }

    public void getUserInfo(String user) {
        Subscriber<GithubUser> subscriber = new Subscriber<GithubUser>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mPresenterI != null)
                    mPresenterI.onError(e.getMessage());
            }

            @Override
            public void onNext(GithubUser user) {
                if (mPresenterI != null)
                    mPresenterI.getUserInfoComplete(user);
            }
        };
        ApiMethods.getInstance().getGithubUserInfo(subscriber,user);
    }
}
