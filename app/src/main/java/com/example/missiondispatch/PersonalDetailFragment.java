package com.example.missiondispatch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        setupViewElements(view);
    }

    private void setupViewElements(@NonNull View view) {
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

        einsatzkraft = new Einsatzkraft(0,"Thomas", "Meier",
                "04232 25293", 3, 1, 1,
                1, 2, 2, "❚");
        //●

        //Abbilden von DB-Werten als int auf die Qualifikationsnamen
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
    }

}