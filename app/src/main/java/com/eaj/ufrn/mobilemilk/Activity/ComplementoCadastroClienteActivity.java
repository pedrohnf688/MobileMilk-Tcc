package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ComplementoCadastroClienteActivity extends AppCompatActivity {

    private TextView cpfUsuario;
    private TextView telefoneUsuario;
    private Button complementoCadastro;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String username;
    private Bundle bundle;

    private Cliente cliente;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.complementar_cadastro_activity);

        getSupportActionBar().setTitle(R.string.cadastrar_se); // Seta um titulo ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita a seta de voltar

        this.cpfUsuario = findViewById(R.id.cpfusuario);
        this.telefoneUsuario = findViewById(R.id.telefoneusuario);

        // Recuperando os dados de CadastrarActivity
        this.bundle = getIntent().getExtras();

        this.progressBar = findViewById(R.id.progressBar2);
        this.progressBar.setVisibility(View.GONE);
    }

    public boolean complementoCadastro(View v){
        this.cpf = this.cpfUsuario.getText().toString();
        this.telefone = this.telefoneUsuario.getText().toString();
        this.nome = this.bundle.getString("nome");
        this.email = this.bundle.getString("email");
        this.senha = this.bundle.getString("senha");
        this.username = this.bundle.getString("username");

        Log.i("SALVOU", "cpf: " + cpf + " nome: " + nome + " email: " + email + " telefone: " + telefone);

        if(cpf.equals("")){
            this.cpfUsuario.setError("Cpf é obrigatório");
            return false;
        }
        else if(cpf.length() < 11){
            this.cpfUsuario.setError("Cpf inválido");
            return  false;
        }
        else if(cpf.contains(" ")){
            this.cpfUsuario.setError("Cpf não pode conter espaços");
            return false;
        }
        else if(telefone.equals("")){
            this.telefoneUsuario.setError("Telefone obrigatório");
            return  false;
        }
        else if(telefone.length() < 11 || telefone.length() > 11){
            this.telefoneUsuario.setError("Deve conter 11 digitos incluindo o DDD");
            return false;
        }
        else if(telefone.contains(" ")){
            this.telefoneUsuario.setError("Não use espaços ao preencher o número");
            return false;
        }

        progressBar.setVisibility(View.VISIBLE);

        List<String> listaTelefones = new ArrayList<>();
        listaTelefones.add(this.telefone);

        cliente = new Cliente(null, listaTelefones, nome, email, cpf, EnumTipoPerfilUsuario.ROLE_CLIENTE.getCodigo(), senha, username);

        Call<Cliente> call = new RetrofitConfig().getClienteService().cadastrarCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                progressBar.setVisibility(View.VISIBLE);
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.SalvoSucesso, Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.CpfInvaliso, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error 404", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        return true;
    }

}
