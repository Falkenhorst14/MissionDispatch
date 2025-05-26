package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment implements RecyclerViewAdapterPersonal.ItemClickListener, RecyclerViewAdapterPersonal.OnItemCheckedChangeListener {

    RecyclerViewAdapterPersonal adapter;
    private List<Einsatzkraft> einsatzkraefte;
    DBHandler dbHandler;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dbHandler = new DBHandler(getActivity());
        einsatzkraefte = dbHandler.getAllEinsatzkraefte();
        //loadEinsatzkraefteData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // View created and data should be loaded
        if (einsatzkraefte != null && !einsatzkraefte.isEmpty()) {
            setupRecyclerView(view);
        }
    }

    private void loadEinsatzkraefteData()
    {
        einsatzkraefte = new ArrayList<Einsatzkraft>(10);
        Einsatzkraft einsatzkraft1 = new Einsatzkraft(0,"Thomas", "Meier",
                "04232 25293", "01.01.1970", 3, 1, 1,
                1, 2, 2, "● ●");
        Einsatzkraft einsatzkraft2 = new Einsatzkraft(1, "Mirko", "Bachmann",
                "04232 25293", "01.01.1970", 2, 2, 0,
                1, 3, 2, "");
        Einsatzkraft einsatzkraft3 = new Einsatzkraft(2, "Jens", "Schulze",
                "04232 25293", "01.01.1970", 3, 1, 0,
                1, 3, 2, "●");
        Einsatzkraft einsatzkraft4 = new Einsatzkraft(3, "Vanessa", "Schmidt",
                "04232 25293", "01.01.1970", 4, 2, 1,
                2, 3, 1, "●");
        Einsatzkraft einsatzkraft5 = new Einsatzkraft(4, "Christian", "Anders",
                "04232 25293", "01.01.1970", 1, 1, 2,
                2, 3, 1, "❚");


        einsatzkraefte.add(einsatzkraft1);
        einsatzkraefte.add(einsatzkraft2);
        einsatzkraefte.add(einsatzkraft3);
        einsatzkraefte.add(einsatzkraft4);
        einsatzkraefte.add(einsatzkraft5);
    }

    //RecyclerView erstellen und fuellen
    private void setupRecyclerView(@NonNull View view) {

        RecyclerView recyclerView = view.findViewById(R.id.rvPersonalUebersicht);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); // view.getContext() wegen Fragment
        adapter = new RecyclerViewAdapterPersonal(getActivity(), einsatzkraefte, this);
        adapter.setClickListener(this); // evtl. ueberfluessig wenn Listener in Kosntruktor uebergeben
        try {
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.d("AdapterError", e.getMessage());
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        if (einsatzkraefte == null || position < 0 || position >= einsatzkraefte.size()) {
            // Handle invalid position or data not ready
            return;
        }

        Einsatzkraft selectedEinsatzkraft = einsatzkraefte.get(position);
        Intent intent = new Intent(getActivity(), PersonalActivity.class);
        intent.putExtra("einsatzkraftID", selectedEinsatzkraft.getId());
        startActivity(intent);
    }

    @Override
    public void onItemCheckedChanged(int position, boolean isChecked) {
        Einsatzkraft einsatzkraft = einsatzkraefte.get(position);
        einsatzkraft.setImEinsatz(isChecked);

        // Hier Datenbank aktualisieren
        new Thread(() -> {
            dbHandler.updateEinsatzkraftStatus(einsatzkraft);
        }).start();
    }

}