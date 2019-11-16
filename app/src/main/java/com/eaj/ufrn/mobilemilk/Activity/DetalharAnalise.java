package com.eaj.ufrn.mobilemilk.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Adapters.AnaliseAdapter;
import com.eaj.ufrn.mobilemilk.Adapters.AnalisesSolicitadasAdapter;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalharAnalise extends AppCompatActivity {

    private RecyclerView recycler;
    private AnaliseAdapter adapter;
    private List<SolicitacaoGetDto> listaSolicitacao = new ArrayList<>();
    private Bundle data;
    TextView textVieworigemdoLeite, textViewproduto, textViewEspecie, textViewNumerodeAmostras;
    Button gerarPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_analise);


        getSupportActionBar().setTitle("Detalhamento das Análises");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.data = getIntent().getExtras();

        this.recycler = findViewById(R.id.recyclerAnalisesSolicitadas);
        textViewEspecie = findViewById(R.id.textoEspecie);
        textViewNumerodeAmostras = findViewById(R.id.textoNumerodeAmostras);
        textVieworigemdoLeite = findViewById(R.id.textoOrigemdoLeite);
        textViewproduto = findViewById(R.id.textoProduto);


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

                    AnalisesSolicitadasAdapter adapter = new AnalisesSolicitadasAdapter(
                            listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaAnalise()
                                    .get(data.getInt("analisePosition")).getAnalisesSolicitadas(),
                            getApplicationContext()
                    );

                    recycler.setAdapter(adapter);

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recycler.setLayoutManager(layout);
                    recycler.setItemAnimator(new DefaultItemAnimator());

                    textVieworigemdoLeite.setText(listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaAnalise()
                            .get(data.getInt("analisePosition")).getOrigemLeite().toString().replace("_"," "));

                    textViewproduto.setText(listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaAnalise()
                            .get(data.getInt("analisePosition")).getProdutos().toString().replace("[","").replace("]",""));

                    textViewEspecie.setText(listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaAnalise()
                            .get(data.getInt("analisePosition")).getEspecie().toString().replace("_"," "));

                    textViewNumerodeAmostras.setText(listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaAnalise()
                            .get(data.getInt("analisePosition")).getQuantidadeAmostras().toString());

//                    if(listaSolicitacao.get(data.getInt("solicitacaoPosition")).getListaLaudoMedia().size() == 0){
//                        gerarPdf.setEnabled(false);
//                    }else{
//                        gerarPdf.setEnabled(true);
//                    }


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
