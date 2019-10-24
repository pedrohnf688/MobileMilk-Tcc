package com.eaj.ufrn.mobilemilk.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Modelo.Amostra;
import com.eaj.ufrn.mobilemilk.ModeloDTO.AmostraDto;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QrCodeActivity extends AppCompatActivity {

    private TextView coleta;
    private TextView numeroAmostra;
    private TextView observacao;
    private TextView origem;
    private TextView produtos;
    private TextView especie;
    private Button leitor,btFinalizar;

    final Activity activity= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        coleta = findViewById(R.id.coleta);
        numeroAmostra = findViewById(R.id.numeroAmostra);
        observacao = findViewById(R.id.observacao);
        origem = findViewById(R.id.origemLeite);
        produtos = findViewById(R.id.produtos);
        especie = findViewById(R.id.especie);

        leitor = findViewById(R.id.scanQR);
        btFinalizar = findViewById(R.id.btFinalizar);

        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        leitor.setOnClickListener(new View.OnClickListener() {
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

       final IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult != null){
            if (intentResult.getContents() !=  null){
                alert(intentResult.getContents());

                SharedPreferences prefs = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

                Call<AmostraDto> call = new RetrofitConfig().getAmostraService().buscarAmostra(intentResult.getContents(),
                        prefs.getString("accessToken", "default")
                );

                call.enqueue(new Callback<AmostraDto>() {
                    @Override
                    public void onResponse(Call<AmostraDto> call, Response<AmostraDto> response) {
                        if(response.isSuccessful()){

                            AmostraDto a = response.body();

                            coleta.setText(a.getData().getDataColeta().toString());
                            numeroAmostra.setText(String.valueOf(a.getData().getNumeroAmostra()));
                            observacao.setText(a.getData().getObservacao());
                            origem.setText(a.getData().getOrigemLeite().toString());
                            produtos.setText(a.getData().getProdutos().toString());
                            especie.setText(a.getData().getEspecie().toString());



                            AlertDialog.Builder alertObservacao = new AlertDialog.Builder(QrCodeActivity.this);

                            final View dialogView = LayoutInflater.from(QrCodeActivity.this).inflate(R.layout.dialog_atualizar_amostra,null);
                            alertObservacao.setView(dialogView);

                            final TextInputEditText observacaoInput = dialogView.findViewById(R.id.observacaoAmostra);
                            Button sim = dialogView.findViewById(R.id.buttonSIM);
                            Button nao = dialogView.findViewById(R.id.buttonNAO);

                            final AlertDialog alertDialog = alertObservacao.create();
                            alertDialog.show();

                            sim.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Amostra amostra = new Amostra();

                                    String obs = observacaoInput.getText().toString().trim();

                                    if(!TextUtils.isEmpty(obs)){

                                        amostra.setFinalizada(true);
                                        amostra.setObservacao(obs);

                                        SharedPreferences prefs2 = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
                                        Call<Amostra> call2 = new RetrofitConfig().getAmostraService().atualizarAmostra(intentResult.getContents(), amostra, prefs2.getString("accessToken", "default"));

                                        call2.enqueue(new Callback<Amostra>() {
                                            @Override
                                            public void onResponse(Call<Amostra> call, Response<Amostra> response) {
                                                if(response.isSuccessful()) {
                                                    alertDialog.dismiss();
                                                    Toast.makeText(QrCodeActivity.this, "Amostra atualizada com sucesso", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Log.i("Erro Atualizar Amostra",""+response.toString());
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Amostra> call, Throwable t) {
                                                alertDialog.dismiss();
                                                Toast.makeText(QrCodeActivity.this, "Falha ao atualizar a amostra", Toast.LENGTH_SHORT).show();

                                                Log.i("Erro amostra Detalhes",""+t.getMessage());
                                            }
                                        });


                                    }else{
                                        Toast.makeText(QrCodeActivity.this, "Está vazia a observação", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                            nao.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                    Toast.makeText(QrCodeActivity.this, "Ação cancelada", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<AmostraDto> call, Throwable t) {
                        Toast.makeText(QrCodeActivity.this, "Falha ao Carregar os Dados ", Toast.LENGTH_SHORT).show();
                    }
                });


            }else{
                alert("Scan cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}
