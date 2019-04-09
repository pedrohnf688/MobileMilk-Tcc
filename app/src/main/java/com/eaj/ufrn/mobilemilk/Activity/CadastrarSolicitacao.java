package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.eaj.ufrn.mobilemilk.R;

public class CadastrarSolicitacao extends AppCompatActivity {

    private Spinner spinnerFazenda;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_solicitacao);

        getSupportActionBar().setTitle(R.string.cadastrar); // set text action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //

        // Implementação do Spinner de Fazendas do cliente
        this.spinnerFazenda = findViewById(R.id.spinnerFazenda);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerFazenda,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerFazenda.setAdapter(adapter);

    }
}
