package me.ssiddh.mycupid;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.ssiddh.mycupid.api.MyCupidService;
import me.ssiddh.mycupid.data.model.Data;
import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.ui.ViewPagerAdapter;
import me.ssiddh.mycupid.util.ConnectionLiveData;
import me.ssiddh.mycupid.util.ConnectionModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PagerActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingAndroidInjector;

    @Inject
    ConnectionLiveData connectionLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        connectionLiveData.observe(this, connectionModel -> {
            Snackbar snackbar = Snackbar.make(viewPager, R.string.no_internet, Snackbar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            if(connectionModel.isConnected()) {
                snackbar.dismiss();
                snackBarView.setBackgroundColor(getColor(R.color.internet));
                snackbar.setText(R.string.internet_ok);
                snackbar.show();
            }else if(!connectionModel.isConnected()) {
                snackbar.dismiss();
                snackBarView.setBackgroundColor(getColor(R.color.error));
                snackbar.setText(R.string.no_internet);
                snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });
    }

    @Override
    public DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyCupidApplication.getRefWatcher(this).watch(this);
    }
}
