package me.ssiddh.mycupid.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.di.DaggerMatchesRepositoryComponent;
import me.ssiddh.mycupid.di.MatchesRepositoryComponent;
import me.ssiddh.mycupid.repository.MatchesRepository;

public class SpecialFragmentViewModel  extends ViewModel{

    private LiveData<List<MatchPerson>> specialBlendList;

    public SpecialFragmentViewModel() {
//        specialBlendList = MatchesRepository.getInstance().getPeopleList();
        MatchesRepositoryComponent matchesRepositoryComponent = DaggerMatchesRepositoryComponent.builder().build();
        MatchesRepository matchesRepository = matchesRepositoryComponent.getMatchesRepository();
        specialBlendList = matchesRepository.getPeopleList();
    }

    public LiveData<List<MatchPerson>> getSpecialBlendList() {
        return specialBlendList;
    }
}
