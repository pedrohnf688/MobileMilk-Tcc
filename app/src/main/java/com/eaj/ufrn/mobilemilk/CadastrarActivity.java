package com.eaj.ufrn.mobilemilk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Credencial;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarActivity extends AppCompatActivity {

    TextView nomeUsuario;
    TextView emailUsuario;
    TextView senhaUsuario;
    TextView usuariousuario;

    Cliente cliente;

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
    public boolean concluirCadastro(View v){

        String nome = this.nomeUsuario.getText().toString();
        String email = this.emailUsuario.getText().toString();
        String senha = this.senhaUsuario.getText().toString();
        String usuario = this.usuariousuario.getText().toString();

        if(nome.equals("")) {
            nomeUsuario.setError("Nome é obrigatório");
            return false;
        }
        else if(email.equals("")){
            emailUsuario.setError("Email é obrigatório");
            return false;
        }
        else if(usuario.equals("")){
            usuariousuario.setError("Usuário é obrigatório");
            return false;
        }
        else if(senha.equals("")){
            senhaUsuario.setError("Senha é obrigatório");
            return false;
        }
        else if(senha.length() < 6 || senha.length() > 16){
            senhaUsuario.setError("Senha deve conter 6 digitos no mínimo e 16 no máximo");
            return false;
        }

        this.cliente = new Cliente(nome, email, usuario, senha);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this); // Object AlertDialog.Builder
        alertDialog.setMessage(R.string.alertDialogMessage)
                .setTitle("Completar Cadastro")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), ComplementoCadastroActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("nome", cliente.getNome());
                        bundle.putString("email", cliente.getEmail());
                        //bundle.putString("usuario", cliente.getCredencial().getUsername());
                        //bundle.putString("senha", cliente.getCredencial().getSenha());
                        startActivity(i, bundle);
                    }
                })
                .setNegativeButton("Agora não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Call<Cliente> call = Cliente.cadastrarCliente(cliente);
                        call.enqueue(new Callback<Cliente>() {
                            //Trata a resposta
                            @Override
                            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                                if(response.isSuccessful()) {
                                    Log.i("MK", "Inseriu com sucesso");
                                    Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "o e-mail utiizado já existe", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<Cliente> call, Throwable t) {
                                Log.i("MK", "Falha ao Inserir");
                                Log.i("MK", "Falha ao Inserir " + t.getMessage());
                                Toast.makeText(getApplicationContext(), "Falha ao inserir", Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        return true;
    }



}
