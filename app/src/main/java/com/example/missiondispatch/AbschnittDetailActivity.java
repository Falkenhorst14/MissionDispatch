package com.example.missiondispatch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class AbschnittDetailActivity extends AppCompatActivity {

    private int abschnittIDExtra;
    DBHandler dbHandler;
    private TextView tvAbschnittDetailName;
    private ListView tvAbschnittDetailList;
    private List<Einsatzkraft> listEinsatzkraefteInAbschnitt;

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

        abschnittIDExtra = intentVonVorschlaege.getIntExtra("abschnittID", -1);

        tvAbschnittDetailName = findViewById(R.id.tvAbschnittDetailName);
        tvAbschnittDetailList = findViewById(R.id.lvAbschnittDetailEinsatzkraefte);




    }
}