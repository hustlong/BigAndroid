package cc.biglong.bigandroid.entity;

/**
 * Created by biglong on 2017/3/29.
 */

public class BaseGithubHttp {

    private String message;

    private String documentation_url;

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setDocumentation_url(String documentation_url){
        this.documentation_url = documentation_url;
    }
    public String getDocumentation_url(){
        return this.documentation_url;
    }
}
