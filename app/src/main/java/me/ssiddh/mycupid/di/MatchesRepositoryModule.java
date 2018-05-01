package me.ssiddh.mycupid.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.repository.MatchesRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MatchesRepositoryModule {

    @Provides
    public MatchesRepository matchesRepository(MyCupidService myCupidService){
        return new MatchesRepository(myCupidService);
    }

    @Singleton @Provides
    public MyCupidService myCupidService(Retrofit retrofit) {
        return retrofit.create(MyCupidService.class);
    }

    @Provides
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(MyCupidService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
