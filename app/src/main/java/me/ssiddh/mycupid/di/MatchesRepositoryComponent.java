package me.ssiddh.mycupid.di;

import dagger.Component;
import me.ssiddh.mycupid.repository.MatchesRepository;

@Component(modules = {MatchesRepositoryModule.class})
public interface MatchesRepositoryComponent {
    MatchesRepository getMatchesRepository();
}
