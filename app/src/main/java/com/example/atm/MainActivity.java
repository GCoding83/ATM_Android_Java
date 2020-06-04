package com.example.atm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnC, btnDel, btnLogout, btnEtat, btnSoumettre;
    TextView txtBonjour, txtMontant, txtEtats, txtVirement;
    String username, nipString;
    RadioButton rDepot, rRetrait, rVirement, rCheque, rEpargne;
    String montantString, chequeString, epargneString, soldeString;
    int montant;
    boolean soldeAffiche;

    enum TypeOperation {
        DEPOT,
        RETRAIT,
        VIREMENT
    }

    enum TypeCompte {
        CHEQUE,
        EPARGNE
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        txtBonjour = (TextView) findViewById(R.id.txtBonjour);
        String prenom = Singleton.getInstance().guichet.getClientActif().getPrenom();
        String nom = Singleton.getInstance().guichet.getClientActif().getNom();
        txtBonjour.setText("Bonjour " + prenom + " " + nom);
        txtMontant = (TextView) findViewById(R.id.txtMontant);
        txtEtats = (TextView) findViewById(R.id.txtEtats);
        txtVirement = (TextView)findViewById(R.id.txtVirement);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnC = (Button) findViewById(R.id.btnC);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnLogout = (Button) findViewById(R.id.btnLogOut);
        btnEtat = (Button) findViewById(R.id.btnEtat);
        btnSoumettre = (Button) findViewById(R.id.btnSoumettre);
        rDepot = (RadioButton) findViewById(R.id.rbtnDepot);
        rDepot.setChecked(true);
        rRetrait = (RadioButton) findViewById(R.id.rbtnRetrait);
        rVirement = (RadioButton) findViewById(R.id.rbtnVirement);
        rCheque = (RadioButton) findViewById(R.id.rbtnCheque);
        rEpargne = (RadioButton) findViewById(R.id.rbtnEpargne);
        rCheque.setChecked(true);
        if (Singleton.getInstance().guichet.getClientActif().getCheque() == null) {
            rCheque.setEnabled(false);
            rEpargne.setChecked(true);
            rVirement.setEnabled(false);
        }
        if (Singleton.getInstance().guichet.getClientActif().getEpargne() == null) {
            rEpargne.setEnabled(false);
            rVirement.setEnabled(false);
        }
        montantString = "";
        chequeString = "";
        epargneString = "";
        soldeString = "";
        soldeAffiche = false;
    }

    public void afficherChiffresEntres(View v) {
        montantString += v == btn0 ? "0" : v == btn1 ? "1" : v == btn2 ? "2" : v == btn3 ? "3" : v == btn4 ? "4" : v == btn5 ? "5" : v == btn6 ? "6" : v == btn7 ? "7" : v == btn8 ? "8" : v == btn9 ? "9" : "";
        if (v == btnDel) {
            montantString = "";
        } else if (v == btnC) {
            if (montantString.length() > 0) {
                montantString = montantString.substring(0, montantString.length() - 1);
            }
        }
        txtMontant.setText(montantString);
    }

    public void deconnecter(View v) {
        Singleton.getInstance().guichet.deconnecter();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void afficherSoldes(View w) {
        soldeAffiche = true;
        if (Singleton.getInstance().guichet.getClientActif().getCheque() != null) {
            chequeString = "";
            chequeString += "Solde compte chèque: " + Singleton.getInstance().guichet.getClientActif().getCheque().getSoldeCompte() + "$\n";
        }
        if (Singleton.getInstance().guichet.getClientActif().getEpargne() != null) {
            epargneString = "";
            epargneString += "Solde compte épargne: " + Singleton.getInstance().guichet.getClientActif().getEpargne().getSoldeCompte() + "$";
        }

        txtEtats.setText(chequeString + epargneString);
    }

    public void toggleTexteVirement(View v){

        if (v == rVirement){
            txtVirement.setVisibility(View.VISIBLE);
        }
        else{
            txtVirement.setVisibility(View.INVISIBLE);
            rCheque.setChecked(true);
        }
    }

    public void operation(View v) {
        if (txtMontant.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Veuillez entrer un montant.", Toast.LENGTH_SHORT).show();
            return;
        }
        montant = Integer.parseInt(txtMontant.getText().toString());
        TypeOperation operation = rDepot.isChecked() ? TypeOperation.DEPOT : rRetrait.isChecked() ? TypeOperation.RETRAIT : TypeOperation.VIREMENT;
        TypeCompte compte = rCheque.isChecked() ? TypeCompte.CHEQUE : TypeCompte.EPARGNE;

        try {
            double solde = traitement(operation, compte, montant);
            String afficher = "";
            afficher += operation == TypeOperation.VIREMENT ? "Virement complété" : operation == TypeOperation.DEPOT ? "Dépôt complété." : "Retrait complété.";
            if (operation == TypeOperation.VIREMENT || compte == TypeCompte.CHEQUE){
                afficher += "\nSolde chèque: " + Singleton.getInstance().guichet.getClientActif().getCheque().getSoldeCompte() + "$.";
            }
            if (operation == TypeOperation.VIREMENT || compte == TypeCompte.EPARGNE) {
                afficher += "\nSolde épargne: " + Singleton.getInstance().guichet.getClientActif().getEpargne().getSoldeCompte() + "$.";
            }

            Toast.makeText(MainActivity.this, afficher, Toast.LENGTH_SHORT).show();
            if (soldeAffiche)   afficherSoldes(btnEtat);
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally
        {
            afficherChiffresEntres(btnDel);
        }
    }

    public java.lang.Double traitement(TypeOperation o, TypeCompte c, int montant) throws Exception {
        Client client = Singleton.getInstance().guichet.getClientActif();
        if (o == TypeOperation.DEPOT) {
            return c == TypeCompte.CHEQUE ? Singleton.getInstance().guichet.depotCheque(client.getCheque(), montant) : Singleton.getInstance().guichet.depotEpargne(client.getEpargne(), montant);
        } else if (o == TypeOperation.RETRAIT) {
            return c == TypeCompte.CHEQUE ? Singleton.getInstance().guichet.retraitCheque(client.getCheque(), montant, false) : Singleton.getInstance().guichet.retraitEpargne(client.getEpargne(), montant, false);
        }
        return c == TypeCompte.CHEQUE ? Singleton.getInstance().guichet.virement(client.getCheque(), client.getEpargne(), montant) : Singleton.getInstance().guichet.virement(client.getEpargne(), client.getCheque(), montant);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean afficheV = rVirement.isChecked() ? true : false;
        boolean afficheS = soldeAffiche ? true : false;
        outState.putBoolean("AFFICHE_VIREMENT", afficheV);
        outState.putBoolean("AFFICHE_SOLDES", afficheS);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean afficheV = savedInstanceState.getBoolean("AFFICHE_VIREMENT", false);
        boolean afficheS = savedInstanceState.getBoolean("AFFICHE_SOLDES", false);

        if (afficheV)   toggleTexteVirement(rVirement);
        if (afficheS)   afficherSoldes(btnEtat);
    }

}
