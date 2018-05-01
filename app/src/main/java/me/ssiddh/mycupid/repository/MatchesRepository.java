package me.ssiddh.mycupid.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.db.MatchesDao;
import me.ssiddh.mycupid.data.db.MyCupidDatabase;
import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class MatchesRepository {

    private MyCupidService webservice;
    private static MatchesRepository matchesRepository;
    private MatchesDao matchesDao;

    @Inject
    public MatchesRepository(MyCupidService myCupidService, MatchesDao matchesDao) {
        this.webservice = myCupidService;
    }

    public LiveData<List<MatchPerson>> getPeopleList() {
        final MutableLiveData<List<MatchPerson>> data = new MutableLiveData<>();
        webservice.getMatches().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                List<MatchPerson> personList = response.body().getData();
                data.setValue(personList);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
        return data;

    }

}
