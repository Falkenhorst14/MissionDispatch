package com.example.missiondispatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterPersonal extends RecyclerView.Adapter<RecyclerViewAdapterPersonal.ViewHolder> {

    private List<Einsatzkraft> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecyclerViewAdapterPersonal(Context context, List<Einsatzkraft> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewpersonal_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String kompletterName = mData.get(position).getVorname() + " " + mData.get(position).getNachname();
        holder.tvName.setText(kompletterName);
        holder.tvFuehrungsausbildung.setText(String.valueOf(mData.get(position).getFuehrungsAusbildung()));
        holder.checkbxEingesetzt.setChecked(mData.get(position).getImEinsatz());
        holder.tvAusbildungTauchen.setText(String.valueOf(mData.get(position).getTauchausbildungString(mData.get(position).getTauchAusbildung())));
        holder.tvAusbildungBoot.setText(String.valueOf(mData.get(position).getBootsAusbildung()));
        holder.tvAusbildungStroemungsrettung.setText(String.valueOf(mData.get(position).getStroemungsrettungsAusbildung()));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvFuehrungsausbildung;
        CheckBox checkbxEingesetzt;
        TextView tvAusbildungTauchen;
        TextView tvAusbildungBoot;
        TextView tvAusbildungStroemungsrettung;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvpersonalName);
            itemView.setOnClickListener(this);
            tvFuehrungsausbildung = itemView.findViewById(R.id.tvfuehrungsausbildung);
            itemView.setOnClickListener(this);
            checkbxEingesetzt = itemView.findViewById(R.id.checkboxEingesetzt);
            itemView.setOnClickListener(this);
            tvAusbildungTauchen = itemView.findViewById(R.id.tvausbildungTauchen);
            itemView.setOnClickListener(this);
            tvAusbildungBoot = itemView.findViewById(R.id.tvausbildungBoot);
            itemView.setOnClickListener(this);
            tvAusbildungStroemungsrettung = itemView.findViewById(R.id.tvausbildungStroemungsrettung);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getNachname();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
