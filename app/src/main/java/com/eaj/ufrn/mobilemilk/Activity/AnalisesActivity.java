package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.eaj.ufrn.mobilemilk.Adapters.AnaliseAdapter;
import com.eaj.ufrn.mobilemilk.Adapters.AnaliseQrCodeAdapter;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.Modelo.Amostra;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalisesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private AnaliseAdapter adapter;
    private List<SolicitacaoGetDto> listaSolicitacao = new ArrayList<SolicitacaoGetDto>();
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analises);

        getSupportActionBar().setTitle("Análises Solicitadas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.data = getIntent().getExtras();

        recycler = findViewById(R.id.listaAnalisesRecycler);



        //startActivity(new Intent(getActivity().getApplicationContext(), QrCodeActivity.class));
        recycler.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getApplicationContext(), recycler, new MeuRecyclerViewClickListener.OnItemClickListener(){
                    @Override
                    public void onItemLongClick(View view, int position) {
                        //Toast.makeText(getContext(), "Clicou LONGO", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity().getApplicationContext(), QrCodeActivity.class));
                    }

                    @Override
                    public void onItemClick(View view, int position){
                        startActivity(new Intent(getApplicationContext(), QrCodeActivity.class));


                    }
                })
        );


    }

    @Override
    protected void onStart(){
        super.onStart();

        final SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<SolicitacaoGetDto>> call = new RetrofitConfig().getClienteService().buscarSolicitacoes(
                prefs.getString("accessId", "default"), prefs.getString("accessToken", "default")
        );
        call.enqueue(new Callback<List<SolicitacaoGetDto>>() {
            @Override
            public void onResponse(Call<List<SolicitacaoGetDto>> call, Response<List<SolicitacaoGetDto>> response) {
                if ( response.isSuccessful()){
                    Log.i("response", "Tudo Certo");
                    listaSolicitacao = response.body();

                    Log.i("Antes kkkkk",listaSolicitacao.toString());

                    AnaliseQrCodeAdapter adapter = new AnaliseQrCodeAdapter(
                            listaSolicitacao.get(data.getInt("recyclerPosition")).getListaAnalise(),
                            getApplicationContext()
                    );

                    Log.i("Depois kkkkk",listaSolicitacao.toString());

                    recycler.setAdapter(adapter);
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recycler.setLayoutManager(layout);
                    recycler.setItemAnimator(new DefaultItemAnimator());


                }else{
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
