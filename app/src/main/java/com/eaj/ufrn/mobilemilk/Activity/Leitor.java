package com.eaj.ufrn.mobilemilk.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eaj.ufrn.mobilemilk.R;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Leitor extends AppCompatActivity {

    TextView txtleite;
    TextView txorigem;
    TextView txtproduto;
    ImageButton btScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor);

        final Activity activity = this;

        txtleite = findViewById(R.id.leite);
        txorigem= findViewById(R.id.origem);
        txtproduto = findViewById(R.id.produtos);


        btScan =  findViewById(R.id.scanQR);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Qr Code da sua Amostra");
                integrator.setCameraId(0); //0 camera traseira e 1 para camera frontal
                integrator.initiateScan();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() != null){
                alert("Deu bom");
                txtleite.setText(result.getContents());
                txorigem.setText(result.getContents());
                txtproduto.setText(result.getContents());
            }else{
                alert("Cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    private  void alert(String msg){ //depois trocar esse toast
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
