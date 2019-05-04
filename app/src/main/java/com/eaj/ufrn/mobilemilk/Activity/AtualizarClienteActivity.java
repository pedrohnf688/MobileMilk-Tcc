package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.eaj.ufrn.mobilemilk.R;

public class AtualizarClienteActivity extends AppCompatActivity {

    private EditText nomeCliente;
    private EditText emailCliente;
    private EditText telefoneCliente;
    private EditText cpfCliente;
    private EditText senhaAtualCLiente;
    private EditText senhaNovaCLiente;

    private Button alterarInfomacoes;
    private Button cancelarAlteracao;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_cliente);

        // Inicia componentes da view do android.
        this.nomeCliente = findViewById(R.id.nomeClienteAtl);
        this.emailCliente = findViewById(R.id.emailClienteAtl);
        this.cpfCliente = findViewById(R.id.cpfClienteAtl);
        this.telefoneCliente = findViewById(R.id.telefoneClienteAtl);
        this.senhaAtualCLiente = findViewById(R.id.senhaAtualClienteAtl);
        this.senhaNovaCLiente = findViewById(R.id.senhaNovaClienteAtl);

        this.alterarInfomacoes = findViewById(R.id.bAtualizarInformacoes);
        this.cancelarAlteracao = findViewById(R.id.bCancelarInformacoes);

        this.carregarPerfil(); // carrega informações para a tela de atualizar_informações_cliente
    }

    public void carregarPerfil(){
        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        this.nomeCliente.setText(prefs.getString("nome", "default"));
        this.emailCliente.setText(prefs.getString("email", "default"));
        this.cpfCliente.setText(prefs.getString("cpf", "default"));
        this.telefoneCliente.setText(prefs.getString("telefone0", "default"));
    }
}
