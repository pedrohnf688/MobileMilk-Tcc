package com.eaj.ufrn.mobilemilk.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizarFazendaActivity extends AppCompatActivity {

    private EditText nomeFazenda;
    private EditText cnpjFazenda;
    private EditText cepFazenda;
    private EditText estadoFazenda;
    private EditText cidadeFazenda;
    private EditText bairroFazenda;
    private EditText numeroFazenda;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_fazenda);

        this.nomeFazenda = findViewById(R.id.nomeFazendaAtl);
        this.cnpjFazenda = findViewById(R.id.cnpjFazendaAtl);
        this.cepFazenda = findViewById(R.id.cepFazendaAtl);
        this.estadoFazenda = findViewById(R.id.estadoFazendaAtl);
        this.cidadeFazenda = findViewById(R.id.cidadeFazendaAtl);
        this.bairroFazenda = findViewById(R.id.bairroFazendaAtl);
        this.numeroFazenda = findViewById(R.id.numeroFazendaAtl);

        // Recebe as informações vindas de Perfil Fazendas
        this.bundle = getIntent().getExtras();

        this.setData(bundle);
    }

    // Confirma alterações
    public void confirmarAlteracoes(View v){

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Cliente cliente = new Cliente(prefs.getString("telefone1", "default")
                , prefs.getString("telefone2", "default")
                , prefs.getString("nome", "default")
                , prefs.getString("email", "default")
                , prefs.getString("cpf", "default")
                , EnumTipoPerfilUsuario.ROLE_CLIENTE
                , prefs.getString("accessId", "default"));

        Fazenda fazenda = new Fazenda(this.nomeFazenda.getText().toString()
                , this.cnpjFazenda.getText().toString()
                , this.cepFazenda.getText().toString()
                , "default"
                , this.estadoFazenda.getText().toString()
                , this.cidadeFazenda.getText().toString()
                , this.bairroFazenda.getText().toString()
                , this.numeroFazenda.getText().toString()
                , cliente, "default");

        Log.i("idFazenda", "idFazenda = " + bundle.getString("idFazenda"));

        Call<FazendaDto> call = new RetrofitConfig().getFazendaService().editarFazenda(
                bundle.getString("idFazenda")
                , prefs.getString("accessToken", "default")
                , fazenda);
        call.enqueue(new Callback<FazendaDto>() {
            @Override
            public void onResponse(Call<FazendaDto> call, Response<FazendaDto> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Fazenda Atualizada", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<FazendaDto> call, Throwable t) {

            }
        });

    }

    // Cancelar alterações
    public void cancelarAlteracoes(View v){
        finish();
    }

    // Seta as informações da fazenda selecionada;
    public void setData(Bundle bundle){
        this.nomeFazenda.setText(bundle.getString("nomeFazenda"));
        this.cnpjFazenda.setText(bundle.getString("cnpjFazenda"));
        this.cepFazenda.setText(bundle.getString("cepFazenda"));
        this.cidadeFazenda.setText(bundle.getString("cidadeFazenda"));
        this.estadoFazenda.setText(bundle.getString("estadoFazenda"));
        this.bairroFazenda.setText(bundle.getString("bairroFazenda"));
        this.numeroFazenda.setText(bundle.getString("numeroFazenda"));
    }
}
