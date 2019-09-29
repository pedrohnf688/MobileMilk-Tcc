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
    private Spinner especie;
    private Spinner produto;

    // Análises
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

    // Análises de Fraude
    private CheckBox analiseFraudeFormol;
    private CheckBox analiseFraudeAçucares;
    private CheckBox analiseFraudeAlcalinizantes;
    private CheckBox analiseFraudeLactoperoxidade;
    private CheckBox analiseFraudeCloretos;
    private CheckBox analiseFraudeAmido;
    private CheckBox analiseFraudePeroxidoHidrogenio;

    // EditView numero de amostras
    private EditText numAmostras;

    // TextView Slecionar fraudes
    private TextView selecionarFraude;

    // Content Layout origemLeite
    private LinearLayout orgLeite;

    // Layout com as análises de frauda
    private LinearLayout contentFrauda;

    // Content layout especies
    private LinearLayout contentEspecie;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cadastrar_analise);

        getSupportActionBar().setTitle("Cadastro de amostras");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Spinners
        this.origemLeite = findViewById(R.id.spinnerOrigemLeite);
        this.produto = findViewById(R.id.spinnerProduto);
        this.especie = findViewById(R.id.spinnerEspecie);

        // Análises
        this.analisesComposicao = findViewById(R.id.composicao);
        this.analisesCaseina = findViewById(R.id.caseina);
        this.analisesCelulasSomaticas = findViewById(R.id.celulasSomaticas);
        this.analisesNitrogenioUreico = findViewById(R.id.nitroGenioUreico);
        this.analisesCondutividadeEletrica = findViewById(R.id.condutividadeeletrica);
        this.analisesResiduoAntibiotico = findViewById(R.id.residuoAntibiotico);
        this.analisesPh = findViewById(R.id.ph);
        this.analisePontoCrioscopico = findViewById(R.id.pontoCrioscopico);
        this.analiseAcidezDornic = findViewById(R.id.acidezDornic);
        this.analiseDensidadeRelativa = findViewById(R.id.densidadeRelativa);
        this.analiseContagemBacteriaTotal = findViewById(R.id.contagemBacteriasTotais);

        // Análises de fraude
        this.analiseFraudeFormol = findViewById(R.id.formol);
        this.analiseFraudeAçucares = findViewById(R.id.açucares);
        this.analiseFraudeAlcalinizantes = findViewById(R.id.alcalinizantes);
        this.analiseFraudeLactoperoxidade = findViewById(R.id.lactoperoxidade);
        this.analiseFraudeCloretos = findViewById(R.id.cloretos);
        this.analiseFraudeAmido = findViewById(R.id.amido);
        this.analiseFraudePeroxidoHidrogenio = findViewById(R.id.peroxidoHidrogenio);

        // número de amostras
        this.numAmostras = findViewById(R.id.numAmostras);

        this.selecionarFraude = findViewById(R.id.selecionarFraude);
        this.contentFrauda = findViewById(R.id.contentFraude);
        this.orgLeite = findViewById(R.id.layout3);

        // Adapter array para spinner de Origem de Leite
        ArrayAdapter<CharSequence> array = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerOrigemLeite,
                android.R.layout.simple_spinner_item);
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.origemLeite.setAdapter(array);

        // Adapter array para spinner de produto
        ArrayAdapter<CharSequence> array2 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerProdutos,
                android.R.layout.simple_spinner_item);
        array2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.produto.setAdapter(array2);

        // Adapter array para spinner de especie
        ArrayAdapter<CharSequence> array3 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerEspecie,
                android.R.layout.simple_spinner_item);
        array3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.especie.setAdapter(array3);

        this.iniciaLayoutAnalise();                 // Ajusta o Layout inicia de opções de análise

        // Implementação de Listener para o spinner de produto
        this.produto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ItemSelected", "Position: " + position + " id: " + id);

                if (position == 7){
                    // habilita as análises de fraude
                    contentFrauda.setVisibility(View.VISIBLE);

                    // Spinner Origem do Leite
                    orgLeite.setVisibility(View.VISIBLE);

                    // todas habilitadas
                    analisesCaseina.setVisibility(View.VISIBLE);
                    analisePontoCrioscopico.setVisibility(View.VISIBLE);
                    analisesCelulasSomaticas.setVisibility(View.VISIBLE);
                    analisesNitrogenioUreico.setVisibility(View.VISIBLE);
                    analisesResiduoAntibiotico.setVisibility(View.VISIBLE);
                    analiseAcidezDornic.setVisibility(View.VISIBLE);
                    analiseDensidadeRelativa.setVisibility(View.VISIBLE);
                    analisesComposicao.setVisibility(View.VISIBLE);
                    analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
                }
                else if ( position == 8){
                    // desaabilita as análises de fraude
                    contentFrauda.setVisibility(View.GONE);

                    // Spinner Origem do Leite
                    orgLeite.setVisibility(View.VISIBLE);

                    // desabilita algumas habilitadas
                    analisesCaseina.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analiseDensidadeRelativa.setVisibility(View.VISIBLE);
                    analisesComposicao.setVisibility(View.VISIBLE);
                    analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
                }
                else if (position == 9){
                    // desaabilita as análises de fraude
                    contentFrauda.setVisibility(View.GONE);

                    // Spinner Origem do Leite
                    orgLeite.setVisibility(View.VISIBLE);

                    // desabilita algumas habilitadas
                    analisesCaseina.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analiseDensidadeRelativa.setVisibility(View.VISIBLE);
                    analisesComposicao.setVisibility(View.VISIBLE);
                    analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.GONE);
                }
                else if (position == 6){
                    // desaabilita as análises de fraude
                    contentFrauda.setVisibility(View.GONE);

                    // Spinner Origem do Leite
                    orgLeite.setVisibility(View.GONE);

                    // desabilita algumas habilitadas
                    analisesCaseina.setVisibility(View.GONE);
                    analisePontoCrioscopico.setVisibility(View.GONE);
                    analisesCelulasSomaticas.setVisibility(View.GONE);
                    analisesNitrogenioUreico.setVisibility(View.GONE);
                    analisesResiduoAntibiotico.setVisibility(View.GONE);
                    analiseAcidezDornic.setVisibility(View.GONE);
                    analiseDensidadeRelativa.setVisibility(View.GONE);
                    analisesComposicao.setVisibility(View.GONE);
                    analisesCondutividadeEletrica.setVisibility(View.GONE);
                    analisesPh.setVisibility(View.VISIBLE);
                    analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
                }
                else{
                    iniciaLayoutAnalise();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    // Ajusta o layout inicial
    public void iniciaLayoutAnalise() {
        /* Setar como invisível os spinners de origem e tipo de leite -> no caso quando
         *  o produto selecionado não for o leite.
         * */

        // Desabilita as análises de fraude
        this.contentFrauda.setVisibility(View.GONE);

        // Spinner Origem do Leite
        orgLeite.setVisibility(View.GONE);

        // Desabilitar análises
        this.analisesCaseina.setVisibility(View.GONE);
        this.analisePontoCrioscopico.setVisibility(View.GONE);
        this.analisesCelulasSomaticas.setVisibility(View.GONE);
        this.analisesNitrogenioUreico.setVisibility(View.GONE);
        this.analisesResiduoAntibiotico.setVisibility(View.GONE);
        this.analiseAcidezDornic.setVisibility(View.GONE);
        this.analiseDensidadeRelativa.setVisibility(View.GONE);
        this.analisesComposicao.setVisibility(View.VISIBLE);
        this.analisesPh.setVisibility(View.VISIBLE);
        this.analisesCondutividadeEletrica.setVisibility(View.VISIBLE);
        this.analiseContagemBacteriaTotal.setVisibility(View.VISIBLE);
    }

    /* salva as informaçoes selecionadas e manda para a activity ListarAnalises ...
        as informações seram tratadas e adicionadas ao um List Local a classe para só
        depois usado o método POST de "/solicitacao", esta requisição também será feitas em lista
        de analises, dentro do fluxo de cadastro de solicitação.
    */
    public void adicionarAnalise(View v) {

        Intent t = new Intent();

        /*
         *   Checando Spinners.
         * */
        t.putExtra("origemLeite", this.origemLeite.getSelectedItemId());
        t.putExtra("produto", this.produto.getSelectedItemId());
        t.putExtra("especie", this.especie.getSelectedItemId());

        Log.i("itens", "origem Leite " + this.origemLeite.getSelectedItemId());
        Log.i("itens", "produtos " + this.produto.getSelectedItemId());
        Log.i("itens", "especie " + this.especie.getSelectedItemId());

        /*
         *   Checando Checkbox de Tipos de Análises
         * */

        t.putExtra("analiseCaseina", this.analisesCaseina.isChecked() ? true : false);
        t.putExtra("analiseComposicao", this.analisesComposicao.isChecked() ? true : false);
        t.putExtra("analiseCelulasSomaticas", this.analisesCelulasSomaticas.isChecked() ? true : false);
        t.putExtra("analiseNitrogenioUreico", this.analisesNitrogenioUreico.isChecked() ? true : false);
        t.putExtra("analiseCondutividadeEletrica", this.analisesCondutividadeEletrica.isChecked() ? true : false);
        t.putExtra("analiseResiduoAntibiotico", this.analisesResiduoAntibiotico.isChecked() ? true : false);
        t.putExtra("analisePH", this.analisesPh.isChecked() ? true : false);
        t.putExtra("analiseAcidezDornic", this.analiseAcidezDornic.isChecked() ? true : false);
        t.putExtra("analiseDensidadeRelativa", this.analiseDensidadeRelativa.isChecked() ? true : false);
        t.putExtra("analisePontoCrioscopico", this.analisePontoCrioscopico.isChecked() ? true : false);
        t.putExtra("analiseContagemBacteriaTotal", this.analiseContagemBacteriaTotal.isChecked() ? true : false);

        /*
         *   Checando Checkbox de Análises de Fraude
         * */
        t.putExtra("analiseFraudeFormol", this.analiseFraudeFormol.isChecked() ? true : false);
        t.putExtra("analiseFraudeAçucares", this.analiseFraudeAçucares.isChecked() ? true : false);
        t.putExtra("analiseFraudeAlcalinizantes", this.analiseFraudeAlcalinizantes.isChecked() ? true : false);
        t.putExtra("analiseFraudeLactoperoxidade", this.analiseFraudeLactoperoxidade.isChecked() ? true : false);
        t.putExtra("analiseFraudeCloretos", this.analiseFraudeCloretos.isChecked() ? true : false);
        t.putExtra("analiseFraudeAmido", this.analiseFraudeAmido.isChecked() ? true : false);

        /*
         *  quantidade de amostras
         * */
        boolean verify = this.numAmostras.getText().toString().isEmpty() ? false : true;
        Integer numAmostras;

        if (verify && !this.numAmostras.getText().toString().contains(".") && !this.numAmostras.getText().toString().contains(",")
                && !this.numAmostras.getText().toString().contains("-")) {
            numAmostras = Integer.parseInt(this.numAmostras.getText().toString());
            if (numAmostras <= 0) {
                this.numAmostras.setError("O número de análises deve ser maior que 0");
                return;
            } else {
                t.putExtra("numAmostras", Integer.parseInt(this.numAmostras.getText().toString()));
            }
        } else {
            this.numAmostras.setError("Este Campo é obrigatório e não aceita caracteres como: '.', ',' e '-' ");
            return;
        }

        setResult(1, t);
        finish();
    }

    public void cancelarAnalise(View v){
        setResult(2);
        finish();
    }

}
