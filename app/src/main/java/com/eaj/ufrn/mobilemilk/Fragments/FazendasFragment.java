package com.eaj.ufrn.mobilemilk.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eaj.ufrn.mobilemilk.Adapters.FazendaAdapter;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

public class FazendasFragment extends Fragment {

    List<Fazenda> listaFazendas = new ArrayList<>();

    public FazendasFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle saveInstanceState){

        Fazenda f = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null, null);
        Fazenda f1 = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null, null);
        listaFazendas.add(f);
        listaFazendas.add(f1);

        View view = inflate.inflate(R.layout.fragment_listar_fazenda, container, false);

        final RecyclerView recycler = view.findViewById(R.id.recyclerViewListarFazendas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);

        recycler.setLayoutManager(layout);
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setAdapter(new FazendaAdapter(listaFazendas, getContext()));
        return view;
    }
}
