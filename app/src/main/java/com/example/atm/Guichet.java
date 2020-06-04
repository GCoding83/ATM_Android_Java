package com.example.atm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Guichet{
    private ArrayList<Client> clients;
    private ArrayList<Compte> comptesCheques;
    private ArrayList<Compte> comptesEpargnes;
    private Client clientActif;

    public Guichet() {
        clients = new ArrayList<>();
        comptesCheques = new ArrayList<>();
        comptesEpargnes = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Compte> getComptesCheques() {
        return comptesCheques;
    }

    public ArrayList<Compte> getComptesEpargnes() {
        return comptesEpargnes;
    }

    public void setComptesEpargnes(ArrayList<Compte> comptesEpargnes) {
        this.comptesEpargnes = comptesEpargnes;
    }

    public Client getClientActif() {
        return clientActif;
    }

    public void deconnecter(){
        this.clientActif = null;
    }

    public boolean validerUtilisateur(String username, int NIP){
        for (Client client : clients) {
            if (client.getUsername().equals(username)){
                if (client.getNIP() == NIP){
                    clientActif = client;
                    return true;
                }
            }
        }
        return false;
    }

    public double retraitCheque(Cheque compte, int montant, boolean autoriserNonDizaines) throws Exception {
            return compte.retrait(montant, autoriserNonDizaines);
    }

    public double retraitEpargne(Epargne compte, int montant, boolean autoriserNonDizaines) throws Exception {

        return compte.retrait(montant, autoriserNonDizaines);
    }

    public double depotCheque(Cheque compte, int montant) throws Exception {
        if (compte==null){
            throw new Exception("Ce compte n'existe pas.");
        }
        return compte.depot(montant);
    }

    public double depotEpargne(Epargne compte, int montant) throws Exception {
        if (compte==null){
            throw new Exception("Ce compte n'existe pas.");
        }
        return compte.depot(montant);
    }

    public void ajouterCompteEpargne(Client client, double solde) throws Exception {
        if (client==null){
            throw new Exception("Ce client n'existe pas.");
        }
        Epargne compte = new Epargne(client, solde);
        comptesEpargnes.add(compte);
        client.setEpargne(compte);
    }

    public double virement(Compte source, Compte destinataire, int montant) throws Exception{
        if (montant > 100000){
            throw new Exception("Maximum de 100,000$ par transaction");
        }
        else if (source.getSoldeCompte() < montant){
            throw new Exception("Le solde du compte source est insuffiant pour cette transaction");
        }
        else if (source instanceof Cheque && destinataire instanceof  Cheque || source instanceof Epargne && destinataire instanceof Epargne){
            throw new Exception("Seuls les virements entre des types de comptes différents sont autorisés.");
        }
        else{
            if (source instanceof Cheque){
                retraitCheque((Cheque)source, montant, true);
                depotEpargne((Epargne)destinataire, montant);
                return montant;
            }
            else{
                retraitEpargne((Epargne)source, montant, true);
                depotCheque((Cheque)destinataire, montant);
                return montant;
            }
        }
    }

    public void ajouterCompteCheque(Client client, double solde) throws Exception {
        if (client==null){
            throw new Exception("Ce client n'existe pas.");
        }
        Cheque compte = new Cheque(client, solde);
        comptesCheques.add(new Cheque(client, solde));
        client.setCheque(compte);
    }

    public Client ajouterClient(String nom, String prenom, String username, int NIP) throws Exception {
        if (nom.equals("") || prenom.equals("") || username.equals("")){
            throw new Exception("Champs vides.");
        }
        if (String.valueOf(NIP).length() != 5 ){
            throw new Exception("Les NIP doivent avoir une taille de 5.");
        }

        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                throw new Exception("Ce nom d'utilisateur existe déjà");
            }
        }

        Client client = new Client(nom, prenom, username, NIP);
        clients.add(client);
        return client;
    }

    public void payerInterets() {
        for (Compte compte : comptesEpargnes) {
            Epargne compteEp = (Epargne)compte;
            compteEp.paiementInteret();
        }
    }
}
