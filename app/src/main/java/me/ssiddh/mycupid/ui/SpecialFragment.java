package me.ssiddh.mycupid.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.ssiddh.mycupid.R;
import me.ssiddh.mycupid.data.model.MatchPerson;
import me.ssiddh.mycupid.viewmodel.SpecialFragmentViewModel;

public class SpecialFragment extends Fragment{

    private RecyclerView specialRecyclerView;
    private MatchesAdapter matchesAdapter;
    private SpecialFragmentViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.special_fragment, container, false);
        specialRecyclerView = rootView.findViewById(R.id.recyclerView);
        setupData();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SpecialFragmentViewModel.class);
        viewModel.getSpecailBlendList().observe(this, specialList -> {
            if (specialList != null){
                matchesAdapter.setPersonList(specialList);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setupData() {
        List<MatchPerson> matchList = new ArrayList<>();
        MatchPerson.Photo photo = new MatchPerson.Photo("36x36/684x684/2/15743311334557165678.jpg");
        MatchPerson p1 = new MatchPerson("5592586755333955055", "bklyn2356", 27, 8715, "NY", "Brooklyn", false, photo);
        matchList.add(p1);
        matchList.add(p1);
        matchList.add(p1);
        matchesAdapter = new MatchesAdapter(getActivity(), matchList);
        specialRecyclerView.setAdapter(matchesAdapter);
        specialRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

}
