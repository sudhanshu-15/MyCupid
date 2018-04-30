package me.ssiddh.mycupid.api;


import java.util.List;

import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyCupidService {
    String BASE_URL = "https://www.okcupid.com/";

    @GET("matchSample.json")
    Call<Data> getMatches();

}
