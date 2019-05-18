package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.AnaliseAdapter;
import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoPostDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarAnalisesActivity extends AppCompatActivity {

    private FloatingActionButton adicionarAmostra;
    private Button concluirCadastroAmostras;
    private FrameLayout frameLayoutMessagemInicial;
    private FrameLayout frameLayoutListarAnalise;

    private Bundle bundle;

    private Integer quantidadeAmostras;                                         // quantidade de analises desejadas
    private String cnpj;                                                        // cnpj da fzenda selecionada

    private List<Analise> listaAnalise = new ArrayList<>();                     // Guarda as analises adicionadas
    private List<Leite> leite = new ArrayList<>();                              // Guarda o Tipo do Leite
    private List<OrigemLeite> origemLeite = new ArrayList<>();                  // Guarda a Origem do Leite
    private List<Produtos> produtos = new ArrayList<>();                        // Guarda rodutos
    private List<AnalisesSolicitadas> analisesSolicitadas = new ArrayList<>();  // Guarda as Analises Solicitadas

    private RecyclerView recyclerListAnalise;
    private AnaliseAdapter analiseAdapter;

    private TextView qtdAnalisesCadastradas;                                    // Quantidade de análises cadastradas

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_listar_analises);

        getSupportActionBar().setTitle("Cadastro de amostras");

        this.adicionarAmostra = findViewById(R.id.adicionarAmostra);
        this.concluirCadastroAmostras = findViewById(R.id.concluirCadastroAmostras);
        this.frameLayoutMessagemInicial = findViewById(R.id.frameMenssagemInicial);
        this.frameLayoutListarAnalise = findViewById(R.id.frameListarAnalises);

        this.frameLayoutListarAnalise.setVisibility(View.GONE);           // seta o layout como inicialmente invisível ao usuário.
        this.concluirCadastroAmostras.setVisibility(View.GONE);           // Button para concluir inicalmente invisível ao usuário.
        this.concluirCadastroAmostras.setClickable(false);                // Button para concluir não está clicável.

        this.bundle = getIntent().getExtras();                            // Recebendo informações da Chamada de CadastrarAnalise.class
        this.quantidadeAmostras = this.bundle.getInt("numAmostras"); // Recebendo a quantidade de amostras solicitadas.
        this.cnpj = this.bundle.getString("cnpjFazenda");            // Recebendo o cnpj da fazenda.

        this.qtdAnalisesCadastradas = findViewById(R.id.qtdAnalisesCadastradas);

        Log.i("quantidade", ""+this.quantidadeAmostras);

        /*
        *   Implementação RECYCLERVIEW
        *
        *   Obs = o Adapter será setado no onActivityResult Linha -> 170 ... n.
        * */
        this.recyclerListAnalise = findViewById(R.id.recyclerViewListarAnalises);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        this.recyclerListAnalise.setLayoutManager(layout);
        this.recyclerListAnalise.setItemAnimator(new DefaultItemAnimator());

    }

    // Cadastrar Análises
    public void cadastrarAnalise(View v){
        Intent i = new Intent(getApplicationContext(), CadastrarAnaliseActivity.class);
        startActivityForResult(i, 1);
    }

    // Cadastrar Solicitação
    public void cadastrarSolicitacao(View v){

        /*
        *   Criando objeto de SolicitaDto -> Será o corpo @Body da requisição...
        * */
        SolicitacaoPostDto solicitacaoPostDto = new SolicitacaoPostDto(this.bundle.getString("cnpjFazenda"), this.listaAnalise);

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<SolicitacaoPostDto> call = new RetrofitConfig().getSolicitacaoService().cadastrarSolicitacao(
                solicitacaoPostDto, prefs.getString("accessToken", "default"));
        call.enqueue(new Callback<SolicitacaoPostDto>() {
            @Override
            public void onResponse(Call<SolicitacaoPostDto> call, Response<SolicitacaoPostDto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "deu certo", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "deu ruim", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SolicitacaoPostDto> call, Throwable t) {

            }
        });
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

            this.frameLayoutMessagemInicial.setVisibility(View.GONE);   // Esconde a mensagem de boas vindas
            this.frameLayoutListarAnalise.setVisibility(View.VISIBLE);  // Layout de Lista análises visívek
            this.concluirCadastroAmostras.setClickable(true);           // Button concluir está clicável
            this.concluirCadastroAmostras.setVisibility(View.VISIBLE);  // Button concluir está visível

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

            /*
            *   Atualiza o textView com a quantidade de análises cadastradas
            * */
            this.qtdAnalisesCadastradas.setText(""+this.listaAnalise.size());

            /*
            *  Setando adaptador para o recyclerView -> Lista Análise..
            * */
            this.recyclerListAnalise.setAdapter(new AnaliseAdapter(listaAnalise, getApplicationContext()));

            Toast.makeText(getApplicationContext(), "Analise Cadastrada", Toast.LENGTH_SHORT).show();
        }
    }

}
