package me.ssiddh.mycupid.viewmodel;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.MockitoAnnotations;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.repository.MatchesRepository;
import me.ssiddh.mycupid.util.TestUtil;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SpecialFragmentViewModelTest {

    private MatchesRepository matchesRepository;
    private SpecialFragmentViewModel specialFragmentViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MutableLiveData<List<MatchPerson>> matchList = new MutableLiveData<>();
    private Observer<List<MatchPerson>> observer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchesRepository = mock(MatchesRepository.class);
        when(matchesRepository.getPeopleList()).thenReturn(matchList);
        specialFragmentViewModel = new SpecialFragmentViewModel(matchesRepository);
        observer = mock(Observer.class);
        specialFragmentViewModel.getSpecialBlendList().observeForever(observer);
    }

    @Test
    public void emptyNotNullList() {
        assertThat(specialFragmentViewModel.getSpecialBlendList(), notNullValue());
    }

    @Test
    public void fetchMatchList() {
//        assertThat(specialFragmentViewModel.getSpecialBlendList(), notNullValue());
        List<MatchPerson> matchPeople = TestUtil.getMatchedList(6);
        doAnswer(invocation -> {
            matchList.setValue(matchPeople);
            return null;
        }).when(matchesRepository).fetchFromServer();
        specialFragmentViewModel.refreshSpecialList();
        verify(observer).onChanged(matchPeople);
        assertEquals(specialFragmentViewModel.getSpecialBlendList().getValue().size(), matchPeople.size());
    }

}