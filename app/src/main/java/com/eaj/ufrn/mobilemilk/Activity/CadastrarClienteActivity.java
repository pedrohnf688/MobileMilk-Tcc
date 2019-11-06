package com.eaj.ufrn.mobilemilk.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarClienteActivity extends AppCompatActivity {

    private TextView nomeUsuario;
    private TextView emailUsuario;
    private TextView senhaUsuario;
    private TextView usuariousuario;

    private ProgressBar progressBar;

    //private Usuario cliente;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_cliente);

        getSupportActionBar().setTitle(R.string.cadastrar_se); // Adiciona um Title ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adiciona a ação voltar ao ActionBar

        // Atribuindo os obejtos View aos seus devidos componentes
        this.nomeUsuario = findViewById(R.id.nomeusuario);
        this.senhaUsuario = findViewById(R.id.senhausuario);
        this.emailUsuario = findViewById(R.id.emailusuario);
        this.usuariousuario = findViewById(R.id.usuariousuario);

        this.progressBar = findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.GONE); // por padrão há progressbar é visível... setar como GONE.
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
        else if(email.contains(" ")){
            emailUsuario.setError("Email não pode haver espaços");
            return false;
        }
        else if(!email.contains("@") || !email.contains(".")){
            emailUsuario.setError("Email não é válido");
            return false;
        }
        else if(usuario.equals("")){
            usuariousuario.setError("Usuário é obrigatório");
            return false;
        }
        else if(usuario.contains(" ")){
            usuariousuario.setError("Usuário não deve conter espaços");
            return false;
        }
        else if(senha.equals("")){
            senhaUsuario.setError("Senha é obrigatória");
            return false;
        }
        else if(senha.contains(" ")){
            senhaUsuario.setError("Senha não deve conter espaços");
            return false;
        }
        else if(senha.length() < 6 || senha.length() > 16){
            senhaUsuario.setError("Senha deve conter entre 6 e 16 caracteres");
            return false;
        }

        cliente =  new Cliente(null, null, null, nome, email, null, EnumTipoPerfilUsuario.ROLE_CLIENTE, senha, usuario);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this); // Object AlertDialog.Builder
        alertDialog.setMessage(R.string.alertDialogMessage)
                .setTitle(R.string.CompletarCadastro)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), ComplementoCadastroClienteActivity.class);
                        i.putExtra("nome", cliente.getNome());
                        i.putExtra("email", cliente.getEmail());
                        i.putExtra("username", cliente.getUsername());
                        i.putExtra("senha", cliente.getSenha());
                        startActivity(i);
                    }
                })
                .setNegativeButton(R.string.AgoraNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        Call<Cliente> call = new RetrofitConfig().getClienteService().cadastrarCliente(cliente);
                        call.enqueue(new Callback<Cliente>() {
                            @Override
                            public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                                if(response.isSuccessful()) {
                                    Log.i("MK", "Inseriu com sucesso");
                                    Toast.makeText(getApplicationContext(), R.string.SalvoSucesso, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), R.string.FalhaInserir, Toast.LENGTH_SHORT).show();
                                    Log.i("erro", ""+response.toString());
                                    Log.i("erro2", ""+response.body());
                                    Log.i("erro3", ""+response.errorBody());
                                    Log.i("erro4", ""+response.message());
                                }//R.string.EmailSetError

                                progressBar.setVisibility(View.GONE);
                            }
                            @Override
                            public void onFailure(Call<Cliente> call, Throwable t) {
                                Log.i("MK", "Falha ao Inserir");
                                Log.i("MK", "Falha ao Inserir " + t.getMessage());
                                Log.i("aaaa", t.toString());
                                Log.i("bbbb", call.toString());

                                Toast.makeText(getApplicationContext(), R.string.FalhaInserir, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        return true;
    }

}
