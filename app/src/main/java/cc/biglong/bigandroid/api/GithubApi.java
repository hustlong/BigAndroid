package cc.biglong.bigandroid.api;

import cc.biglong.bigandroid.entity.GithubUser;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by biglong on 2017/3/29.
 */

public interface GithubApi {

    @GET("users/{user}")
    Observable<GithubUser> getGithubUserInfo(
            @Path("user") String user
    );

}
