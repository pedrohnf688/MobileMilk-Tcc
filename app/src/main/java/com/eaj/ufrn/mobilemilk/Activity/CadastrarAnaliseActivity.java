package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cadastrar_analise);

        this.origemLeite = findViewById(R.id.origemDoLeite);
        this.tipoLeite = findViewById(R.id.spinnerTipoLeite);

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
