package me.ssiddh.mycupid.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.db.MatchesDao;
import me.ssiddh.mycupid.data.db.MyCupidDatabase;
import me.ssiddh.mycupid.viewmodel.MyCupidViewModelFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton @Provides
    MyCupidService provideMyCupidService() {
        return new Retrofit.Builder()
                .baseUrl(MyCupidService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyCupidService.class);
    }

    @Singleton @Provides
    MyCupidDatabase provideDb(Application application) {
        return Room.databaseBuilder(application, MyCupidDatabase.class, "mycupid.db").build();
    }

    @Singleton @Provides
    MatchesDao provideMatchesDao(MyCupidDatabase db) {
        return db.matchesDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new MyCupidViewModelFactory(viewModelSubComponent.build());
    }
}
