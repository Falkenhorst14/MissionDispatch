package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AbschnittDetailActivity extends AppCompatActivity {

    private int abschnittIDExtra;
    DBHandler dbHandler;
    private TextView tvAbschnittDetailName;
    private ListView lvAbschnittDetailList;
    private List<Einsatzkraft> listEinsatzkraefteInAbschnitt;
    private Button btnAbschnittLoeschen;
    private Abschnitt aktuellerAbschnitt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abschnitt_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentVonVorschlaege = getIntent();
        dbHandler = new DBHandler(this);

        abschnittIDExtra = intentVonVorschlaege.getIntExtra("abschnittID", -1);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintAbschnittDetailList);

        aktuellerAbschnitt = dbHandler.getAbschnitt(abschnittIDExtra);

        tvAbschnittDetailName = findViewById(R.id.tvAbschnittDetailName);
        tvAbschnittDetailName.setText(aktuellerAbschnitt.getName());

        lvAbschnittDetailList = findViewById(R.id.lvAbschnittDetailEinsatzkraefte);
        listEinsatzkraefteInAbschnitt = dbHandler.getAllEinsatzkraefte(abschnittIDExtra);
        btnAbschnittLoeschen = findViewById(R.id.btnLoeschenAbschnitt);

        if (listEinsatzkraefteInAbschnitt.size() <= 0)
        {
            Toast.makeText(this, "Im Abschnitt sind aktuell keine Kräfte eingesetzt.", Toast.LENGTH_LONG).show();
            constraintLayout.setVisibility(View.GONE);
        }
        else {
            EinsatzkraftAdapter adapter = new EinsatzkraftAdapter(this, listEinsatzkraefteInAbschnitt);
            lvAbschnittDetailList.setAdapter(adapter);

            lvAbschnittDetailList.setOnItemClickListener((parent, view, position, id) -> {
                Einsatzkraft ek = listEinsatzkraefteInAbschnitt.get(position);
                int ekId = ek.getId();

                Toast.makeText(this, "Es war Id " + ekId, Toast.LENGTH_SHORT).show();
            });
        }

        btnAbschnittLoeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteAbschnitt(aktuellerAbschnitt.getId());
                Intent intent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });


    }
}
