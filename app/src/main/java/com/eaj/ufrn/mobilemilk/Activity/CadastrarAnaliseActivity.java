package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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

}
