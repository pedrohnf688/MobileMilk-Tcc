package com.eaj.ufrn.mobilemilk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_inicio);

        getSupportActionBar().hide(); // Remove o ActionBar do AppTheme default do Projeto.
    }

    public void loginAuth(View v){
        Intent i = new Intent(this, LoginActivity.class);
        //Toast.makeText(this, "En", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    public void cadastrar(View v){
        Intent i = new Intent(this, CadastrarActivity.class);
        startActivity(i);
    }

}
