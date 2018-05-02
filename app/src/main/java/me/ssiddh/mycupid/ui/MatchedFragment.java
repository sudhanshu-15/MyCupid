package me.ssiddh.mycupid.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.ssiddh.mycupid.R;
import me.ssiddh.mycupid.di.Injectable;
import me.ssiddh.mycupid.viewmodel.MatchedFragmentViewModel;
import me.ssiddh.mycupid.viewmodel.SpecialFragmentViewModel;

public class MatchedFragment extends Fragment implements Injectable {

    private RecyclerView matchedRecyclerView;
    private MatchedAdapter matchedAdapter;
    private MatchedFragmentViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.special_fragment, container, false);
        matchedRecyclerView = rootView.findViewById(R.id.recyclerView);
        matchedAdapter = new MatchedAdapter(getActivity());
        matchedAdapter.setOnItemClickListener((view, pos) -> {
            //TODO
        });
        matchedRecyclerView.setAdapter(matchedAdapter);
        matchedRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchedFragmentViewModel.class);
        viewModel.getMatchedList().observe(this, specialList -> {
            matchedAdapter.setPersonList(specialList);
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
}
