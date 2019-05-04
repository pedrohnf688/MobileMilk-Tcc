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
import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarFazendaActivity extends AppCompatActivity {

    List<FazendaDto> listaFazendas = new ArrayList<>();

    private FloatingActionButton cadastrarFazenda;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_listar_fazenda);

        getSupportActionBar().setTitle("Fazendas");

        this.cadastrarFazenda = findViewById(R.id.actionButtonCadastrarFazenda);

        final RecyclerView recycler = findViewById(R.id.recyclerViewListarFazendas);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);

        recycler.setLayoutManager(layout);

        recycler.setItemAnimator(new DefaultItemAnimator());

        //recycler.setAdapter(new FazendaAdapter(listaFazendas, getApplicationContext()));

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

        Log.i("onstart", "Entrou aqui");
        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<FazendaDto>> call = new RetrofitConfig()
                                        .getClienteService()
                                        .buscarFazendas(prefs.getString("accessToken", "default")
                                            , prefs.getString("accessId", "default"));
        call.enqueue(new Callback<List<FazendaDto>>() {
            @Override
            public void onResponse(Call<List<FazendaDto>> call, Response<List<FazendaDto>> response) {
                if(response.isSuccessful()){
                    listaFazendas = response.body();
                    Log.i("listaFazendas", "deu certo");
                    Toast.makeText(ListarFazendaActivity.this, "Deu certo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FazendaDto>> call, Throwable t) {
                Log.i("Error404", "cause: " + t.getCause());
                Log.i("Error404", "message: " + t.getMessage());
                Toast.makeText(ListarFazendaActivity.this, "Erro ao listar fazendas", Toast.LENGTH_SHORT).show();
            }
        });
        Log.i("onstart", "Encerrou aqui");
    }
}
