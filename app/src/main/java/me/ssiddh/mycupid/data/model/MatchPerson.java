package me.ssiddh.mycupid.data.model;


public class MatchPerson {

    private String userid;
    private String username;
    private int age;
    private int match;
    private String state_code;
    private String city_name;
    private Boolean liked;
    private Photo photo;

    public MatchPerson(String userid, String username, int age, int match, String state_code, String city_name, Boolean liked, Photo photo) {
        this.userid = userid;
        this.username = username;
        this.age = age;
        this.match = match;
        this.state_code = state_code;
        this.city_name = city_name;
        this.liked = liked;
        this.photo = photo;
    }

    public static class Photo {
        private static final String baseUrl = "https://k2.okccdn.com/php/load_okc_image.php/images/";
        private String base_path;

        public Photo(String base_path) {
            this.base_path = base_path;
        }
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
