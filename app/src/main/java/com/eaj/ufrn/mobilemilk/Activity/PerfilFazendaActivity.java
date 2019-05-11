package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFazendaActivity extends AppCompatActivity {

    private TextView nome;
    private TextView cnpj;
    private TextView cep;
    private TextView estado;
    private TextView cidade;
    private TextView bairro;
    private TextView numero;

    private Button editarFazenda;
    private Button excluirFazenda;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_perfil_fazenda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil da fazenda");

        this.bundle = getIntent().getExtras(); // Recebe as informaçães de fazenda de ListaFazendaActivity

        this.nome = findViewById(R.id.nomeFazenda);
        this.cnpj = findViewById(R.id.cnpjFazenda);
        this.cep = findViewById(R.id.cepFazenda);
        this.cidade = findViewById(R.id.cidadeFazenda);
        this.estado = findViewById(R.id.estadoFazenda);
        this.bairro = findViewById(R.id.bairroFazenda);
        this.numero = findViewById(R.id.numeroFazenda);

        this.setData(bundle);

    }

    // Seta as informações da fazenda selecionada;
    public void setData(Bundle bundle){
        this.nome.setText(bundle.getString("nomeFazenda"));
        this.cnpj.setText(bundle.getString("cnpjFazenda"));
        this.cep.setText(bundle.getString("cepFazenda"));
        this.cidade.setText(bundle.getString("cidadeFazenda"));
        this.estado.setText(bundle.getString("estadoFazenda"));
        this.bairro.setText(bundle.getString("bairroFazenda"));
        this.numero.setText(bundle.getString("numeroFazenda"));
    }

    // Editar informações
    public void editar(View v){
        Intent i = new Intent(getApplicationContext(), AtualizarFazendaActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    // Excruir Fazenda
    public void excluir(View v){

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<FazendaDto> call = new RetrofitConfig().getFazendaService().deletarFazenda(
                bundle.getString("idFazenda")
                , prefs.getString("accessToken", "default"));
        call.enqueue(new Callback<FazendaDto>() {
            @Override
            public void onResponse(Call<FazendaDto> call, Response<FazendaDto> response) {
                Intent i = new Intent();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Fazenda Excluida", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i("Error", "mensagem: " + response.errorBody());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<FazendaDto> call, Throwable t) {
                Log.i("Error", "mensagem: " + t.getMessage());
            }
        });

    }
}
