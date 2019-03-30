package com.eaj.ufrn.mobilemilk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setTitle("Cadastrar-se"); // Adiciona um Title ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adiciona a ação voltar ao ActionBar
    }
}
