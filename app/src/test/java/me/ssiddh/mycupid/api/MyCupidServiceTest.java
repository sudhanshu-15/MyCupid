package me.ssiddh.mycupid.api;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static me.ssiddh.mycupid.util.LiveDataTestUtil.getValue;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MyCupidServiceTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MyCupidService service;

    private MockWebServer mockWebServer;

    @Before
    public void createService() throws IOException {
        mockWebServer = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyCupidService.class);
    }

    @After
    public void stopService() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getPersons() throws IOException, InterruptedException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("api-resp/testMatch.json");
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)));

        retrofit2.Response<Data> response = service.getMatches().execute();
        MatchPerson person = createMatchPerson();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertNotNull(response.body());
        Data data = response.body();
        Assert.assertEquals(data.getData().get(0).getUsername(), person.getUsername());
    }

    private MatchPerson createMatchPerson() throws IOException {
        MatchPerson.Photo photo = new MatchPerson.Photo("36x36/684x684/2/15743311334557165678.jpg");
        MatchPerson person = new MatchPerson(0, "5592586755333955055", "bklyn2356", 27,8715,"NY", "Brooklyn", false, photo);
        return person;
    }

}