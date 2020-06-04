package com.example.atm;

public class Epargne extends Compte {
    public static final double TAUX_INTERET = 0.0125;

    public Epargne(Client client, double soldeCompte){
        super(client, soldeCompte);
    }

    public void paiementInteret(){
        soldeCompte += soldeCompte * TAUX_INTERET;
    }
}
