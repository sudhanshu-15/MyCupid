package me.ssiddh.mycupid.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import me.ssiddh.mycupid.R;
import me.ssiddh.mycupid.data.model.MatchPerson;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder> {

    List<MatchPerson> personList;
    LayoutInflater layoutInflater;

    public MatchesAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MatchesAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matchRow = layoutInflater.inflate(R.layout.single_card, parent, false);
        MatchViewHolder holder = new MatchViewHolder(matchRow);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.MatchViewHolder holder, int position) {
        MatchPerson current = personList.get(position);
        holder.username.setText(current.getUsername());
        holder.ageLocation.setText(current.getAge() + " \u2022 " + current.getCityName() + ", " + current.getStateCode());
        holder.matchPercent.setText(current.getMatch()/100 + "%");
        Picasso.get().load(current.getPhoto().getImage()).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return personList == null ? 0 : personList.size();
    }

    public List<MatchPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<MatchPerson> personList) {
        if (this.personList == null) {
            this.personList = personList;
            notifyItemRangeInserted(0, personList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MatchesAdapter.this.personList.size();
                }

                @Override
                public int getNewListSize() {
                    return personList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MatchesAdapter.this.personList.get(oldItemPosition).getUserid() == personList.get(newItemPosition).getUserid();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MatchPerson person = personList.get(newItemPosition);
                    MatchPerson old = personList.get(oldItemPosition);
                    return person.getUserid() == old.getUserid() && Objects.equals(person.getUsername(), old.getUsername());
                }
            });
            this.personList = personList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MatchViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView ageLocation;
        ImageView picture;
        TextView matchPercent;

        public MatchViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            ageLocation = itemView.findViewById(R.id.agelocation);
            picture = itemView.findViewById(R.id.matchImage);
            matchPercent = itemView.findViewById(R.id.matchpercent);
        }
    }
}
