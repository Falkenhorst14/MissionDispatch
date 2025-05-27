package com.example.missiondispatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterAbschnitte extends RecyclerView.Adapter<RecyclerViewAdapterAbschnitte.ViewHolder> {

    private List<Abschnitt> mData;
    private LayoutInflater mInflater;
    private RecyclerViewAdapterAbschnitte.ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapterAbschnitte(Context context, List<Abschnitt> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerViewAdapterAbschnitte.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewabschnitte_row, parent, false);
        return new RecyclerViewAdapterAbschnitte.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RecyclerViewAdapterAbschnitte.ViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvAbschnittId.setText(String.valueOf(mData.get(position).getId()));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvAbschnittId;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvabschnittName);
            itemView.setOnClickListener(this);
            tvAbschnittId = itemView.findViewById(R.id.tvabschnittId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }


    // convenience method for getting Abschnitt
    Abschnitt getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(RecyclerViewAdapterAbschnitte.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
