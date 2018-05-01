package me.ssiddh.mycupid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;

public class SpecialFragmentViewModel  extends AndroidViewModel {

    private LiveData<List<MatchPerson>> specialBlendList;

    @Inject
    public SpecialFragmentViewModel(MatchesRepository repository, Application application) {
        super(application);
//        specialBlendList = MatchesRepository.getInstance().getPeopleList();
//        MatchesRepositoryComponent matchesRepositoryComponent = DaggerMatchesRepositoryComponent.builder().build();
//        MatchesRepository matchesRepository = matchesRepositoryComponent.getMatchesRepository();
        specialBlendList = repository.getPeopleList();
    }

    public LiveData<List<MatchPerson>> getSpecialBlendList() {
        return specialBlendList;
    }
}
