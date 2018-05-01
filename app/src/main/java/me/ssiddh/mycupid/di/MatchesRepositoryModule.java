package me.ssiddh.mycupid.di;

import dagger.Module;
import dagger.Provides;
import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.repository.MatchesRepository;

@Module(includes = MyCupidServiceModule.class)
public class MatchesRepositoryModule {

    @Provides
    public MatchesRepository matchesRepository(MyCupidService myCupidService){
        return new MatchesRepository(myCupidService);
    }
}
