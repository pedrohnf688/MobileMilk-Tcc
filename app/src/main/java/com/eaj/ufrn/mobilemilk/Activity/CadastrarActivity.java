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

import com.eaj.ufrn.mobilemilk.Enum.TipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Credencial;
import com.eaj.ufrn.mobilemilk.Modelo.Usuario;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarActivity extends AppCompatActivity {

    private TextView nomeUsuario;
    private TextView emailUsuario;
    private TextView senhaUsuario;
    private TextView usuariousuario;

    private ProgressBar progressBar;

    //private Usuario cliente;
    private Credencial credencial;

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
            senhaUsuario.setError("Senha é obrigatória");
            return false;
        }
        else if(senha.length() < 6 || senha.length() > 16){
            senhaUsuario.setError("Senha deve conter entre 6 e 16 caracteres");
            return false;
        }

        Usuario cliente = new Cliente(nome, email, null, TipoPerfilUsuario.ROLE_CLIENTE, null, null, null);
        credencial = new Credencial(senha, usuario, cliente);
        Log.i("CREDENCIAL1", "nome: " + credencial.getUsuario().getNome()+" email: "+credencial.getUsuario().getEmail());
        Log.i("CREDENCIAL1", "senha: " + credencial.getSenha()+" email: "+credencial.getUsername());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this); // Object AlertDialog.Builder
        alertDialog.setMessage(R.string.alertDialogMessage)
                .setTitle(R.string.CompletarCadastro)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), ComplementoCadastroClienteActivity.class);
                        i.putExtra("nome", credencial.getUsuario().getNome());
                        i.putExtra("email", credencial.getUsuario().getEmail());
                        i.putExtra("username", credencial.getUsername());
                        i.putExtra("senha", credencial.getSenha());
                        startActivity(i);
                    }
                })
                .setNegativeButton(R.string.AgoraNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Call<Usuario> call = Cliente.cadastrarCliente(credencial);
                        call.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if(response.isSuccessful()) {
                                    Log.i("MK", "Inseriu com sucesso");
                                    Toast.makeText(getApplicationContext(), R.string.SalvoSucesso, Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);
                                }
                                else
                                    Toast.makeText(getApplicationContext(), R.string.EmailSetError, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Log.i("MK", "Falha ao Inserir");
                                Log.i("MK", "Falha ao Inserir " + t.getMessage());
                                Toast.makeText(getApplicationContext(), R.string.FalhaInserir, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        return true;
    }



}
