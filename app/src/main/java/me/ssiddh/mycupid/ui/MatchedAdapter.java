package me.ssiddh.mycupid.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import me.ssiddh.mycupid.R;
import me.ssiddh.mycupid.data.model.MatchPerson;

public class MatchedAdapter extends RecyclerView.Adapter<MatchedAdapter.MatchViewHolder> {

    List<MatchPerson> personList;
    LayoutInflater layoutInflater;
    private static MatchedAdapter.ClickListener clickListener;
    @Inject
    public Picasso picasso;

    public interface ClickListener {
        void onItemClicked(View view, int pos);
    }

    public void setOnItemClickListener(MatchedAdapter.ClickListener listener) {
        clickListener = listener;
    }

    public MatchedAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MatchedAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matchRow = layoutInflater.inflate(R.layout.single_card, parent, false);
        MatchedAdapter.MatchViewHolder holder = new MatchedAdapter.MatchViewHolder(matchRow);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchedAdapter.MatchViewHolder holder, int position) {
        MatchPerson current = personList.get(position);
        holder.username.setText(current.getUsername());
        holder.ageLocation.setText(current.getAge() + " \u2022 " + current.getCityName() + ", " + current.getStateCode());
        holder.matchPercent.setText(current.getMatch()/100 + "%");
        picasso.get().load(current.getPhoto().getImage()).into(holder.picture);
    }

    @Override
    public long getItemId(int position) {
        return personList.get(position).getId();
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
                    return MatchedAdapter.this.personList.size();
                }

                @Override
                public int getNewListSize() {
                    return personList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    boolean isSame = MatchedAdapter.this.personList.get(oldItemPosition).getLiked() == personList.get(newItemPosition).getLiked();
//                    Log.d("DiffUtil", "areItemsTheSame: " + isSame);
                    return isSame;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MatchPerson person = personList.get(newItemPosition);
                    MatchPerson old = MatchedAdapter.this.personList.get(oldItemPosition);
//                    Log.d("DiffUtil", "areContentsTheSame: old " + old.getId());
//                    Log.d("DiffUtil", "areContentsTheSame: new " + person.getId());
//                    Log.d("DiffUtil", "areContentsTheSame: old liked " + old.getLiked());
//                    Log.d("DiffUtil", "areContentsTheSame: new liked" + person.getLiked());
                    boolean isContentSame = person.getId() == old.getId() && person.getLiked() == old.getLiked();
//                    Log.d("DiffUtil", "areContentsTheSame: " + isContentSame);
                    return isContentSame;
                }
            });
            this.personList = personList;
            result.dispatchUpdatesTo(this);
        }
    }

    class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username;
        TextView ageLocation;
        ImageView picture;
        TextView matchPercent;
        CardView cardView;

        public MatchViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.singleCard);
            username = itemView.findViewById(R.id.username);
            ageLocation = itemView.findViewById(R.id.agelocation);
            picture = itemView.findViewById(R.id.matchImage);
            matchPercent = itemView.findViewById(R.id.matchpercent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            Log.d("Inside Holder", "onClick: " + position);
            clickListener.onItemClicked(view, position);
        }
    }
}
