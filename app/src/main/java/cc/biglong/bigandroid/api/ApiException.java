package cc.biglong.bigandroid.api;

/**
 * Created by biglong on 2017/3/29.
 */

class ApiException extends RuntimeException {


    public static final String TYPE_GITHUB = "Github";

    public ApiException(String type, String msg) {
        super(type+":"+msg);
    }

}
