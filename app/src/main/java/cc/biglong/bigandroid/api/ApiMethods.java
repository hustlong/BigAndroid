package cc.biglong.bigandroid.api;

import java.util.concurrent.TimeUnit;

import cc.biglong.bigandroid.entity.BaseGithubHttp;
import cc.biglong.bigandroid.entity.GithubUser;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by biglong on 2017/3/29.
 */

public class ApiMethods {

    private static final int DEFAULT_TIMEOUT = 30;
    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private GithubApi mGithubApi;


    public void getGithubUserInfo(Subscriber<GithubUser> subscriber,String user) {
        Observable<GithubUser> observable = mGithubApi.getGithubUserInfo(user)
                .map(new GithubMapFunc<GithubUser>());
        toSubscribe(observable,subscriber);
    }




    /**
     * 统一管理Github请求的信息
     * @param <T>
     */
    private class GithubMapFunc<T extends BaseGithubHttp> implements Func1<T, T> {

        @Override
        public T call(T httpResult) throws ApiException{
            if ( httpResult.getMessage() != null) {
                throw new ApiException(ApiException.TYPE_GITHUB,httpResult.getMessage());
            }
            return httpResult;
        }
    }

    /**
     * 统一添加线程管理并订阅
     * @param o 发布者
     * @param s 订阅者
     */
    private void toSubscribe(Observable o, Subscriber s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    //构造方法私有,单例模式
    private ApiMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GITHUB_BASE_URL)
                .build();
        mGithubApi = retrofit.create(GithubApi.class);
    }

    public static ApiMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ApiMethods INSTANCE = new ApiMethods();
    }
}
