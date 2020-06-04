package com.example.atm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ListeClients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listes_clients);

        ClientAdapter adapter = new ClientAdapter(this, R.layout.clientsliste_layout, Singleton.getInstance().guichet.getClients());
        final ListView list = (ListView)findViewById(R.id.maListe);
        final TextView quantite = (TextView)findViewById(R.id.nombre);
        String nombreClients = getString(R.string.nombre_clients) + " " + adapter.getCount();
        quantite.setText(nombreClients);

        list.setAdapter(adapter);
    }
}
