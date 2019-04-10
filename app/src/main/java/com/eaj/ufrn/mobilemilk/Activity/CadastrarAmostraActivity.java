package com.eaj.ufrn.mobilemilk.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.R;

public class CadastrarAmostraActivity extends AppCompatActivity {

    private FloatingActionButton adicionarAmostra;
    private Button concluir;
    private TextView quantidadeAmostrar;
    private FrameLayout frameALayoutAmostras;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cadastrar_amostra);
    }
}
