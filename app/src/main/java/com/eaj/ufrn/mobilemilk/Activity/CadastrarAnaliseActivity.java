package com.eaj.ufrn.mobilemilk.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Fragments.DatePickerFragment;
import com.eaj.ufrn.mobilemilk.R;

import java.text.DateFormat;
import java.util.Calendar;

public class CadastrarAnaliseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner spinnerFazenda;
    private TextView numAmostras;
    private TextView dataObtencaoAmostra;
    private Button avançar;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_analise);

        getSupportActionBar().setTitle(R.string.cadastrar); // set text action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //

        // Implementação do Spinner de Fazendas do cliente
        this.spinnerFazenda = findViewById(R.id.spinnerFazenda);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerFazenda,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerFazenda.setAdapter(adapter);

        this.numAmostras = findViewById(R.id.numeroAmostras);
        this.dataObtencaoAmostra = findViewById(R.id.dataObtencaoAmostras);
        this.avançar = findViewById(R.id.bAvançar);

    }

    // Chama ao clicar no textView de data de obtenção de amostras...
    public void setData(View v){
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "Date Picker");
        Toast.makeText(getApplicationContext(), "Apertou", Toast.LENGTH_LONG).show();
    }

    // Trata o retorno da função setdata
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        this.dataObtencaoAmostra.setText(currentDateString);
    }

    // Avança para a tela de cadastrara amostras.
    public void avançar(View v){

    }
}
