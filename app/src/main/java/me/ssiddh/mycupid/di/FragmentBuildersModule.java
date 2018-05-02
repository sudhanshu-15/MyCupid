package me.ssiddh.mycupid.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ssiddh.mycupid.ui.MatchedFragment;
import me.ssiddh.mycupid.ui.SpecialFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract SpecialFragment contributeSpecialFragment();

    @ContributesAndroidInjector
    abstract MatchedFragment contributeMatchedFragment();
}
