package com.eaj.ufrn.mobilemilk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ComplementoCadastroActivity extends AppCompatActivity {

    private TextView cpfUsuario;
    private TextView telefoneUsuario;
    private Button complementoCadastro;

    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String cpf;
    private String telefone;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.complementar_cadastro_activity);

        getSupportActionBar().setTitle("Cadastrar-se");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.cpfUsuario = findViewById(R.id.cpfusuario);
        this.telefoneUsuario = findViewById(R.id.telefoneusuario);

        // Recuperando os dados de CadastrarActivity
        Bundle bundle = getIntent().getExtras();
        Log.i("SALVOU", "nome "+ bundle.getString("nome") + " email " + bundle.getString("email"));

    }

    public boolean complementoCadastro(View v){
        String cpf = this.cpfUsuario.getText().toString();
        String telefone = this.telefoneUsuario.getText().toString();

        if(cpf.equals("")){
            this.cpfUsuario.setError("Cpf é obrigatório");
            return false;
        }
        else if(cpf.length() < 11){
            this.cpfUsuario.setError("Cpf inválido");
            return  false;
        }
        else if(telefone.equals("")){
            this.telefoneUsuario.setError("Telefone é obrigatório");
            return  false;
        }
        else if(telefone.length() < 11 || telefone.length() > 11){
            this.telefoneUsuario.setError("Número deve conter 11 dígitos incluindo o DDD");
        }

        return true;
    }

}
