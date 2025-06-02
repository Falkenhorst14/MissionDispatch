package com.example.missiondispatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterPersonal extends RecyclerView.Adapter<RecyclerViewAdapterPersonal.ViewHolder> {

    private List<Einsatzkraft> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private OnItemCheckedChangeListener listener;

    // data is passed into the constructor
    RecyclerViewAdapterPersonal(Context context, List<Einsatzkraft> data, OnItemCheckedChangeListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.listener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewpersonal_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the (e.g.) TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String kompletterName = mData.get(position).getVorname() + " " + mData.get(position).getNachname();
        holder.tvName.setText(kompletterName);
        holder.tvFuehrungsausbildung.setText(mData.get(position).getFuehrungsAusbildung());
        holder.checkbxEingesetzt.setChecked(mData.get(position).getImEinsatz());
        holder.tvAusbildungTauchen.setText(mData.get(position).getTauchausbildungString(mData.get(position).getTauchAusbildung()));
        holder.tvAusbildungBoot.setText(mData.get(position).getBootsausbildungString(mData.get(position).getBootsAusbildung()));
        holder.tvAusbildungStroemungsrettung.setText(mData.get(position).getStroemungsrettungsausbildungString(mData.get(position).getStroemungsrettungsAusbildung()));
        holder.bind(mData.get(position));
        holder.tvAbschnitt.setText(String.valueOf(mData.get(position).getAbschnittId()));

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
        TextView tvAbschnitt;

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
            tvAbschnitt = itemView.findViewById(R.id.tvAbschnittAuswahl);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        public void bind(Einsatzkraft einsatzkraft)
        {
            checkbxEingesetzt.setOnCheckedChangeListener(null);
            checkbxEingesetzt.setChecked(einsatzkraft.getImEinsatz());

            checkbxEingesetzt.setOnCheckedChangeListener((buttonView, isChecked) ->
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemCheckedChanged(position, isChecked);
                }
            });
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

    public interface OnItemCheckedChangeListener {
        void onItemCheckedChanged(int position, boolean isChecked);
    }


}
