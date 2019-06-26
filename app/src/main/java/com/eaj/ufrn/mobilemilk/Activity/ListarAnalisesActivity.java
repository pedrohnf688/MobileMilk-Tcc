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
import com.eaj.ufrn.mobilemilk.Enum.EnumEspecie;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoPostDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private EnumEspecie especie;                                                  // Guarda o Tipo de Especie
    private OrigemLeite origemLeite;                                            // Guarda a Origem do Leite
    private List<Produtos> produtos = new ArrayList<>();                        // Guarda rodutos
    private List<AnalisesSolicitadas> analisesSolicitadas = new ArrayList<>();  // Guarda as Analises Solicitadas

    private RecyclerView recyclerListAnalise;
    private AnaliseAdapter analiseAdapter;

    private TextView qtdAnalisesCadastradas;                                    // Quantidade de análises cadastradas

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_listar_analises);

        getSupportActionBar().setTitle("Amostras Cadastradas");

        this.adicionarAmostra = findViewById(R.id.adicionarAmostra);
        this.concluirCadastroAmostras = findViewById(R.id.concluirCadastroAmostras);
        this.frameLayoutMessagemInicial = findViewById(R.id.frameMenssagemInicial);
        this.frameLayoutListarAnalise = findViewById(R.id.frameListarAnalises);

        this.frameLayoutListarAnalise.setVisibility(View.GONE);           // seta o layout como inicialmente invisível ao usuário.
        this.concluirCadastroAmostras.setVisibility(View.GONE);           // Button para concluir inicalmente invisível ao usuário.
        this.concluirCadastroAmostras.setClickable(false);                // Button para concluir não está clicável.

        this.bundle = getIntent().getExtras();// Recebendo informações da Chamada de CadastrarAnalise.class.

        try {
            this.cnpj = this.bundle.getString("cnpjFazenda");            // Recebendo o cnpj da fazenda.
        }catch (RuntimeException e){

        }

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

        Date date = new Date();
        String data = date.toString();
        Log.i("data", "data: " + data);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String dataFormat = format.format(date);

        SolicitacaoPostDto solicitacaoPostDto = new SolicitacaoPostDto(this.bundle.getString("cnpjFazenda"), dataFormat, this.listaAnalise);

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<SolicitacaoPostDto> call = new RetrofitConfig().getSolicitacaoService().cadastrarSolicitacao(
                solicitacaoPostDto, prefs.getString("accessToken", "default"));
        call.enqueue(new Callback<SolicitacaoPostDto>() {
            @Override
            public void onResponse(Call<SolicitacaoPostDto> call, Response<SolicitacaoPostDto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Solicitação Cadastrada", Toast.LENGTH_SHORT).show();
                    finish();
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

        if (data == null){
            return;
        }

        Bundle bundle = data.getExtras();

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
            if(bundle.getBoolean("analiseAcidezDornic")) this.analisesSolicitadas.add(AnalisesSolicitadas.ACIDEZ_DORNIC);
            if(bundle.getBoolean("analiseDensidadeRelativa")) this.analisesSolicitadas.add(AnalisesSolicitadas.DENSIDADE_RELATIVA);
            if(bundle.getBoolean("analisePontoCrioscopico")) this.analisesSolicitadas.add(AnalisesSolicitadas.PONTO_CRIOSCOPICO);
            if(bundle.getBoolean("analiseContagemBacteriaTotal")) this.analisesSolicitadas.add(AnalisesSolicitadas.CBT);

            /*
            *  Verificando Analises Solicitadas (Fraudes)
            *
            * */
            if(bundle.getBoolean("analiseFraudeFormol")) this.analisesSolicitadas.add(AnalisesSolicitadas.FORMOL);
            if(bundle.getBoolean("analiseFraudeAçucares")) this.analisesSolicitadas.add(AnalisesSolicitadas.AÇUCARES);
            if(bundle.getBoolean("analiseFraudeAlcalinizantes")) this.analisesSolicitadas.add(AnalisesSolicitadas.ALCALINIZANTES);
            if(bundle.getBoolean("analiseFraudeLactoperoxidade")) this.analisesSolicitadas.add(AnalisesSolicitadas.LACTOPEROXIDADE);
            if(bundle.getBoolean("analiseFraudeCloretos")) this.analisesSolicitadas.add(AnalisesSolicitadas.CLORETOS);
            if(bundle.getBoolean("analiseFraudeAmido")) this.analisesSolicitadas.add(AnalisesSolicitadas.AMIDO);

            Log.i("tamanho", "tamanho list analisesSolicitadas " + analisesSolicitadas.size());

            /*
            * Verificando Proutos
            * */
            if(bundle.getInt("produto") == 0) {
                this.produtos.add(Produtos.CREME_30_GORDURA);
                Log.i("produto", ""+0);
            }
            if(bundle.getInt("produto") == 1) this.produtos.add(Produtos.CREME_45_GORDURA); Log.i("produto", ""+1);
            if(bundle.getInt("produto") == 2) this.produtos.add(Produtos.SORO); Log.i("produto", ""+2);
            if(bundle.getInt("produto") == 3) this.produtos.add(Produtos.BEBIDA_LACTEA); Log.i("produto", ""+3);
            if(bundle.getInt("produto") == 4) this.produtos.add(Produtos.IORGUTE); Log.i("produto", ""+4);
            if(bundle.getInt("produto") == 5) this.produtos.add(Produtos.SORVETE); Log.i("produto", ""+5);
            if(bundle.getInt("produto") == 6) this.produtos.add(Produtos.QUEIJO); Log.i("produto", ""+6);
            if(bundle.getInt("produto") == 7) this.produtos.add(Produtos.LEITE_UHT); Log.i("produto", ""+7);
            if(bundle.getInt("produto") == 8) this.produtos.add(Produtos.LEITE_PASTEURIZADO); Log.i("produto", ""+8);
            if(bundle.getInt("produto") == 9) this.produtos.add(Produtos.LEITE_CRU); Log.i("produto", ""+9);

            /*
            *  Verificando Origem do Leite
            * */
            if(bundle.getInt("origemLeite") == 0) this.origemLeite = OrigemLeite.TANQUE_COLETIVO; Log.i("origemLeite", ""+0);
            if(bundle.getInt("origemLeite") == 1) this.origemLeite = OrigemLeite.ANIMAL_QUARTO_MAMARIO; Log.i("origemLeite", ""+1);
            if(bundle.getInt("origemLeite") == 2) this.origemLeite = OrigemLeite.TETEIRA; Log.i("origemLeite", ""+2);
            if(bundle.getInt("origemLeite") == 3) this.origemLeite = OrigemLeite.TANQUE_INDIVIDUAL; Log.i("origemLeite", ""+3);
            if(bundle.getInt("origemLeite") == 4) this.origemLeite = OrigemLeite.ANIMAL; Log.i("origemLeite", ""+4);
            if(bundle.getInt("origemLeite") == 5) this.origemLeite = OrigemLeite.REBANHO_BALDE; Log.i("origemLeite", ""+5);
            if(bundle.getInt("origemLeite") == 6) this.origemLeite = OrigemLeite.TETO; Log.i("origemLeite", ""+6);

            /*
            *   Verificando especie
            * */
            if(bundle.getInt("especie") == 0) this.especie = EnumEspecie.CAPRINO; Log.i("especie", ""+0);
            if(bundle.getInt("especie") == 1) this.especie = EnumEspecie.BOVINO; Log.i("especie", ""+1);
            if(bundle.getInt("especie") == 2) this.especie = EnumEspecie.OVINO; Log.i("especie", ""+2);
            if(bundle.getInt("especie") == 3) this.especie = EnumEspecie.BUBALINO; Log.i("especie", ""+3);
            if(bundle.getInt("especie") == 4) this.especie = EnumEspecie.ASILINO; Log.i("especie", ""+4);

            /*
            *  Quantidade de amostras
            * */
            Integer numAnalises = bundle.getInt("numAmostras");

            /*
            *   Criando objeto do tipo Analise para adicionar ao List<Analise> ...
            * */
            Analise analise = new Analise(
                this.origemLeite, this.produtos
                    , this.analisesSolicitadas, especie, numAnalises
                    , null, null
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

            //this.analisesSolicitadas.clear();
            //this.produtos.clear();
        }
        else{
            Toast.makeText(getApplicationContext(), "Cadastro Cancelado", Toast.LENGTH_SHORT).show();
        }

    }


}
