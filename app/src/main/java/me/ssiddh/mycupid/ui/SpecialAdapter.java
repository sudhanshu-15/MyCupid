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

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MatchViewHolder> {

    List<MatchPerson> personList;
    LayoutInflater layoutInflater;
    private ClickListener clickListener;
    @Inject
    public Picasso picasso;

    public interface ClickListener {
        void onItemClicked(View view, int pos);
    }

    public void setOnItemClickListener(ClickListener listener) {
        clickListener = listener;
    }

    public SpecialAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SpecialAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View matchRow = layoutInflater.inflate(R.layout.single_card, parent, false);
        MatchViewHolder holder = new MatchViewHolder(matchRow);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialAdapter.MatchViewHolder holder, int position) {
        MatchPerson current = personList.get(position);
        holder.username.setText(current.getUsername());
        holder.liked.setSelected(current.getLiked());
        holder.ageLocation.setText(current.getAge() + " \u2022 " + current.getCityName() + ", " + current.getStateCode());
        holder.matchPercent.setText(current.getMatch()/100 + "%");
        picasso.get().load(current.getPhoto().getImage()).into(holder.picture);
        if(current.getLiked()){
            holder.cardView.setBackgroundColor(Color.parseColor("#F8BBD0"));
        }else {
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
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
                    return SpecialAdapter.this.personList.size();
                }

                @Override
                public int getNewListSize() {
                    return personList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    boolean isSame = SpecialAdapter.this.personList.get(oldItemPosition).getLiked() == personList.get(newItemPosition).getLiked();
//                    Log.d("DiffUtil", "areItemsTheSame: " + isSame);
                    return isSame;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MatchPerson person = personList.get(newItemPosition);
                    MatchPerson old = SpecialAdapter.this.personList.get(oldItemPosition);
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
        TextView liked;
        CardView cardView;

        public MatchViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.singleCard);
            username = itemView.findViewById(R.id.username);
            ageLocation = itemView.findViewById(R.id.agelocation);
            picture = itemView.findViewById(R.id.matchImage);
            matchPercent = itemView.findViewById(R.id.matchpercent);
            liked = itemView.findViewById(R.id.liked);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
//            Log.d("Inside Holder", "onClick: " + position);
            view.findViewById(R.id.singleCard).setBackgroundColor(Color.parseColor("#F8BBD0"));
            liked.setSelected(true);
            clickListener.onItemClicked(view, position);
        }
    }
}
