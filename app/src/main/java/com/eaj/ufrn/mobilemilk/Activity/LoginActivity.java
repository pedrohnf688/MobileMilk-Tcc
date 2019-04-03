package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Activity.HomeActivity;
import com.eaj.ufrn.mobilemilk.R;


public class LoginActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView usuario, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(R.string.Login); // Adiciona um Title ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // habilita a seta voltar

        this.senha = findViewById(R.id.senha);
        this.usuario = findViewById(R.id.usuario);

    }

    public void autenticar(View v){
        if(this.senha.getText().toString().equals("admin") && this.usuario.getText().toString().equals("admin"))
            finish();
            startActivity(new Intent(this, HomeActivity.class));
    }
}
