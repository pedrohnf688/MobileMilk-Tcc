package com.eaj.ufrn.mobilemilk.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.FazendaAdapter;
import com.eaj.ufrn.mobilemilk.Fragments.DatePickerFragment;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext())
                    .setMessage("Não fazendas cadastradas, gostaria de cadastrar ?")
                    .setTitle("Temos um problema")
                    .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent t = new Intent(getApplicationContext(), CadastrarFazendaActivity.class);
                            startActivity(t);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.AgoraNao, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();

            return;

        }

        // buscar o cnpj da fazenda solicitada
        String nome = this.spinnerFazenda.getSelectedItem().toString(); // Fazenda Selecionada.
        String cnpj = null;                                             // Cnpj da fazenda selecionada.

        // Buscando Cnpj
        for(Fazenda f: listaFazendas){
            if(f.getNome().equals(nome))
                cnpj = f.getCnpj();
        }

        //Log.i("Nomefazenda", "nome: "+ nome + "Cnpj: " + cnpj);
        Intent t = new Intent(getApplicationContext(), ListarAnalisesActivity.class);
        t.putExtra("nomeFazenda", nome);
        t.putExtra("cnpjFazenda", cnpj);
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
