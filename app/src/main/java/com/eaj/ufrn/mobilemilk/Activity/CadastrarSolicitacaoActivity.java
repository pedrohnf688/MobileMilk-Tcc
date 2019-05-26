package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarSolicitacaoActivity extends AppCompatActivity {

    private Spinner spinnerFazenda;

    private List<Fazenda> listaFazendas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_solicitacao);

        getSupportActionBar().setTitle(R.string.cadastrar); // set text action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //

        this.spinnerFazenda = findViewById(R.id.spinnerFazenda);
    }

    // Avança para a tela de cadastrara amostras.
    public void avançar(View v){

        /*
        *  Verifica se há fazendas cadastradas
        * */
        if(this.listaFazendas.isEmpty()){
            Toast.makeText(getApplicationContext(), "Não há fazendas cadastradas", Toast.LENGTH_LONG).show();
            return;
        }

        // buscar o cnpj da fazenda solicitada
        String nome = this.spinnerFazenda.getSelectedItem().toString(); // Fazenda Selecionada.
        String cnpj = null;                                             // Cnpj da fazenda selecionada.

        // Buscando Cnpj
        for(Fazenda f: listaFazendas){
            if(f.getNome().equals(nome))
                cnpj = f.getCpfcnpj();
        }

        //Log.i("Nomefazenda", "nome: "+ nome + "Cnpj: " + cnpj);
        Intent t = new Intent(getApplicationContext(), ListarAnalisesActivity.class);
        t.putExtra("nomeFazenda", nome);
        t.putExtra("cnpjFazenda", cnpj);
        finish();
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

                    String[] arrayFazenda = new String[listaFazendas.size()];

                    for(int i = 0; i < listaFazendas.size(); i++){
                        arrayFazenda[i] = listaFazendas.get(i).getNome();
                    }
                    // ArrayAdapter para listar Fazendas
                    ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayFazenda);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerFazenda.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Fazenda>> call, Throwable t) {
                Log.i("Error404", "cause: " + t.getCause());
                Log.i("Error404", "message: " + t.getMessage());
            }
        });
    }

}
