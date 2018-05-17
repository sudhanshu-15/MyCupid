package me.ssiddh.mycupid.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import me.ssiddh.mycupid.repository.MatchesRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class SpecialFragmentViewModelTest {

    private MatchesRepository matchesRepository;
    private SpecialFragmentViewModel specialFragmentViewModel;

    @Rule
    InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        matchesRepository = mock(MatchesRepository.class);
//        specialFragmentViewModel = new SpecialFragmentViewModel(matchesRepository, )
    }

}