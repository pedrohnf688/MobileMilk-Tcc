package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplementoCadastroClienteActivity extends AppCompatActivity {

    private EditText cpfUsuario;
    private EditText telefone1Usuario;
    private EditText telefone2Usuario;

    private Button complementoCadastro;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone1;
    private String telefone2;
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
        this.telefone1Usuario = findViewById(R.id.telefone1usuario);
        this.telefone2Usuario = findViewById(R.id.telefone2usuario);

        // Recuperando os dados de CadastrarClienteActivity
        this.bundle = getIntent().getExtras();

        this.progressBar = findViewById(R.id.progressBar2);
        this.progressBar.setVisibility(View.GONE);

        // Adicionando maskaras ao campo cpf
        SimpleMaskFormatter mask = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher watcher = new MaskTextWatcher(this.cpfUsuario, mask);
        this.cpfUsuario.addTextChangedListener(watcher);

        // Adicionando maskaras ao camo telefone 1 e telefone 2
        SimpleMaskFormatter mask1 = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher watcher1 = new MaskTextWatcher(this.telefone1Usuario, mask1);
        this.telefone1Usuario.addTextChangedListener(watcher1);
        watcher1 = new MaskTextWatcher(this.telefone2Usuario, mask1);
        this.telefone2Usuario.addTextChangedListener(watcher1);
    }

    public boolean complementoCadastro(View v){
        this.cpf = this.cpfUsuario.getText().toString();
        this.telefone1 = this.telefone1Usuario.getText().toString();
        this.telefone2 = this.telefone2Usuario.getText().toString();
        this.nome = this.bundle.getString("nome");
        this.email = this.bundle.getString("email");
        this.senha = this.bundle.getString("senha");
        this.username = this.bundle.getString("username");


        if(cpf.equals("")){
            this.cpfUsuario.setError("Cpf é obrigatório");
            return false;
        }
        else if(cpf.length() < 14){
            this.cpfUsuario.setError("Cpf inválido");
            return  false;
        }
        else if(cpf.contains(" ")){
            this.cpfUsuario.setError("Cpf não pode conter espaços");
            return false;
        }
        else if(telefone1.equals("")){
            this.telefone1Usuario.setError("Telefone obrigatório");
            return  false;
        }
        else if(telefone1.length() < 14 || telefone1.length() > 14){
            this.telefone1Usuario.setError("Deve conter 11 digitos incluindo o DDD");
            return false;
        }
        else if(telefone1.contains(" ")){
            this.telefone1Usuario.setError("Não use espaços ao preencher o número");
            return false;
        }
        else if(telefone2.length() < 14 || telefone2.length() > 14){
            this.telefone2Usuario.setError("Deve conter 11 digitos incluindo o DDD");
            return false;
        }
        else if(telefone2.contains(" ")){
            this.telefone2Usuario.setError("Não use espaços ao preencher o número");
            return false;
        }

        progressBar.setVisibility(View.VISIBLE);

        cliente = new Cliente(null, telefone1, telefone2, nome, email, cpf, EnumTipoPerfilUsuario.ROLE_CLIENTE, senha, username);

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
                    Toast.makeText(getApplicationContext(), R.string.FalhaInserir, Toast.LENGTH_SHORT).show();
                }//R.string.CpfInvaliso
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error 404", Toast.LENGTH_SHORT).show();
                Log.i("Error404", "vausa: " + t.getCause());
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });

        return true;
    }

}
