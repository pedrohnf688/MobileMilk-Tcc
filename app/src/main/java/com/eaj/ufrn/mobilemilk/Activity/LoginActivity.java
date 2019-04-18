package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Activity.HomeActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Credencial;
import com.eaj.ufrn.mobilemilk.Modelo.Token;
import com.eaj.ufrn.mobilemilk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView usuario, senha;

    private Credencial credencial;

    private Integer tokenAcesso;

    private SharedPreferences prefs = getSharedPreferences("PREFS_NAME",MODE_PRIVATE);    // Usado para armazenar o token de acesso...
    private SharedPreferences.Editor editor = this.prefs.edit();                                // usado para editar o prefs da aplicação...

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
        if(this.senha.getText().toString() != null && this.usuario.getText().toString() != null){
            this.credencial = new Credencial(this.senha.getText().toString(), this.usuario.getText().toString());

            Log.i("credencial", "senha: " + credencial.getSenha() + " username: " + credencial.getUsername());

            Call<Token> call = Credencial.autenticacaoCliente(credencial);
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if(response.isSuccessful()){
                        Log.i("headers", ""+ response.headers());
                        Log.i("body", ""+ response.body());
                        Log.i("toString", ""+ response.toString());
                        Log.i("message", ""+ response.message());
                    }
                    else{
                        Log.i("xx", ""+ response.toString());
                        Log.i("xx", ""+ response.errorBody());
                        Log.i("xx", ""+ response.message());
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Log.i("yy", ""+ t.getStackTrace());
                    Log.i("yy", ""+ t.getMessage());
                    Log.i("yy", ""+ t.getCause());
                    Log.i("yy", ""+ t.toString());
                }
            });
        }
    }

    // guarda o acessToken
    public void saveToken(String acessToken){
        this.editor.putString("acessToken", acessToken);
        this.editor.commit();
    }

}
