package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.eaj.ufrn.mobilemilk.R;

public class AtualizarClienteActivity extends AppCompatActivity {

    private EditText nomeCLiente;
    private EditText emailCLiente;
    private EditText usernameCLiente;
    private EditText cpfCLiente;
    private EditText senhaAtualCLiente;
    private EditText senhaNovaCLiente;

    private Button alterarInfomacoes;
    private Button cancelarAlteracao;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_cliente);

        // Inicia componentes da view do android.
        this.nomeCLiente = findViewById(R.id.nomeClienteAtl);
        this.emailCLiente = findViewById(R.id.emailClienteAtl);
        this.cpfCLiente = findViewById(R.id.cpfClienteAtl);
        this.usernameCLiente = findViewById(R.id.usernameClienteAtl);
        this.senhaAtualCLiente = findViewById(R.id.senhaAtualClienteAtl);
        this.senhaNovaCLiente = findViewById(R.id.senhaNovaClienteAtl);

        this.alterarInfomacoes = findViewById(R.id.bAtualizarInformacoes);
        this.cancelarAlteracao = findViewById(R.id.bCancelarInformacoes);
    }
}
