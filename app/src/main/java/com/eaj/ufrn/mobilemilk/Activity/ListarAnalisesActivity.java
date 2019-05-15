package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoDto;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

public class ListarAnalisesActivity extends AppCompatActivity {

    private FloatingActionButton adicionarAmostra;
    private Button concluirCadastroAmostras;
    private FrameLayout frameLayoutMessagemInicial;
    private FrameLayout frameLayoutListarAmostras;

    private Bundle bundle;

    private Integer quantidadeAmostras;  // quantidade de analises desejadas
    private String cnpj;                 // cnpj da fzenda selecionada

    private List<Analise> listaAnalise = new ArrayList<>();  // Guarda as analises adicionadas

    private Solicitacao solicitacao;     // Serve para adicionar as analises

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_listar_analises);

        getSupportActionBar().setTitle("Cadastro de amostras");

        this.adicionarAmostra = findViewById(R.id.adicionarAmostra);
        this.concluirCadastroAmostras = findViewById(R.id.concluirCadastroAmostras);
        this.frameLayoutMessagemInicial = findViewById(R.id.frameMenssagemInicial);
        this.frameLayoutListarAmostras = findViewById(R.id.frameListarAnalises);

        this.frameLayoutListarAmostras.setVisibility(View.GONE);          // seta o layout como inicialmente invisível ao usuário.

        this.bundle = getIntent().getExtras();                            // Recebendo informações da Chamada de CadastrarAnalise.class
        this.quantidadeAmostras = this.bundle.getInt("numAmostras"); // Recebendo a quantidade de amostras solicitadas.
        this.cnpj = this.bundle.getString("cnpjFazenda");            // Recebendo o cnpj da fazenda.

        Log.i("quantidade", ""+this.quantidadeAmostras);

    }

    // Verifica a quantidade de analises cadastradas com a quantidade solicitada pelo cliente
    public void verificaNumeroAmostras(Integer tamanho){
        if(tamanho > 0 & tamanho < this.quantidadeAmostras){
            this.frameLayoutMessagemInicial.setVisibility(View.GONE);
            this.frameLayoutListarAmostras.setVisibility(View.VISIBLE);
        } else if (tamanho == this.quantidadeAmostras){
            this.frameLayoutMessagemInicial.setVisibility(View.GONE);
            this.frameLayoutListarAmostras.setVisibility(View.VISIBLE);
            this.concluirCadastroAmostras.setClickable(true);
            this.concluirCadastroAmostras.setBackgroundColor(getResources().getColor(R.color.GreenB));
            this.concluirCadastroAmostras.setTextColor(getResources().getColor(R.color.branco));
        }
    }

    // Cadastrar Análises
    public void cadastrarAnalise(View v){
        Intent i = new Intent(getApplicationContext(), CadastrarAnaliseActivity.class);
        startActivityForResult(i, 1);
    }

    /*
    *   Trata a volta de CadastrarAnaliseActivity.
    *   Adiciona objeto ao listaAnalise.
    * */
    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data){

        Bundle bundle = data.getExtras();

        if(data == null)
            return;

        if(resultcode == 1) {

        }



    }

}
