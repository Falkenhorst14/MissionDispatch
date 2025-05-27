package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AbschnitteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbschnitteFragment extends Fragment implements RecyclerViewAdapterAbschnitte.ItemClickListener {

    RecyclerViewAdapterAbschnitte adapter;
    private RecyclerView recyclerView;
    private List<Abschnitt> abschnitte;
    DBHandler dbHandler;
    private Button btnerstellen;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AbschnitteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbschnitteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AbschnitteFragment newInstance(String param1, String param2) {
        AbschnitteFragment fragment = new AbschnitteFragment();
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
        abschnitte = dbHandler.getAllAbschnitte();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abschnitte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // View created and data should be loaded
        if (abschnitte != null && !abschnitte.isEmpty()) {
            setupRecyclerView(view);
        }
        else {
            Toast.makeText(getActivity(),"Keine Abschnitte vorhanden, erstelle den ersten!", Toast.LENGTH_SHORT).show();
        }
        btnerstellen = getActivity().findViewById(R.id.btnErstellenAbschnitt);

        btnerstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtAbschnittName = getActivity().findViewById(R.id.edtAbschnittName);
                String name = edtAbschnittName.getText().toString();
                dbHandler.addNewAbschnitt(name);
                loadAllAbschnitte();
            }
        });
    }

    //Erstellt RecyclerView und befuellt
    private void setupRecyclerView(@NonNull View view) {

        recyclerView = view.findViewById(R.id.rvAbschnittUebersicht);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); // view.getContext() wegen Fragments
        adapter = new RecyclerViewAdapterAbschnitte(getActivity(), abschnitte);
        adapter.setClickListener(this); // evtl. ueberfluessig wenn Listener in Kosntruktor uebergeben
        try {
            recyclerView.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Log.d("AdapterError", e.getMessage());
        }
    }


    //Innerer Teil vorerst ueberfluessig ohne Einstellungen TODO: Spaeter evtl. als Loeschen verwenden
    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(getActivity(), "Du hast an Position " + position + " geklickt.", Toast.LENGTH_SHORT).show();
        /*if (abschnitte == null || position < 0 || position >= abschnitte.size()) {
            // Handle invalid position or data not ready
            return;
        }

        Abschnitt selectedAbschnitt = abschnitte.get(position);
        Intent intent = new Intent(getActivity(), PersonalActivity.class);
        intent.putExtra("abschnittID", selectedAbschnitt.getId());
        startActivity(intent);*/
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() != null)
        {
            setupRecyclerView(getView());
        }
        else { Log.d("ViewError", "Fehler beim getten der View."); }

    }

    private void loadAllAbschnitte()
    {
        List<Abschnitt> abschnitte = dbHandler.getAllAbschnitte();
        adapter = new RecyclerViewAdapterAbschnitte(getActivity(), abschnitte);
        recyclerView.setAdapter(adapter);
    }

}