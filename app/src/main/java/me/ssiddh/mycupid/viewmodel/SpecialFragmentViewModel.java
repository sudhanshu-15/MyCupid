package me.ssiddh.mycupid.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;

public class SpecialFragmentViewModel  extends ViewModel{

    private LiveData<List<MatchPerson>> specailBlendList;

    public SpecialFragmentViewModel() {
        specailBlendList = MatchesRepository.getInstance().getPeopleList();
    }

    public LiveData<List<MatchPerson>> getSpecailBlendList() {
        return specailBlendList;
    }
}
