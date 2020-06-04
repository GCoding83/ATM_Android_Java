package com.example.atm;

import java.util.ArrayList;

public class Client {
    private String nom;
    private String prenom;
    private String username;
    private int NIP;
    private Epargne epargne;
    private Cheque cheque;

    public Client(String nom, String prenom, String username, int NIP) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.NIP = NIP;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public int getNIP() {
        return NIP;
    }

    public Epargne getEpargne() {
        return epargne;
    }

    public void setEpargne(Epargne epargne) {
        this.epargne = epargne;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }
}
