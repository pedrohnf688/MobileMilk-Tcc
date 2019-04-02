package com.eaj.ufrn.mobilemilk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class CadastrarFazendaActivity extends AppCompatActivity {

    private TextView empresaFazenda;
    private TextView localizacaoFazenda;
    private TextView cnpjFazenda;
    private TextView cepFazenda;
    private TextView enderecoFazenda;

    private Button concluir;

    private String empresa;
    private String localizacao;
    private String cnpj;
    private String cep;
    private String endereco;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_fazenda);

        getSupportActionBar().setTitle(R.string.CadastrarFazenda); // Seta um titulo ao ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita a seta voltar

        this.empresaFazenda = findViewById(R.id.empresaFazenda);
        this.localizacaoFazenda = findViewById(R.id.localizacaoFazenda);
        this.cnpjFazenda = findViewById(R.id.cnpjFazenda);
        this.cepFazenda = findViewById(R.id.cepFazenda);
        this.enderecoFazenda = findViewById(R.id.enderecoFazenda);

        this.concluir = findViewById(R.id.bConcluir);

    }
}
