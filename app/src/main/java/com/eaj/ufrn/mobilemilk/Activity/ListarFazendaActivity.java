package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.FazendaAdapter;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarFazendaActivity extends AppCompatActivity {

    List<Fazenda> listaFazendas = new ArrayList<>();

    private FloatingActionButton cadastrarFazenda;

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_listar_fazenda);

//        listaFazendas.add(f);
//        listaFazendas.add(f1);

        getSupportActionBar().setTitle("Fazendas");

        this.cadastrarFazenda = findViewById(R.id.actionButtonCadastrarFazenda);

        recycler = findViewById(R.id.recyclerViewListarFazendas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);

        recycler.setLayoutManager(layout);

        recycler.setItemAnimator(new DefaultItemAnimator());

        // Implementação de Listener de cliques.
        recycler.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getApplicationContext(), recycler, new MeuRecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemLongClick(View view, int position) {
                        // Implementação opcional para desenvolvimento futuro ....
                        Toast.makeText(getApplicationContext(), "LONG", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getApplicationContext(), PerfilFazendaActivity.class);
                        // Salva as informações da fazenda clicada
                        i.putExtra("nomeFazenda", listaFazendas.get(position).getNome());
                        i.putExtra("cnpjFazenda", listaFazendas.get(position).getCpfcnpj());
                        i.putExtra("cepFazenda", listaFazendas.get(position).getCep());
                        i.putExtra("estadoFazenda", listaFazendas.get(position).getEstado());
                        i.putExtra("cidadeFazenda", listaFazendas.get(position).getCidade());
                        i.putExtra("bairroFazenda", listaFazendas.get(position).getBairro());
                        i.putExtra("numeroFazenda", listaFazendas.get(position).getNumero());
                        i.putExtra("idFazenda", listaFazendas.get(position).getId());
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

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<Fazenda>> call = new RetrofitConfig()
                .getClienteService()
                .buscarFazendas(prefs.getString("accessId", "default")
                        , prefs.getString("accessToken", "default"));
        call.enqueue(new Callback<List<Fazenda>>() {
            @Override
            public void onResponse(Call<List<Fazenda>> call, Response<List<Fazenda>> response) {
                listaFazendas.clear();
                if(response.isSuccessful()){
                    listaFazendas = response.body();
                    recycler.setAdapter(new FazendaAdapter(listaFazendas, getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Fazenda>> call, Throwable t) {
                Log.i("Error404", "cause: " + t.getCause());
                Log.i("Error404", "message: " + t.getMessage());
                Toast.makeText(ListarFazendaActivity.this, "Erro ao listar fazendas", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
