package me.ssiddh.mycupid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;

//View Model from Matched Fragment (Match% Tab)
public class MatchedFragmentViewModel extends ViewModel {

    private LiveData<List<MatchPerson>> matchedList;
    private MatchesRepository repository;

    //Get list of top matches from repository
    @Inject
    public MatchedFragmentViewModel(MatchesRepository repository) {
//        super(application);
        this.repository = repository;
        matchedList = repository.getMatchedList();
    }

    public LiveData<List<MatchPerson>> getMatchedList() {
        return matchedList;
    }
}
