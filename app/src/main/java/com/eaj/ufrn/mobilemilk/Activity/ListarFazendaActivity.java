package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.eaj.ufrn.mobilemilk.Adapters.FazendaAdapter;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

public class ListarFazendaActivity extends AppCompatActivity {

    List<Fazenda> listaFazendas = new ArrayList<>();

    private FloatingActionButton cadastrarFazenda;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_listar_fazenda);

        getSupportActionBar().setTitle("Fazendas");

        Fazenda f = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null);
        Fazenda f1 = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null);
        Fazenda f2 = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null);
        Fazenda f3 = new Fazenda("Fazenda 1", null, null, null, null, null, null, null, null);

        listaFazendas.add(f);
        listaFazendas.add(f1);
        listaFazendas.add(f2);
        listaFazendas.add(f3);

        final RecyclerView recycler = findViewById(R.id.recyclerViewListarFazendas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);

        recycler.setLayoutManager(layout);
        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setAdapter(new FazendaAdapter(listaFazendas, getApplicationContext()));

        this.cadastrarFazenda = findViewById(R.id.actionButtonCadastrarFazenda);

    }

    public void CadastrarFazenda(View v){
        Intent t = new Intent(getApplicationContext(), CadastrarFazendaActivity.class);
        startActivity(t);
    }

}
