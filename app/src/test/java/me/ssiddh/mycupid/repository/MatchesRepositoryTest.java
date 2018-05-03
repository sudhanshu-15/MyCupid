package me.ssiddh.mycupid.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.verification.Calls;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.db.MatchesDao;
import me.ssiddh.mycupid.data.db.MyCupidDatabase;
import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.util.ApiUtil;
import me.ssiddh.mycupid.util.FakeMatchesSource;

import static me.ssiddh.mycupid.util.ApiUtil.successCall;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class MatchesRepositoryTest {

    private MatchesRepository repository;
    private MatchesDao dao;
    private MyCupidService service;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        dao = mock(MatchesDao.class);
        service = mock(MyCupidService.class);
        MyCupidDatabase db = mock(MyCupidDatabase.class);
        when(db.matchesDao()).thenReturn(dao);
        repository = new MatchesRepository(service, dao, Executors.newSingleThreadExecutor());
    }

    @Test
    public void getMatches() throws IOException {
        MutableLiveData<List<MatchPerson>> dbData = new MutableLiveData<>();
        when(dao.getAll()).thenReturn(dbData);

        LiveData<List<MatchPerson>> data = repository.getPeopleList();

        verify(dao).getAll();

        verify(service, never()).getMatches();

        MatchPerson person = FakeMatchesSource.getPerson(5);
        List<MatchPerson> personList = Collections.singletonList(person);
        Data matchData = FakeMatchesSource.getMatchData(personList);
        when(service.getMatches()).thenReturn(ApiUtil.successCall(matchData));

        android.arch.lifecycle.Observer<List<MatchPerson>> observer = mock(android.arch.lifecycle.Observer.class);
        data.observeForever(observer);

        MutableLiveData<List<MatchPerson>> updatedDbData = new MutableLiveData<>();
        when(dao.getAll()).thenReturn(updatedDbData);
        dbData.setValue(Collections.emptyList());

        ArgumentCaptor<List<MatchPerson>> inserted = ArgumentCaptor.forClass((Class) List.class);
        verify(dao).insertAll(inserted.capture());

        assertThat(inserted.getValue().size(), is(1));
        MatchPerson first = inserted.getValue().get(0);
        assertThat(first.getUsername(), is("testUserName"));
    }

}
