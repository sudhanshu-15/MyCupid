package me.ssiddh.mycupid.data.model;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "matches")
public class MatchPerson {
    @PrimaryKey
    private String userid;
    private String username;
    private int age;
    private int match;
    @SerializedName("state_code")
    private String stateCode;
    @SerializedName("cityName")
    private String cityName;
    private Boolean liked;
    @Embedded
    private Photo photo;

    public MatchPerson(String userid, String username, int age, int match, String stateCode, String cityName, Boolean liked, Photo photo) {
        this.userid = userid;
        this.username = username;
        this.age = age;
        this.match = match;
        this.stateCode = stateCode;
        this.cityName = cityName;
        this.liked = liked;
        this.photo = photo;
    }

    public static class Photo {
        private static final String baseUrl = "https://k2.okccdn.com/php/load_okc_image.php/images/";
        @SerializedName("base_path")
        private String basePath;

        public Photo(String basePath) {
            this.basePath = basePath;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
