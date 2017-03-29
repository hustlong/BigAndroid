package cc.biglong.bigandroid.entity;

/**
 * Created by biglong on 2017/3/29.
 */

public class GithubUser extends BaseGithubHttp{
    private String login;

    private int id;

    private String avatar_url;

    private String gravatar_id;

    private String url;

    private String html_url;

    private String followers_url;

    private String following_url;

    private String gists_url;

    private String starred_url;

    private String subscriptions_url;

    private String organizations_url;

    private String repos_url;

    private String events_url;

    private String received_events_url;

    private String type;

    private boolean site_admin;

    private String name;

    private String company;

    private String blog;

    private String location;

    private String email;

    private String hireable;

    private String bio;

    private int public_repos;

    private int public_gists;

    private int followers;

    private int following;

    private String created_at;

    private String updated_at;



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n昵称:").append(login)
                .append("\n博客:").append(blog)
                .append("\n位置:").append(location)
                .append("\n邮箱:").append(email)
                .append("\n项目数量:").append(public_repos)
                .append("\n粉丝:").append(followers)
                .append("\n关注:").append(following)
                .append("\n注册时间:").append(created_at)
                .append("\n最近更新:").append(updated_at);
        return builder.toString();
    }






    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return this.login;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setGravatar_id(String gravatar_id){
        this.gravatar_id = gravatar_id;
    }
    public String getGravatar_id(){
        return this.gravatar_id;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setHtml_url(String html_url){
        this.html_url = html_url;
    }
    public String getHtml_url(){
        return this.html_url;
    }
    public void setFollowers_url(String followers_url){
        this.followers_url = followers_url;
    }
    public String getFollowers_url(){
        return this.followers_url;
    }
    public void setFollowing_url(String following_url){
        this.following_url = following_url;
    }
    public String getFollowing_url(){
        return this.following_url;
    }
    public void setGists_url(String gists_url){
        this.gists_url = gists_url;
    }
    public String getGists_url(){
        return this.gists_url;
    }
    public void setStarred_url(String starred_url){
        this.starred_url = starred_url;
    }
    public String getStarred_url(){
        return this.starred_url;
    }
    public void setSubscriptions_url(String subscriptions_url){
        this.subscriptions_url = subscriptions_url;
    }
    public String getSubscriptions_url(){
        return this.subscriptions_url;
    }
    public void setOrganizations_url(String organizations_url){
        this.organizations_url = organizations_url;
    }
    public String getOrganizations_url(){
        return this.organizations_url;
    }
    public void setRepos_url(String repos_url){
        this.repos_url = repos_url;
    }
    public String getRepos_url(){
        return this.repos_url;
    }
    public void setEvents_url(String events_url){
        this.events_url = events_url;
    }
    public String getEvents_url(){
        return this.events_url;
    }
    public void setReceived_events_url(String received_events_url){
        this.received_events_url = received_events_url;
    }
    public String getReceived_events_url(){
        return this.received_events_url;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setSite_admin(boolean site_admin){
        this.site_admin = site_admin;
    }
    public boolean getSite_admin(){
        return this.site_admin;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCompany(String company){
        this.company = company;
    }
    public String getCompany(){
        return this.company;
    }
    public void setBlog(String blog){
        this.blog = blog;
    }
    public String getBlog(){
        return this.blog;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setHireable(String hireable){
        this.hireable = hireable;
    }
    public String getHireable(){
        return this.hireable;
    }
    public void setBio(String bio){
        this.bio = bio;
    }
    public String getBio(){
        return this.bio;
    }
    public void setPublic_repos(int public_repos){
        this.public_repos = public_repos;
    }
    public int getPublic_repos(){
        return this.public_repos;
    }
    public void setPublic_gists(int public_gists){
        this.public_gists = public_gists;
    }
    public int getPublic_gists(){
        return this.public_gists;
    }
    public void setFollowers(int followers){
        this.followers = followers;
    }
    public int getFollowers(){
        return this.followers;
    }
    public void setFollowing(int following){
        this.following = following;
    }
    public int getFollowing(){
        return this.following;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
}
