package com.example.atm;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListeEpargnes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_epargnes);

        CompteAdapter adapter = new CompteAdapter(this, R.layout.epargneliste_layout, Singleton.getInstance().guichet.getComptesEpargnes());
        final ListView list = (ListView)findViewById(R.id.maListeComptesEpargnes);
        final TextView quantite = (TextView)findViewById(R.id.nombreComptesEpargnes);
        String nombreComptes = getString(R.string.nombre_comptes) + " " + adapter.getCount();
        quantite.setText(nombreComptes);

        list.setAdapter(adapter);
    }
}
