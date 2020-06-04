package com.example.atm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ListeCheques extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_cheques);

        CompteAdapter adapter = new CompteAdapter(this, R.layout.chequeliste_layout, Singleton.getInstance().guichet.getComptesCheques());
        final ListView list = (ListView)findViewById(R.id.maListeComptes);
        final TextView quantite = (TextView)findViewById(R.id.nombreComptes);
        String nombreComptes = getString(R.string.nombre_comptes) + " " + adapter.getCount();
        quantite.setText(nombreComptes);

        list.setAdapter(adapter);
    }
}