package com.example.atm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CompteAdapter extends ArrayAdapter {
    private ArrayList<Compte> listeComptes;
    private Context context;

    private int viewRes;
    private Resources res;

    public CompteAdapter(Context context, int textViewResourceId, ArrayList<Compte> comptes){
        super(context, textViewResourceId, comptes);
        this.listeComptes = comptes;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if (view == null){
            LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Compte compte = listeComptes.get(position);
        if (compte != null){
            final TextView numeroView = (TextView)view.findViewById(R.id.numero_compte_ep);
            final TextView soldeView = (TextView)view.findViewById(R.id.solde_compte_ep);

            String numeroCompte = res.getString(R.string.numero_compte) + "" + compte.getNumeroCompte();
            String soldeCompte = res.getString(R.string.solde_compte) + "" + compte.getSoldeCompte() + "$";

            numeroView.setText(numeroCompte);
            soldeView.setText(soldeCompte);
        }
        return view;
    }

    @Override
    public int getCount(){
        return listeComptes.size();
    }

}
