package com.example.missiondispatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDetailFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DBHandler dbHandler;

    private Einsatzkraft einsatzkraft;
    private TextView tvName;
    private TextView tvFuehrungsausbildung;
    private TextView tvFuehrungsausbildungSub;

    private CheckBox checkbxImEinsatz;
    private TextView tvTelefon;
    private TextView tvTauchausbildung;
    private TextView tvBootsausbildung;
    private TextView tvStroemungsrettungsausbildung;
    private TextView tvWrdausbildung;
    private TextView tvSanausbildung;
    private TextView tvFunkausbildung;
    private int bundledId;
    private Spinner spnAbschnitt;
    private TextView tvAbschnitt;
    private Button btnAbschnittzuweisen;
    private Button btnAbschnittentfernen;
    private int selectedAbschnittForZuweisung;

    public PersonalDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalDetailFragment newInstance(String param1, String param2) {
        PersonalDetailFragment fragment = new PersonalDetailFragment();
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

        Bundle bundle = this.getArguments();
        if ((bundle != null) && (bundle.getInt("einsatzkraftID", -1) != 0)) {
            bundledId = bundle.getInt("einsatzkraftID", -1);
        }
        //Toast.makeText(getActivity(), "You selected" + bundledId, Toast.LENGTH_SHORT).show();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnAbschnitt = view.findViewById(R.id.spnAbschnitt);
        btnAbschnittzuweisen = view.findViewById(R.id.btnAbschnittZuweisen);
        btnAbschnittentfernen = view.findViewById(R.id.btnAbschnittEntfernen);
        setupViewElements(view);



        //Es muss im Folgenden ein String-Array befüllt werden mit allen Abschnitten

        List<Abschnitt> abschnitte = dbHandler.getAllAbschnitte();
        List<String> abschnittNamen = new ArrayList<>();

        for (Abschnitt a : abschnitte )
        {
            abschnittNamen.add(a.getName());
        }
        String[] items = new String[abschnittNamen.size()];
        for (int i = 0; i < abschnittNamen.size(); i++)
        {
            items[i] = abschnittNamen.get(i);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, items);
        spnAbschnitt.setAdapter(spinnerAdapter);
        spnAbschnitt.setOnItemSelectedListener(this);
        spnAbschnitt.setPrompt("Neuen Abschnitt auswählen");

        checkbxImEinsatz.setOnCheckedChangeListener((buttonView, isChecked) -> {
            einsatzkraft.setImEinsatz(isChecked);
            new Thread(() -> {
                dbHandler.updateEinsatzkraftStatus(einsatzkraft);
                einsatzkraft = dbHandler.getEinsatzkraft(einsatzkraft.getId());
                checkbxImEinsatz.setChecked(einsatzkraft.getImEinsatz());
            }).start();
        });


        btnAbschnittzuweisen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                einsatzkraft.setAbschnittId(selectedAbschnittForZuweisung);
                dbHandler.updateEinsatzkraftAbschnitt(einsatzkraft);
                setupViewElements();
            }
        });

        btnAbschnittentfernen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                einsatzkraft.setAbschnittId(0);
                dbHandler.updateEinsatzkraftAbschnitt(einsatzkraft);
                setupViewElements();
            }
        });
    }

    private void setupViewElements(@NonNull View view) {
        findViewElements(view);

        einsatzkraft = dbHandler.getEinsatzkraft(bundledId);

        //Abbilden von DB-Werten als int auf die Qualifikationsnamen
        //TODO Geburtsdatum abfragen
        tvName.setText(einsatzkraft.getVorname() + " " + einsatzkraft.getNachname());
        tvFuehrungsausbildung.setText(einsatzkraft.getFuehrungsAusbildung().toString());
        tvFuehrungsausbildungSub.setText(einsatzkraft.getFuehrungsausbildungString(einsatzkraft.getFuehrungsAusbildung()));
        checkbxImEinsatz.setChecked(einsatzkraft.getImEinsatz());
        tvTelefon.setText(einsatzkraft.getTelefon());
        tvTauchausbildung.setText(einsatzkraft.getTauchausbildungString(einsatzkraft.getTauchAusbildung()));
        tvBootsausbildung.setText(einsatzkraft.getBootsausbildungString(einsatzkraft.getBootsAusbildung()));
        tvStroemungsrettungsausbildung.setText(einsatzkraft.getStroemungsrettungsausbildungString(einsatzkraft.getStroemungsrettungsAusbildung()));
        tvWrdausbildung.setText(einsatzkraft.getWrdausbildungString(einsatzkraft.getWrdAusbildung()));
        tvSanausbildung.setText(einsatzkraft.getSanausbildungString(einsatzkraft.getSanAusbildung()));
        tvFunkausbildung.setText(einsatzkraft.getFunkausbildungString(einsatzkraft.getFunkAusbildung()));
        if (einsatzkraft.getAbschnittId() == 0) {
            tvAbschnitt.setText("Kein Abschnitt");
        }
        else
        {
            tvAbschnitt.setText((dbHandler.getAbschnitt(einsatzkraft.getAbschnittId())).getName());
        }


        /*einsatzkraft = new Einsatzkraft(0,"Thomas", "Meier",
                "04232 25293", "01.01.1970", 3, 1, 1,
                1, 2, 2, "❚");*/
        //●
    }

    //Ueberladen zur Aktualisierung
    private void setupViewElements() {

        einsatzkraft = dbHandler.getEinsatzkraft(bundledId);

        //Abbilden von DB-Werten als int auf die Qualifikationsnamen
        //TODO Geburtsdatum abfragen
        tvName.setText(einsatzkraft.getVorname() + " " + einsatzkraft.getNachname());
        tvFuehrungsausbildung.setText(einsatzkraft.getFuehrungsAusbildung().toString());
        tvFuehrungsausbildungSub.setText(einsatzkraft.getFuehrungsausbildungString(einsatzkraft.getFuehrungsAusbildung()));
        checkbxImEinsatz.setChecked(einsatzkraft.getImEinsatz());
        tvTelefon.setText(einsatzkraft.getTelefon());
        tvTauchausbildung.setText(einsatzkraft.getTauchausbildungString(einsatzkraft.getTauchAusbildung()));
        tvBootsausbildung.setText(einsatzkraft.getBootsausbildungString(einsatzkraft.getBootsAusbildung()));
        tvStroemungsrettungsausbildung.setText(einsatzkraft.getStroemungsrettungsausbildungString(einsatzkraft.getStroemungsrettungsAusbildung()));
        tvWrdausbildung.setText(einsatzkraft.getWrdausbildungString(einsatzkraft.getWrdAusbildung()));
        tvSanausbildung.setText(einsatzkraft.getSanausbildungString(einsatzkraft.getSanAusbildung()));
        tvFunkausbildung.setText(einsatzkraft.getFunkausbildungString(einsatzkraft.getFunkAusbildung()));
        if (einsatzkraft.getAbschnittId() == 0) {
            tvAbschnitt.setText("Kein Abschnitt");
        }
        else {
            tvAbschnitt.setText((dbHandler.getAbschnitt(einsatzkraft.getAbschnittId())).getName());
        }

        /*einsatzkraft = new Einsatzkraft(0,"Thomas", "Meier",
                "04232 25293", "01.01.1970", 3, 1, 1,
                1, 2, 2, "❚");*/
        //●
    }

    private void findViewElements(@NonNull View view)
    {
        tvName = view.findViewById(R.id.tvpersonalName);
        tvFuehrungsausbildung = view.findViewById(R.id.tvfuehrungsausbildung);
        tvFuehrungsausbildungSub = view.findViewById(R.id.tvfuehrungSub);
        checkbxImEinsatz = view.findViewById(R.id.checkboxEingesetzt);
        tvTelefon = view.findViewById(R.id.tvtelefon);
        tvTauchausbildung = view.findViewById(R.id.tvausbildungTauchen);
        tvBootsausbildung = view.findViewById(R.id.tvausbildungBoot);
        tvStroemungsrettungsausbildung = view.findViewById(R.id.tvausbildungStroemungsrettung);
        tvWrdausbildung = view.findViewById(R.id.tvausbildungWasserrettung);
        tvSanausbildung = view.findViewById(R.id.tvausbildungMedizin);
        tvFunkausbildung = view.findViewById(R.id.tvausbildungFunk);
        tvAbschnitt = view.findViewById(R.id.tvAktuellerAbschnitt);

        dbHandler = new DBHandler(getActivity().getApplicationContext());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //wenn der gewaehlte Text gebraucht wird
        //String choice = parent.getItemAtPosition(position).toString();
        int selectedPosition = parent.getSelectedItemPosition();
        selectedAbschnittForZuweisung = dbHandler.getAbschnittByPosition(selectedPosition).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}