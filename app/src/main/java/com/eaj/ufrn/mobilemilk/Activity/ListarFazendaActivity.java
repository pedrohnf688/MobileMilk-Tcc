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
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
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

        this.cadastrarFazenda = findViewById(R.id.actionButtonCadastrarFazenda);

        Fazenda f = new Fazenda("Fazenda 1", "123", "123", "Macaíba", "RN", "macaíba", "jundiaí", "200", null);
        Fazenda f1 = new Fazenda("Fazenda 2", "123", "123", "Macaíba", "RN", "macaíba", "jundiaí", "200", null);
        Fazenda f2 = new Fazenda("Fazenda 3", "123", "123", "Macaíba", "RN", "macaíba", "jundiaí", "200", null);
        Fazenda f3 = new Fazenda("Fazenda 4", "123", "123", "Macaíba", "RN", "macaíba", "jundiaí", "200", null);

        listaFazendas.add(f);
        listaFazendas.add(f1);
        listaFazendas.add(f2);
        listaFazendas.add(f3);

        final RecyclerView recycler = findViewById(R.id.recyclerViewListarFazendas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);

        recycler.setLayoutManager(layout);

        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setAdapter(new FazendaAdapter(listaFazendas, getApplicationContext()));

        // Implementação de Listener de cliques.
        recycler.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getApplicationContext(), recycler, new MeuRecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemLongClick(View view, int position) {
                        // Implementação opcional para desenvolvimento futuro ....
                    }

                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(), PerfilFazendaActivity.class);
                        startActivity(i);
                    }
                }));

    }

    public void CadastrarFazenda(View v){
        Intent t = new Intent(getApplicationContext(), CadastrarFazendaActivity.class);
        startActivity(t);
    }


    // onStart() -> Carrega as fazendas do Banco de Dados ...
    @Override
    protected void onStart(){
        super.onStart();

    }
}
