package com.eaj.ufrn.mobilemilk.Activity;

import android.app.AlertDialog;
import android.content.Context;
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

        /*
         *   Implementação RECYCLERVIEW
         *
         *
         * */
        this.recycler = findViewById(R.id.recyclerViewListarAnalises);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        this.recycler.setLayoutManager(layout);
        this.recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.addOnItemTouchListener(new MeuRecyclerViewClickListener(getApplicationContext(), recycler, new MeuRecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {

                final Analise sa = listaSolicitacao.get(data.getInt("recyclerPosition")).getListaAnalise().get(position);


                AlertDialog.Builder alertDeletarAnalise = new AlertDialog.Builder(DetalheSolicitacaoActivity.this);

                final View dialogView = LayoutInflater.from(DetalheSolicitacaoActivity.this).inflate(R.layout.dialog_deletar_analise,null);
                alertDeletarAnalise.setView(dialogView);

                Button buttonAnaliseNAO = dialogView.findViewById(R.id.buttonAnaliseNAO);
                Button buttonAnaliseSIM = dialogView.findViewById(R.id.buttonAnaliseSIM);


                final AlertDialog alertDialog = alertDeletarAnalise.create();
                alertDialog.show();


                buttonAnaliseSIM.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

                        Call<Analise> call2 = new RetrofitConfig().getAnaliseService().deletarAnalisePorId(sa.getId(), prefs.getString("accessToken", "default"));

                        call2.enqueue(new Callback<Analise>() {
                            @Override
                            public void onResponse(Call<Analise> call, Response<Analise> response) {
                                if(response.isSuccessful()){
                                    alertDialog.dismiss();

                                    Toast.makeText(getApplicationContext(), "Análise excluida com sucesso", Toast.LENGTH_SHORT).show();
                                }else{
                                    alertDialog.dismiss();

                                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<Analise> call, Throwable t) {
                                alertDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Falha na exclução da analise", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });


                buttonAnaliseNAO.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Ação cancelada", Toast.LENGTH_SHORT).show();

                    }
                });

                ///Toast.makeText(DetalheSolicitacaoActivity.this, "Fazendo o deletar Analise", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View view, int position) {

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
