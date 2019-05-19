package com.eaj.ufrn.mobilemilk.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LeitorQrCode extends AppCompatActivity {
    ImageButton btScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__leitor_qr_code);

        final Activity activity = this;

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
