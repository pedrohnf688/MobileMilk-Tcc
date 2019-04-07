package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.R;

public class CadastrarFazendaActivity extends AppCompatActivity {

    private TextView nomeFazenda;
    private TextView cnpjFazenda;
    private TextView cepFazenda;
    private TextView estadoFazenda;
    private TextView cidadeFazenda;
    private TextView bairroFazenda;
    private TextView numeroFazenda;

    private Button adicionar;
    private Button confirmar;
    private Button cancelar;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_fazendas);

        getSupportActionBar().setTitle(R.string.CadastrarFazenda); // Seta um titulo ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita a seta voltar

        this.nomeFazenda = findViewById(R.id.nomeFazenda);
        this.cnpjFazenda = findViewById(R.id.cnpjFazenda);
        this.cepFazenda = findViewById(R.id.cepFazenda);
        this.estadoFazenda = findViewById(R.id.estadoFazenda);
        this.cidadeFazenda = findViewById(R.id.cidadeFazenda);
        this.bairroFazenda = findViewById(R.id.bairroFazenda);
        this.numeroFazenda = findViewById(R.id.numeroFazenda);

        this.adicionar = findViewById(R.id.bAdicionar);
        this.confirmar = findViewById(R.id.confirmarCadastro);
        this.cancelar = findViewById(R.id.cancelarCadastro);
    }
}
