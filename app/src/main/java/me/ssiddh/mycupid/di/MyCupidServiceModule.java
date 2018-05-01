package me.ssiddh.mycupid.di;

import dagger.Module;
import dagger.Provides;
import me.ssiddh.mycupid.api.MyCupidService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MyCupidServiceModule {

    @Provides
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
