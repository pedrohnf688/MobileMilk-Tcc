package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Activity.CadastrarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Credencial;
import com.eaj.ufrn.mobilemilk.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplementoCadastroClienteActivity extends AppCompatActivity {

    private TextView cpfUsuario;
    private TextView telefoneUsuario;
    private Button complementoCadastro;

    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String cpf;
    private String telefone;
    private String username;
    private Bundle bundle;

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
        //Log.i("SALVOU", "nome "+ bundle.getString("nome") + " email " + bundle.getString("email"));

    }

    public boolean complementoCadastro(View v){
        this.cpf = this.cpfUsuario.getText().toString();
        this.telefone = this.telefoneUsuario.getText().toString();
        this.nome = this.bundle.getString("nome");
        this.email = this.bundle.getString("email");
        this.usuario = this.bundle.getString("usuario");
        this.senha = this.bundle.getString("senha");
        this.username = this.bundle.getString("username");

        Log.i("SALVOU", "cpf: " + cpf + " nome: " + nome + " email: " + email + " telefone: " + telefone);

        if(cpf.equals("")){
            this.cpfUsuario.setError(""+R.string.CpfObrigatorio);
            return false;
        }
        else if(cpf.length() < 11){
            this.cpfUsuario.setError(""+R.string.CpfInvaliso);
            return  false;
        }
        else if(telefone.equals("")){
            this.telefoneUsuario.setError(""+R.string.TelefoneObrigatorio);
            return  false;
        }
        else if(telefone.length() < 11 || telefone.length() > 11){
            this.telefoneUsuario.setError(""+R.string.TelefoneInvalido);
            return false;
        }

        Credencial c = new Credencial(this.senha, this.username, "ROLE_CLIENTE");
        Cliente cliente = new Cliente(this.nome, this.email, this.cpf, this.telefone,c);
        Call<Cliente> call = Cliente.cadastrarCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.SalvoSucesso, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), CadastrarFazendaActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.CpfInvaliso, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), CadastrarFazendaActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error 404", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }

}
