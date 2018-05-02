package me.ssiddh.mycupid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;

public class MatchedFragmentViewModel extends AndroidViewModel {

    private LiveData<List<MatchPerson>> matchedList;
    private MatchesRepository repository;

    @Inject
    public MatchedFragmentViewModel(MatchesRepository repository, @NonNull Application application) {
        super(application);
        this.repository = repository;
        matchedList = repository.getMatchedList();
    }

    public LiveData<List<MatchPerson>> getMatchedList() {
        return matchedList;
    }
}
