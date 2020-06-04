package com.example.atm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);
    }

    public void lancerClients(View v){
        i = new Intent(this, ListeClients.class);
        startActivity(i);
    }
    public void lancerCheques(View v){
        i = new Intent(this, ListeCheques.class);
        startActivity(i);

    }
    public void lancerEpargnes(View v){
        i = new Intent(this, ListeEpargnes.class);
        startActivity(i);

    }
    public void lancerInterets(View v){
        i = new Intent(this, Interets.class);
        startActivity(i);
    }

    public void deconnecterAdmin(View v) {
        i = new Intent(this, Login.class);
        startActivity(i);
    }

}
