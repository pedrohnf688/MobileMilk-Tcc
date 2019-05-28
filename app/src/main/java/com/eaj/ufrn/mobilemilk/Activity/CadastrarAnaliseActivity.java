package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

    // EditView numero de amostras
    private EditText numAmostras;


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
        this.analisesCondutividadeEletrica = findViewById(R.id.celulasSomaticas);
        this.analisesResiduoAntibiotico = findViewById(R.id.residuoAntibiotico);
        this.analisesPh = findViewById(R.id.ph);
        this.analisePontoCrioscopico = findViewById(R.id.pontoCrioscopico);
        this.analiseAcidezDornic = findViewById(R.id.acidezDornic);
        this.analiseDensidadeRelativa = findViewById(R.id.densidadeRelativa);

        this.numAmostras = findViewById(R.id.numAmostras);

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
