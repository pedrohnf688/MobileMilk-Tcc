package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.R;

public class PerfilFazendaActivity extends AppCompatActivity {

    private TextView nome;
    private TextView cnpj;
    private TextView cep;
    private TextView estado;
    private TextView cidade;
    private TextView bairro;
    private TextView numero;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_perfil_fazenda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil da fazenda");

        this.nome = findViewById(R.id.nomeFazenda);
        this.cnpj = findViewById(R.id.cnpjFazenda);
        this.cep = findViewById(R.id.cepFazenda);
        this.cidade = findViewById(R.id.cidadeFazenda);
        this.estado = findViewById(R.id.estadoFazenda);
        this.bairro = findViewById(R.id.bairroFazenda);
        this.numero = findViewById(R.id.numeroFazenda);


    }
}
