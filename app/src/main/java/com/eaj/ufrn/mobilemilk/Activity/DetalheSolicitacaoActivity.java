package com.eaj.ufrn.mobilemilk.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.AnaliseAdapter;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheSolicitacaoActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private AnaliseAdapter adapter;
    private List<SolicitacaoGetDto> listaSolicitacao = new ArrayList<>();
    private Bundle data;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detalhe_solicitacao);

        getSupportActionBar().setTitle("Análises Solicitadas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.data = getIntent().getExtras();

        this.recycler = findViewById(R.id.recyclerViewListarAnalises);

        recycler.addOnItemTouchListener(new MeuRecyclerViewClickListener(getApplicationContext(), recycler, new MeuRecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                final Analise sa = listaSolicitacao.get(data.getInt("recyclerPosition")).getListaAnalise().get(position);

            }

            @Override
            public void onItemClick(View view, int position) {
                Integer solicitacaoID = data.getInt("recyclerPosition");
                //Exibir os detalhes da da analise como produto, analises solicitadas e etc...
                Intent i = new Intent(getApplicationContext(), DetalharAnalise.class);
                i.putExtra("solicitacaoPosition",solicitacaoID);
                i.putExtra("analisePosition",position);
                startActivity(i);


            }

        }));

    }


    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<SolicitacaoGetDto>> call = new RetrofitConfig().getClienteService().buscarSolicitacoes(
          prefs.getString("accessId", "default"), prefs.getString("accessToken", "default")
        );
        call.enqueue(new Callback<List<SolicitacaoGetDto>>() {
            @Override
            public void onResponse(Call<List<SolicitacaoGetDto>> call, Response<List<SolicitacaoGetDto>> response) {
                if ( response.isSuccessful()){
                    Log.i("response", "Tudo Certo");
                    listaSolicitacao = response.body();

                    AnaliseAdapter adapter = new AnaliseAdapter(
                            listaSolicitacao.get(data.getInt("recyclerPosition")).getListaAnalise(),
                            getApplicationContext()
                    );

                    recycler.setAdapter(adapter);

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recycler.setLayoutManager(layout);
                    recycler.setItemAnimator(new DefaultItemAnimator());

                }
                else{
                    Log.i("response", "code: "+response.code());
                    Log.i("response", "Error Body: "+response.errorBody());
                    Log.i("response", "Message: "+response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SolicitacaoGetDto>> call, Throwable t) {
                Log.i("failure", "Tudo Certo"+t.getCause());
                Log.i("failure", "Tudo Certo"+t.getMessage());
            }
        });
    }


}
