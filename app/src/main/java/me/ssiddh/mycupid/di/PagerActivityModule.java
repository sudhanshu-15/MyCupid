package me.ssiddh.mycupid.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.ssiddh.mycupid.PagerActivity;

@Module
public abstract class PagerActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract PagerActivity contributePagerActivity();
}
