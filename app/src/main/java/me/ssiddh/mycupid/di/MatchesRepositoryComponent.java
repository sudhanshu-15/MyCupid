package me.ssiddh.mycupid.di;

import javax.inject.Singleton;

import dagger.Component;
import me.ssiddh.mycupid.repository.MatchesRepository;

@Singleton
@Component(modules = {MatchesRepositoryModule.class})
public interface MatchesRepositoryComponent {
    MatchesRepository getMatchesRepository();
}
