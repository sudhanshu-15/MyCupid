package me.ssiddh.mycupid.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.db.MatchesDao;
import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.util.TestUtil;
import retrofit2.Call;


import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MatchesRepositoryTest {

    private MatchesDao matchesDao;
    private MyCupidService myCupidService;
    private MatchesRepository repository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        matchesDao = mock(MatchesDao.class);
        myCupidService = mock(MyCupidService.class);
        repository = new MatchesRepository(myCupidService, matchesDao, Executors.newSingleThreadExecutor());
    }

    @Test
    public void fetchFromDB() {
        MutableLiveData<List<MatchPerson>> dbData = new MutableLiveData<>();
        List<MatchPerson> personList = TestUtil.getMatchedList(4);
        dbData.setValue(personList);
        when(matchesDao.getAll()).thenReturn(dbData);
        Observer<List<MatchPerson>> observer = mock(Observer.class);
        repository.getPeopleList().observeForever(observer);
        verify(observer).onChanged(personList);
    }

    @Test
    public void fetchTopMatchedFromDB() {
        MutableLiveData<List<MatchPerson>> dbData = new MutableLiveData<>();
        List<MatchPerson> topList = TestUtil.getMatchedList(4);
        dbData.setValue(topList);
        when(matchesDao.getTopMatched()).thenReturn(dbData);
        Observer<List<MatchPerson>> observer = mock(Observer.class);
        repository.getMatchedList().observeForever(observer);
        verify(observer).onChanged(topList);

    }

    @Test
    public void updatePerson() {
        List<MatchPerson> matchPeopleList = TestUtil.getMatchedList(5);
        MatchPerson person = matchPeopleList.get(1);
        person.setLiked(!person.getLiked());
        doAnswer(invocation -> {
            matchPeopleList.get(1).setLiked(!matchPeopleList.get(1).getLiked());
            return null;
        }).when(matchesDao).updateLiked(person);
        repository.updatePerson(person);
        assertEquals(person, matchPeopleList.get(1));
    }

}