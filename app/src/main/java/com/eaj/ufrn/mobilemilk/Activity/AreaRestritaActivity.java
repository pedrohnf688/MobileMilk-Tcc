package com.eaj.ufrn.mobilemilk.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.AnalisesSolicitadasAdapter;
import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.ModeloDTO.AmostraDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaRestritaActivity extends AppCompatActivity {

    TextView nomeCliente, nomeFazenda, coleta, origemLeite,numeroAmostra, especie, produto, observacao, amostraStatus;
    RecyclerView recyclerView;
    Button sairQrCode, pesquisarQrCode;
    final Activity activity= this;
    private List<AnalisesSolicitadas> analisesSolicitadasArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_restrita);


        nomeCliente = findViewById(R.id.nomeClienteQrCode);
        nomeFazenda = findViewById(R.id.nomeFazendaQrCode);
        coleta = findViewById(R.id.coletaQrCode);
        origemLeite = findViewById(R.id.origemLeiteQrCode);
        numeroAmostra = findViewById(R.id.numeroAmostraQrCode);
        especie = findViewById(R.id.especieQrCode);
        produto = findViewById(R.id.produtoQrCode);
        observacao = findViewById(R.id.observacaoQrCode);
        amostraStatus = findViewById(R.id.amostraStatusQrCode);

        recyclerView = findViewById(R.id.recyclerViewAnalisesSolicitadasQrCode);

        sairQrCode = findViewById(R.id.sairQrCode);
        pesquisarQrCode = findViewById(R.id.pesquisarQrCode);


        sairQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pesquisarQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("SCAN");
                intentIntegrator.setCameraId(0);
                intentIntegrator.initiateScan();

            }
        });

    }


    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() != null) {
                //alert(intentResult.getContents());

                SharedPreferences prefs = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

                Call<AmostraDto> call = new RetrofitConfig().getAmostraService().buscarAmostraInfo(intentResult.getContents(),
                        prefs.getString("accessToken", "default"));

                call.enqueue(new Callback<AmostraDto>() {
                    @Override
                    public void onResponse(Call<AmostraDto> call, Response<AmostraDto> response) {
                        if (response.isSuccessful()) {

                            AmostraDto a = response.body();

                            if (a.getData().getDataColeta() != null && a.getData().getEspecie() != null &&
                                    a.getData().getNumeroAmostra() != 0 && a.getData().getOrigemLeite().toString() != null &&
                                    a.getData().getProdutos().toString() != null || response.body().toString() != null) {

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                                String strDate = formatter.format(a.getData().getDataColeta());

                                Log.i("AmostraTo",a.getData().toString());
                                coleta.setText(strDate);
                                numeroAmostra.setText(String.valueOf(a.getData().getNumeroAmostra()));
                                observacao.setText(a.getData().getObservacao());
                                origemLeite.setText(a.getData().getOrigemLeite().toString());
                                produto.setText(a.getData().getProdutos().toString().replace("[","").replace("]","").replace("_"," "));
                                especie.setText(a.getData().getEspecie().toString());
                                nomeCliente.setText(a.getData().getNomeCliente());
                                nomeFazenda.setText(a.getData().getNomeFazenda());

                                if(a.getData().getObservacao() != null){
                                    amostraStatus.setText("Amostra coletada");
                                }else if(a.getData().getObservacao() == null){
                                    amostraStatus.setText("Amostra não coletada");
                                }


                                analisesSolicitadasArrayList = a.getData().getAnalisesSolicitadas();

                                AnalisesSolicitadasAdapter adapter = new AnalisesSolicitadasAdapter(
                                        analisesSolicitadasArrayList,getApplicationContext()
                                );

                                recyclerView.setAdapter(adapter);

                                RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(layout);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());



                            }else{
                                Toast.makeText(AreaRestritaActivity.this, "Falha ao ler o QrCode", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AreaRestritaActivity.this, "Falha ao ler o QrCode", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AmostraDto> call, Throwable t) {
                        Toast.makeText(AreaRestritaActivity.this, "Falha ao ler o QrCode", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        }
    }


}