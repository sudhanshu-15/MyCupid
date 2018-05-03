package me.ssiddh.mycupid.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.ssiddh.mycupid.MyCupidApplication;
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

    private final MyCupidService webservice;
    private final MatchesDao matchesDao;
    private final Executor executor;
    private MutableLiveData<List<MatchPerson>> mPersonData;

    @Inject
    public MatchesRepository(MyCupidService myCupidService, MatchesDao matchesDao, Executor executor) {
        this.webservice = myCupidService;
        this.matchesDao = matchesDao;
        this.executor = executor;
        mPersonData = new MutableLiveData<>();
    }

    public LiveData<List<MatchPerson>> getPeopleList() {
        fetchFromServer();
        return matchesDao.getAll();

    }

    public LiveData<List<MatchPerson>> getMatchedList() {
        return matchesDao.getTopMatched();
    }

    public void updatePerson(MatchPerson person) {
        executor.execute(() -> {
            matchesDao.updateLiked(person);
        });
    }

    private void fetchFromServer() {
        executor.execute(() -> {
            int count = matchesDao.getCount();
//            Log.d("DB", "fetchFromServer: count " + count);
            boolean matchesExist = (matchesDao.getCount() > 0);
//            Log.d("Matches Exist", "fetchFromServer: " + matchesExist);
            if (!matchesExist) {
                webservice.getMatches().enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        executor.execute(() -> {
                            List<MatchPerson> personList = response.body().getData();
//                            Log.d("Server Response", "onResponse: " + personList.size());
                            matchesDao.insertAll(personList);
                        });
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
//                        Log.d("ServerCall", "onFailure: " + t);
                    }
                });
            }
        });
    }
}
