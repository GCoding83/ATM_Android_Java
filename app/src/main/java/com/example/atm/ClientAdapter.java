package com.example.atm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class ClientAdapter extends ArrayAdapter<Client>{

    private ArrayList<Client> listeClients;
    private Context context;

    private int viewRes;
    private Resources res;

    public ClientAdapter(Context context, int textViewResourceId, ArrayList<Client> clients){
        super(context, textViewResourceId, clients);
        this.listeClients = clients;
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
        final Client client = listeClients.get(position);
        if (client != null){
            final TextView nomView = (TextView)view.findViewById(R.id.nom_client);
            final TextView prenomView = (TextView)view.findViewById(R.id.prenom_client);

            String nomClient = res.getString(R.string.nom_client) + "" + client.getNom();
            String prenomClient = res.getString(R.string.prenom_client) + "" + client.getPrenom();

            nomView.setText(nomClient);
            prenomView.setText(prenomClient);
        }
        return view;
    }

    @Override
    public int getCount(){
        return listeClients.size();
    }
}
