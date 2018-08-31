package com.clakestudio.pc.everyday.showdays;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.Day;

import java.util.ArrayList;


/**
 * Created by Jan on 8/31/2018.
 */


public class ShowDaysAdapter extends RecyclerView.Adapter<ShowDaysAdapter.ShowDaysViewHolder> {

    private ArrayList<Day> days;


    public static class ShowDaysViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitel;
        public TextView tvNote;


        public ShowDaysViewHolder(View itemView) {
            super(itemView);

            tvTitel = (TextView) itemView.findViewById(R.id.tvTitle);
            tvNote = (TextView) itemView.findViewById(R.id.tvNote);

        }
    }

    public ShowDaysAdapter(ArrayList<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public ShowDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.day, parent);
        ShowDaysViewHolder showDaysViewHolder = new ShowDaysViewHolder(view);
        return showDaysViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDaysViewHolder holder, int position) {
        holder.tvTitel.setText(days.get(position).getTitle());
        holder.tvNote.setText(days.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
