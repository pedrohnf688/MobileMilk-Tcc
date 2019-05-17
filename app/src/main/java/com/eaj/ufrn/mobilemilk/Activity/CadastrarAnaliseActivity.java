package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

    // Radio Button Tipos de Leite
    private RadioButton produtosCreme30;
    private RadioButton produtosCreme45;
    private RadioButton produtosSoro;

    // Radio Button Origem do Leite
    private RadioButton analisesComposicao;
    private RadioButton analisesCaseina;
    private RadioButton analisesCelulasSomaticas;
    private RadioButton analisesNitrogenioUreico;
    private RadioButton analisesCondutividadeEletrica;
    private RadioButton analisesResiduoAntibiotico;
    private RadioButton analisesPh;
    private RadioButton analisesSoro;

    // EditView numero de amostras
    private EditText numAmostras;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cadastrar_analise);

        this.origemLeite = findViewById(R.id.origemDoLeite);
        this.tipoLeite = findViewById(R.id.spinnerTipoLeite);

        this.produtosCreme30 = findViewById(R.id.creme30);
        this.produtosCreme45 = findViewById(R.id.creme45);
        this.produtosSoro = findViewById(R.id.soro);

        this.analisesComposicao = findViewById(R.id.composicao);
        this.analisesCaseina = findViewById(R.id.caseina);
        this.analisesCelulasSomaticas = findViewById(R.id.celulasSomaticas);
        this.analisesNitrogenioUreico  = findViewById(R.id.nitrogenioUreico);
        this.analisesCondutividadeEletrica = findViewById(R.id.condutividadeEletrica);
        this.analisesResiduoAntibiotico = findViewById(R.id.residuoAntibiotico);
        this.analisesPh = findViewById(R.id.ph);
        this.analisesSoro = findViewById(R.id.Soro);

        this.numAmostras = findViewById(R.id.numeroAmostras);

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

    }

    /* salva as informaçoes selecionadas e manda para a activity ListarAnalises ...
        as informações seram tratadas e adicionadas ao um List Local a classe para só
        depois usado o método POST de "/solicitacao", esta requisição também será feitas em lista
        de analises, dentro do fluxo de cadastro de solicitação.
    */
    public void adicionarAnalise(View v){

        Intent t = new Intent();
        /*
        *   Checando Spinner de TipoLeite e OrigemLeite
        * */
        t.putExtra("origemLeite", this.origemLeite.getSelectedItemId());
        t.putExtra("tipoLeite", this.tipoLeite.getSelectedItemId());

        /*
        *   Checando radio Buttons de Tipos de Análises
        * */
        t.putExtra("analiseCaseina", this.analisesCaseina.isChecked() ? true : false);
        t.putExtra("analiseComposicao", this.analisesComposicao.isChecked() ? true : false);
        t.putExtra("analiseCelulasSomaticas", this.analisesCelulasSomaticas.isChecked() ? true : false);
        t.putExtra("analiseNitrogenioUreico", this.analisesNitrogenioUreico.isChecked() ? true : false);
        t.putExtra("analiseCondutividadeEletrica", this.analisesCondutividadeEletrica.isChecked() ? true : false);
        t.putExtra("analiseResiduoAntibiotico", this.analisesResiduoAntibiotico.isChecked() ? true : false);
        t.putExtra("analisePH", this.analisesPh.isChecked() ? true : false);
        t.putExtra("analiseSoro", this.analisesSoro.isChecked() ? true : false);

        /*
         *   Checando radio Buttons de Produtos
         * */
        t.putExtra("produtosCreme30", this.produtosCreme30.isChecked() ? true : false);
        t.putExtra("produtosCreme45", this.produtosCreme45.isChecked() ? true : false);
        t.putExtra("produtosSoro", this.produtosSoro.isChecked() ? true : false);

        /*
        *  quantidade de amostras
        * */
        boolean verify = this.numAmostras.getText().toString().isEmpty() ? false : true;
        Integer numAmostras;

        if(verify && !this.numAmostras.getText().toString().contains(".") && !this.numAmostras.getText().toString().contains(",")
            && !this.numAmostras.getText().toString().contains("-")){
            numAmostras = Integer.parseInt(this.numAmostras.getText().toString());
            if(numAmostras <= 0 ){
                this.numAmostras.setError("O número de análises deve ser maior que 0");
                return;
            }else{
                t.putExtra("numAmostras", Integer.parseInt(this.numAmostras.getText().toString()));
            }
        }
        else {
            this.numAmostras.setError("Este Campo é obrigatório e não aceita caracteres como: '.', ',' e '-' ");
            return;
        }

        setResult(1, t);
        finish();
    }
}
