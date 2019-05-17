package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
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

    private Integer quantidadeAmostras;                                         // quantidade de analises desejadas
    private String cnpj;                                                        // cnpj da fzenda selecionada

    private List<Analise> listaAnalise = new ArrayList<>();                     // Guarda as analises adicionadas
    private List<Leite> leite = new ArrayList<>();                              // Guarda o Tipo do Leite
    private List<OrigemLeite> origemLeite = new ArrayList<>();                  // Guarda a Origem do Leite
    private List<Produtos> produtos = new ArrayList<>();                        // Guarda rodutos
    private List<AnalisesSolicitadas> analisesSolicitadas = new ArrayList<>();  // Guarda as Analises Solicitadas

    private Solicitacao solicitacao;                                            // Serve para adicionar as analises

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

            /*
            *  Verificando Analises Solicitadas
            * */
            if(bundle.getBoolean("analiseCaseina")) this.analisesSolicitadas.add(AnalisesSolicitadas.CASEINA);
            if(bundle.getBoolean("analiseComposicao")) this.analisesSolicitadas.add(AnalisesSolicitadas.COMPOSICAO);
            if(bundle.getBoolean("analiseCelulasSomaticas")) this.analisesSolicitadas.add(AnalisesSolicitadas.CELULAS_SOMATICAS);
            if(bundle.getBoolean("analiseCondutividadeEletrica")) this.analisesSolicitadas.add(AnalisesSolicitadas.CONDUTIVIDADE_ELETRICA);
            if(bundle.getBoolean("analiseNitrogenioUreico")) this.analisesSolicitadas.add(AnalisesSolicitadas.NITROGENIO_UREICO);
            if(bundle.getBoolean("analiseResiduoAntibiotico")) this.analisesSolicitadas.add(AnalisesSolicitadas.RESIDUO_ANTIBIOTICO);
            if(bundle.getBoolean("analisePH")) this.analisesSolicitadas.add(AnalisesSolicitadas.PH);
            if(bundle.getBoolean("analiseSoro")) this.analisesSolicitadas.add(AnalisesSolicitadas.SORO);

            Log.i("tamanho", "tamanho list analisesSolicitadas " + analisesSolicitadas.size());

            /*
            * Verificando Proutos
            * */
            if(bundle.getBoolean("produtosCreme30")) this.produtos.add(Produtos.CREME30GORDURA);
            if(bundle.getBoolean("produtosCreme30")) this.produtos.add(Produtos.CREME30GORDURA);
            if(bundle.getBoolean("produtosCreme30")) this.produtos.add(Produtos.CREME30GORDURA);

            /*
            *  Verificando Origem do Leite
            * */
            if(bundle.getInt("origemLeite") == 0) this.origemLeite.add(OrigemLeite.BALDE);
            if(bundle.getInt("origemLeite") == 1) this.origemLeite.add(OrigemLeite.TANQUE);
            if(bundle.getInt("origemLeite") == 2) this.origemLeite.add(OrigemLeite.TETO);
            if(bundle.getInt("origemLeite") == 3) this.origemLeite.add(OrigemLeite.TETEIRA);

            /*
             * Verificando tipo do Leite
             * */
            if(bundle.getInt("tipoLeite") == 0) this.leite.add(Leite.CRU);
            if(bundle.getInt("tipoLeite") == 1) this.leite.add(Leite.PASTEURIZADO);
            if(bundle.getInt("tipoLeite") == 2) this.leite.add(Leite.UHT);

            /*
            *  Quantidade de amostras
            * */
            Integer numAnalises = bundle.getInt("numAmostras");

            /*
            *   Criando objeto do tipo Analise para adicionar ao List<Analise> ...
            * */
            Analise analise = new Analise(
                this.leite, this.origemLeite, this.produtos
                    , this.analisesSolicitadas, "Nelore", numAnalises
                    , null
            );

            this.listaAnalise.add(analise);

            Toast.makeText(getApplicationContext(), "Analise Cadastrada", Toast.LENGTH_SHORT).show();
        }
    }

}
