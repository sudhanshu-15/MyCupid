package me.ssiddh.mycupid.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.ssiddh.mycupid.R;
import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.viewmodel.SpecialFragmentViewModel;

public class MatchedFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.special_fragment, container, false);
//        specialRecyclerView = rootView.findViewById(R.id.recyclerView);
//        matchesAdapter = new MatchesAdapter(getActivity());
//        specialRecyclerView.setAdapter(matchesAdapter);
//        specialRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        setupData();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        viewModel = ViewModelProviders.of(this).get(SpecialFragmentViewModel.class);
//        viewModel.getSpecialBlendList().observe(this, specialList -> {
//            Log.d("Fragment", "onActivityCreated: This works");
//            for(MatchPerson p : specialList){
//                Log.d("Fragment", "onActivityCreated: " + p.getUserid());
//            }
//            matchesAdapter.setPersonList(specialList);
//        });
    }
}
