package com.eaj.ufrn.mobilemilk;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Credencial;

public class CadastrarActivity extends AppCompatActivity {

    TextView nomeUsuario;
    TextView emailUsuario;
    TextView senhaUsuario;
    TextView usuariousuario;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar);

        getSupportActionBar().setTitle("Cadastrar-se"); // Adiciona um Title ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adiciona a ação voltar ao ActionBar

        // Atribuindo os obejtos View aos seus devidos componentes
        this.nomeUsuario = findViewById(R.id.nomeusuario);
        this.senhaUsuario = findViewById(R.id.senhausuario);
        this.emailUsuario = findViewById(R.id.emailusuario);
        this.usuariousuario = findViewById(R.id.usuariousuario);
    }

    //Caso de uso Cadastrar Cliente.
    public void concluirCadastro(View v){
        String nome = this.nomeUsuario.getText().toString();
        String email = this.emailUsuario.getText().toString();
        String senha = this.senhaUsuario.getText().toString();
        String usuario = this.usuariousuario.getText().toString();

        Credencial credencial = new Credencial(usuario, senha);
        Cliente cliente = new Cliente(nome, email, usuario, senha);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this); // Object AlertDialog.Builder
        alertDialog.setMessage("Para poder fazer uma requisição de análise você precisa completar seu cadastro" +
                " fornecendo sua identificação Cpf e uma Fazenda. Deseja Cadastrar informações agora?")
                .setTitle("Completar Cadastro")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Apertou Sim", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Apertou Não", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

}
