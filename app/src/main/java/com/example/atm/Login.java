package com.example.atm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText txtUsername, txtNIP;
    String username, nipString;
    boolean valide, bloquer;
    int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtNIP = (EditText) findViewById(R.id.txtNIP);
        bloquer = false;
        compteur = 3;
    }

    public void setCompteur(int  valeur){
        compteur = valeur;
    }

    public void login(View v){
        username = txtUsername.getText().toString();
        nipString = txtNIP.getText().toString();

        if (username.equals("Admin") && nipString.equals("D001")){
            valide = true;
        }
        else {
            if (username.equals("") || nipString.equals("")) {
                Toast.makeText(Login.this, "Entrez tous les champs.", Toast.LENGTH_SHORT).show();
                return;
            } else if (nipString.length() != 5) {
                Toast.makeText(Login.this, "Entrez un NIP à 5 caractères.", Toast.LENGTH_SHORT).show();
                return;
            }
            valide = valider(username, nipString);
        }


        if (!valide){
            if (bloquer){
                Toast.makeText(Login.this,"Trois tentatives infructueuses. Veuillez réessayer plus tard.",Toast.LENGTH_SHORT).show();
                return;
            }
            compteur--;
            if (compteur == 0){
                Toast.makeText(Login.this,"C'était votre dernière tentative autorisée. Veuillez réessayer plus tard.",Toast.LENGTH_SHORT).show();
                bloquer = true;
                return;
            }
            Toast.makeText(Login.this,"Combinaison invalide. Il vous reste " + Integer.toString(compteur)  + " tentatives.",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Intent i;
            if (username.equals("Admin")){
                i = new Intent(this, Admin.class);
                startActivity(i);
            }
            else{
                i = new Intent(this, MainActivity.class);
                startActivity(i);
            }

        }
    }

    public boolean valider(String username, String nipString){
        int nip = Integer.parseInt(nipString);
        return Singleton.getInstance().guichet.validerUtilisateur(username, nip);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("COMPTEUR", compteur);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int compteur = savedInstanceState.getInt("COMPTEUR", 3);
        setCompteur(compteur);
    }
}
