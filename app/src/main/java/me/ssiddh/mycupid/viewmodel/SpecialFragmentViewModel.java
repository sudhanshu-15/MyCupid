package me.ssiddh.mycupid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;

//View model for SpecialFragment (Special Tab)
public class SpecialFragmentViewModel  extends AndroidViewModel {

    private LiveData<List<MatchPerson>> specialBlendList;
    private MatchesRepository repository;

    //Gets list of matches from the repository
    @Inject
    public SpecialFragmentViewModel(MatchesRepository repository, Application application) {
        super(application);
        this.repository = repository;
        specialBlendList = repository.getPeopleList();
    }

    public LiveData<List<MatchPerson>> getSpecialBlendList() {
        return specialBlendList;
    }

    public void refreshSpecialList() {
        repository.fetchFromServer();
    }

    //Calls updatePerson on the DB, finds the correct record and updates it on the DB
    //TODO update logic to make it better
    public void updateLiked(int position) {
        List<MatchPerson> temp = specialBlendList.getValue();
        MatchPerson tempPerson = temp.get(position);
        tempPerson.setLiked(true);
        repository.updatePerson(tempPerson);
    }
}
