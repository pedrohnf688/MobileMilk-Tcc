package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

public class CadastrarAnaliseActivity extends AppCompatActivity {

    private Spinner origemLeite;
    private Spinner tipoLeite;
    private Spinner produto;

    // Radio Button Origem do Leite
    private CheckBox analisesComposicao;
    private CheckBox analisesCaseina;
    private CheckBox analisesCelulasSomaticas;
    private CheckBox analisesNitrogenioUreico;
    private CheckBox analisesCondutividadeEletrica;
    private CheckBox analisesResiduoAntibiotico;
    private CheckBox analisesPh;
    private CheckBox analisePontoCrioscopico;
    private CheckBox analiseAcidezDornic;
    private CheckBox analiseDensidadeRelativa;
    private CheckBox analiseContagemBacteriaTotal;

    // EditView numero de amostras
    private EditText numAmostras;

    private TextView selecionarFraude;

    // Layout com as análises de frauda
    private LinearLayout contentFrauda;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cadastrar_analise);

        getSupportActionBar().setTitle("Cadastro de amostras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.origemLeite = findViewById(R.id.spinnerOrigemLeite);
        this.tipoLeite = findViewById(R.id.spinnerTipoLeite);
        this.produto = findViewById(R.id.spinnerProduto);

        this.analisesComposicao = findViewById(R.id.composicao);
        this.analisesCaseina = findViewById(R.id.caseina);
        this.analisesCelulasSomaticas = findViewById(R.id.celulasSomaticas);
        this.analisesNitrogenioUreico  = findViewById(R.id.nitroGenioUreico);
        this.analisesCondutividadeEletrica = findViewById(R.id.condutividadeeletrica);
        this.analisesResiduoAntibiotico = findViewById(R.id.residuoAntibiotico);
        this.analisesPh = findViewById(R.id.ph);
        this.analisePontoCrioscopico = findViewById(R.id.pontoCrioscopico);
        this.analiseAcidezDornic = findViewById(R.id.acidezDornic);
        this.analiseDensidadeRelativa = findViewById(R.id.densidadeRelativa);
        this.analiseContagemBacteriaTotal = findViewById(R.id.contagemBacteriasTotais);

        this.numAmostras = findViewById(R.id.numAmostras);

        this.selecionarFraude = findViewById(R.id.selecionarFraude);

        this.contentFrauda = findViewById(R.id.contentFraude);

        // Adapter array para spinner de Origem de Leite
        ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerOrigemLeite,
                android.R.layout.simple_spinner_item);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.origemLeite.setAdapter(array);

        // Adapter array para spinner de Tipo de Leite
        ArrayAdapter<CharSequence> array1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerTipoLeite,
                android.R.layout.simple_spinner_item);
        array1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.tipoLeite.setAdapter(array1);

        // Adapter array para spinner de produto
        ArrayAdapter<CharSequence> array2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerProdutos,
                android.R.layout.simple_spinner_item);
        array2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.produto.setAdapter(array2);

        this.iniciaLayoutAnalise(); // Ajusta o Layout inicia de opções de análise

        // Implementação de Listener para o spinner de produto
        this.produto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ItemSelected", "Position: " + position + " id: " + id);

                if(position != 7 && position != 6){

                    /* Setar como invisível os spinners de origem e tipo de leite -> no caso quando
                    *  o produto selecionado não for o leite.
                    * */
                    origemLeite.setVisibility(View.GONE);
                    tipoLeite.setVisibility(View.GONE);
                    selecionarFraude.setVisibility(View.GONE);
                    contentFrauda.setVisibility(View.GONE);

                    // Desabilita algumas análises
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesCaseina.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseDensidadeRelativa.setVisibility(View.GONE);

                    // Habilita algumas análises
                    analisesComposicao.setVisibility(View.VISIBLE);
                    analisesPh.setVisibility(View.VISIBLE);
                    analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);

                }
                else if(position == 6){

                    /* Setar como invisível os spinners de origem e tipo de leite -> no caso quando
                     *  o produto selecionado não for o leite.
                     * */
                    origemLeite.setVisibility(View.GONE);
                    tipoLeite.setVisibility(View.GONE);
                    selecionarFraude.setVisibility(View.GONE);
                    contentFrauda.setVisibility(View.GONE);

                    // Desabilita algumas análises
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesCaseina.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseDensidadeRelativa.setVisibility(View.GONE);
                    analisesComposicao.setVisibility(View.GONE);
                    analiseContagemBacteriaTotal.setVisibility(View.GONE);
                    analisesCondutividadeEletrica.setVisibility(View.GONE);

                    // Habilita
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);

                }
                else{
                    /* Setar como Visível os spinners de origem e tipo de leite -> no caso quando
                     *  o produto selecionado não for o leite.
                     * */
                    origemLeite.setVisibility(View.VISIBLE);
                    tipoLeite.setVisibility(View.VISIBLE);
                    selecionarFraude.setVisibility(View.VISIBLE);
                    contentFrauda.setVisibility(View.VISIBLE);

                    // tudo habilitado
                    analisesCelulasSomaticas.setVisibility(View.VISIBLE);
                    analisesCaseina.setVisibility(View.VISIBLE);
                    analisesNitrogenioUreico.setVisibility(View.VISIBLE);
                    analisePontoCrioscopico.setVisibility(View.VISIBLE);
                    analiseAcidezDornic.setVisibility(View.VISIBLE);
                    analisesResiduoAntibiotico.setVisibility(View.VISIBLE);
                    analiseDensidadeRelativa.setVisibility(View.VISIBLE);
                    analisesComposicao.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);

                    // Habilita
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Implementação para o spinner de Tipo do Leite
        this.tipoLeite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ItemSelected", "Position: " + position + " id: " + id);

                if(position == 0){
                    // Habilita as opções de seleção de tipos de análises de fraude
                    selecionarFraude.setVisibility(View.VISIBLE);
                    contentFrauda.setVisibility(View.VISIBLE);

                    // todas as opções são habilitadas
                    analisesCelulasSomaticas.setVisibility(View.VISIBLE);
                    analisesCaseina.setVisibility(View.VISIBLE);
                    analisesNitrogenioUreico.setVisibility(View.VISIBLE);
                    analisePontoCrioscopico.setVisibility(View.VISIBLE);
                    analiseAcidezDornic.setVisibility(View.VISIBLE);
                    analisesResiduoAntibiotico.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);

                }
                else if(position == 1){
                    // Desabilita as opções de seleção de tipos de análises de fraude
                    selecionarFraude.setVisibility(View.GONE);
                    contentFrauda.setVisibility(View.GONE);

                    // Desabilita algumas opções de análises
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesCaseina.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);

                    // Habilita
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
                }
                else{
                    // Desabilita as opções de seleção de tipos de análises de fraude
                    selecionarFraude.setVisibility(View.GONE);
                    contentFrauda.setVisibility(View.GONE);

                    // Desabilita algumas opções de análises
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesCaseina.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseContagemBacteriaTotal.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    // Ajusta o layout inicial
    public void iniciaLayoutAnalise(){
        /* Setar como invisível os spinners de origem e tipo de leite -> no caso quando
         *  o produto selecionado não for o leite.
         * */
        origemLeite.setVisibility(View.GONE);
        tipoLeite.setVisibility(View.GONE);
        selecionarFraude.setVisibility(View.GONE);
        contentFrauda.setVisibility(View.GONE);

        // Desabilita algumas análises
        analisesCelulasSomaticas.setVisibility(View.GONE);
        analisesCaseina.setVisibility(View.GONE);
        analisesNitrogenioUreico.setVisibility(View.GONE);
        analisePontoCrioscopico.setVisibility(View.GONE);
        analiseAcidezDornic.setVisibility(View.GONE);
        analisesResiduoAntibiotico.setVisibility(View.GONE);
        analiseDensidadeRelativa.setVisibility(View.GONE);

        // Habilita algumas análises
        analisesComposicao.setVisibility(View.VISIBLE);
        analisesPh.setVisibility(View.VISIBLE);
        analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
        analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
    }

    /* salva as informaçoes selecionadas e manda para a activity ListarAnalises ...
        as informações seram tratadas e adicionadas ao um List Local a classe para só
        depois usado o método POST de "/solicitacao", esta requisição também será feitas em lista
        de analises, dentro do fluxo de cadastro de solicitação.
    */
    public void adicionarAnalise(View v){

//        Intent t = new Intent();
//        /*
//        *   Checando Spinner de TipoLeite e OrigemLeite
//        * */
//        t.putExtra("origemLeite", this.origemLeite.getSelectedItemId());
//        t.putExtra("tipoLeite", this.tipoLeite.getSelectedItemId());
//
//        Log.i("item", "origemLeite: " + this.origemLeite.getSelectedItemId());
//        Log.i("item", "tipoLeite: " + this.tipoLeite.getSelectedItemId());
//
//        /*
//        *   Checando radio Buttons de Tipos de Análises
//        * */
//        t.putExtra("analiseCaseina", this.analisesCaseina.isChecked() ? true : false);
//        t.putExtra("analiseComposicao", this.analisesComposicao.isChecked() ? true : false);
//        t.putExtra("analiseCelulasSomaticas", this.analisesCelulasSomaticas.isChecked() ? true : false);
//        t.putExtra("analiseNitrogenioUreico", this.analisesNitrogenioUreico.isChecked() ? true : false);
//        t.putExtra("analiseCondutividadeEletrica", this.analisesCondutividadeEletrica.isChecked() ? true : false);
//        t.putExtra("analiseResiduoAntibiotico", this.analisesResiduoAntibiotico.isChecked() ? true : false);
//        t.putExtra("analisePH", this.analisesPh.isChecked() ? true : false);
//        t.putExtra("analiseSoro", this.analisesSoro.isChecked() ? true : false);
//
//        /*
//         *   Checando radio Buttons de Produtos
//         * */
//        t.putExtra("produtosCreme30", this.produtosCreme30.isChecked() ? true : false);
//        t.putExtra("produtosCreme45", this.produtosCreme45.isChecked() ? true : false);
//        t.putExtra("produtosSoro", this.produtosSoro.isChecked() ? true : false);
//
//        /*
//        *  quantidade de amostras
//        * */
//        boolean verify = this.numAmostras.getText().toString().isEmpty() ? false : true;
//        Integer numAmostras;
//
//        if(verify && !this.numAmostras.getText().toString().contains(".") && !this.numAmostras.getText().toString().contains(",")
//            && !this.numAmostras.getText().toString().contains("-")){
//            numAmostras = Integer.parseInt(this.numAmostras.getText().toString());
//            if(numAmostras <= 0 ){
//                this.numAmostras.setError("O número de análises deve ser maior que 0");
//                return;
//            }else{
//                t.putExtra("numAmostras", Integer.parseInt(this.numAmostras.getText().toString()));
//            }
//        }
//        else {
//            this.numAmostras.setError("Este Campo é obrigatório e não aceita caracteres como: '.', ',' e '-' ");
//            return;
//        }
//
//        setResult(1, t);
//        finish();
    }
}
