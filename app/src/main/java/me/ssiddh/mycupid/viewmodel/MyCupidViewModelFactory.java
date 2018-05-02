package me.ssiddh.mycupid.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.ssiddh.mycupid.di.ViewModelSubComponent;

@Singleton
public class MyCupidViewModelFactory implements ViewModelProvider.Factory{

    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public MyCupidViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        creators.put(SpecialFragmentViewModel.class, () -> viewModelSubComponent.specialFragmentViewModel());
        creators.put(MatchedFragmentViewModel.class, () -> viewModelSubComponent.matchedFragmentViewModel());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("Unknow model class " + modelClass);
        }

        try {
            return (T) creator.call();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
