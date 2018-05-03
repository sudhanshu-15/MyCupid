package me.ssiddh.mycupid.data.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.util.FakeMatchesSource;
import me.ssiddh.mycupid.util.LiveDataTestUtil;

@RunWith(AndroidJUnit4.class)
public class MatchesDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MyCupidDatabase myCupidDatabase;

    private MatchesDao matchesDao;

    @Before
    public void initDb() throws Exception {
        myCupidDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                MyCupidDatabase.class)
                .allowMainThreadQueries()
                .build();
        matchesDao = myCupidDatabase.matchesDao();
    }

    @After
    public void closeDb() throws Exception {
        myCupidDatabase.close();
    }

    @Test
    public void onGetAll_getEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List<MatchPerson> matchPeople = LiveDataTestUtil.getValue(matchesDao.getAll());
        Assert.assertTrue(matchPeople.isEmpty());
    }

    @Test
    public void onInsertAll_checkIf_RowCountIsCorrect() throws InterruptedException {
        int size = 9;
        List<MatchPerson> matchPeople = FakeMatchesSource.getMatchedList(size);
        matchesDao.insertAll(matchPeople);
        Assert.assertEquals(size-1, matchesDao.getCount());
    }

    @Test
    public void onGetTopMatched_checkIf_RowCountIsCorrect() throws InterruptedException {
        int size = 9;
        List<MatchPerson> matchPeople = FakeMatchesSource.getMatchedList(size);
        int i = 0;
        for(MatchPerson person : matchPeople) {
            if(person.getLiked()) i++;
        }
        matchesDao.insertAll(matchPeople);
        Assert.assertEquals(i, LiveDataTestUtil.getValue(matchesDao.getTopMatched()).size());
    }

    @Test
    public void onUpdate_checkIf_UpdateWasCorrect() throws InterruptedException {
        int size = 3;
        String testCity = "Bloomington";
        List<MatchPerson> matchPeople = FakeMatchesSource.getMatchedList(size);
        matchesDao.insertAll(matchPeople);
        MatchPerson tempPerson = matchPeople.get(1);
        tempPerson.setCityName(testCity);
        matchesDao.updateLiked(tempPerson);
        matchPeople = LiveDataTestUtil.getValue(matchesDao.getAll());
        MatchPerson newTempPerson = matchPeople.get(1);
        Assert.assertEquals(testCity, newTempPerson.getCityName());

    }
}
