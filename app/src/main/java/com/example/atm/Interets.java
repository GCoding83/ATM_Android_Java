package com.example.atm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Interets extends AppCompatActivity {

    TextView txtDescription;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interets);
        init();
    }

    public void init() {
        String afficher = "";
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        afficher += getString(R.string.nombre_comptes_epargnes) + " " + Integer.toString(nombreComptesEpargnes()) + ".\n\n";
        afficher += getString(R.string.sommaire_comptes_epargnes) + " " + Double.toString(sommeEpargnes()) + "$.\n\n";
        afficher += getString(R.string.sommaire_interets) + " " + Double.toString(sommeInterets()) + "$.";

        txtDescription.setText(afficher);
    }

    public int nombreComptesEpargnes(){
        return Singleton.getInstance().guichet.getComptesEpargnes().size();
    }

    public double sommeEpargnes(){
        double somme = 0;
        for (Compte compte : Singleton.getInstance().guichet.getComptesEpargnes()) {
            somme += compte.getSoldeCompte();
        }
        return Math.floor(somme * 100)/100;
    }

    public double sommeInterets(){
        double sommeEpargnes = sommeEpargnes();
        return Math.floor(sommeEpargnes * Epargne.TAUX_INTERET * 100)/100;
    }

    public void payerInterets(View v){
        Singleton.getInstance().guichet.payerInterets();
        init();
        Toast.makeText(Interets.this, "Paiement effectué avec succès. Les nouveaux données sont affichées.", Toast.LENGTH_SHORT).show();
    }

    public void annuler(View v){
        i = new Intent(this, Admin.class);
        startActivity(i);
    }




}

