package me.ssiddh.mycupid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(MyCupidService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        MyCupidService service = retrofit.create(MyCupidService.class);

        service.getMatches().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<MatchPerson> data = response.body().getData();
                for(MatchPerson p : data){
                    Log.d("Resp", "onResponse: " + p.getUsername());
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("Response", "onFailure: " + t);
            }
        });

    }
}
