package com.eaj.ufrn.mobilemilk.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class QrCodeFragment extends Fragment {

    private TextView texto;
    private ImageButton leitor;

    public QrCodeFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.activity_leitor, group, false);

        this.texto = view.findViewById(R.id.texto);
        this.leitor = view.findViewById(R.id.scanQR);

        this.leitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Qr Code da sua Amostra");
                integrator.setCameraId(0); //0 camera traseira e 1 para camera frontal
                integrator.initiateScan();
            }
        });


        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() != null){
                //alert("Deu bom");
                texto.setText(result.getContents());
            }else{
                alert("Cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    private  void alert(String msg){ //depois trocar esse toast
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
