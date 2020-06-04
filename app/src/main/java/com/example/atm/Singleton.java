package com.example.atm;

public class Singleton {

    private static Singleton instance = null;

    public Guichet guichet = new Guichet();

    private Singleton(){
        try {
            Client aruda = guichet.ajouterClient("Aruda", "Horracio", "Covid", 12345);
            Client francois = guichet.ajouterClient("Legault", "Francois", "PM", 99999);
            Client toufik = guichet.ajouterClient("Bellal", "Toufik", "Chanelle", 67890);
            guichet.ajouterCompteCheque(aruda, 2000);
            guichet.ajouterCompteCheque(francois, 50000);
            guichet.ajouterCompteEpargne(francois, 100000);
            guichet.ajouterCompteEpargne(toufik, 1000);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static Singleton getInstance() {
        if (instance == null)  {
            instance = new Singleton();
        }
        return instance;
    }
}
