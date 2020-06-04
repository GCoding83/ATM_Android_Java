package com.example.atm;

import android.content.Context;
import android.widget.Toast;

public class Compte {

    protected Client client;
    protected int numeroCompte;
    protected double soldeCompte;
    protected int compteur = 9000;

    public Compte(Client client, double soldeCompte) {
        this.client = client;
        this.numeroCompte = compteur++;
        this.soldeCompte = soldeCompte;
    }

    public Client getClient() {
        return client;
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public double getSoldeCompte() {
        return Math.floor(soldeCompte * 100)/100;
    }

    public double retrait(int montant, boolean autoriserNonDizaines) throws Exception{
        if (montant > soldeCompte){
            throw new Exception("Le solde est insuffisant pour retirer un tel montant.");
        }
        else if (montant > 1000){
            throw new Exception("Maximum de 1000$ par retrait.");
        }
        else if (autoriserNonDizaines == false && montant%10!=0){
            throw new Exception("Les retraits doivent s'effectuer par tranches de 10$.");
        }
        else{
            soldeCompte -= montant;
            return soldeCompte;
        }
    }

    public double depot(double montant){
        soldeCompte += montant;
        return soldeCompte;
    }
}

