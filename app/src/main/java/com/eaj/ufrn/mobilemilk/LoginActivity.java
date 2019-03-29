package com.eaj.ufrn.mobilemilk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void criatConta(View v){
        Intent i = new Intent(this, InitActivity.class);
        Toast.makeText(this, "Vamos Criar a Sua Conta", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

}
