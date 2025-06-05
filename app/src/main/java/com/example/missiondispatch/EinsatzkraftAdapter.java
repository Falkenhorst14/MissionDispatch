package com.example.missiondispatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EinsatzkraftAdapter extends ArrayAdapter<Einsatzkraft> {

    private Context context;
    private List<Einsatzkraft> einsatzkraefte;

    public EinsatzkraftAdapter(@NonNull Context context, List<Einsatzkraft> einsatzkraefte) {
        super(context, 0, einsatzkraefte);
        this.context = context;
        this.einsatzkraefte = einsatzkraefte;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.einsatzkraft_list_item, parent, false);
        }

        Einsatzkraft einsatzkraft = einsatzkraefte.get(position);

        TextView tvName = convertView.findViewById(R.id.tvEinsatzkraftListName);
        String name = einsatzkraft.getVorname() + " " + einsatzkraft.getNachname();
        tvName.setText(name);

        return convertView;
    }
}
