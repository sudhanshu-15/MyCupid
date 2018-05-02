package me.ssiddh.mycupid.di;

import dagger.Subcomponent;
import me.ssiddh.mycupid.viewmodel.MatchedFragmentViewModel;
import me.ssiddh.mycupid.viewmodel.SpecialFragmentViewModel;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    SpecialFragmentViewModel specialFragmentViewModel();

    MatchedFragmentViewModel matchedFragmentViewModel();

}
