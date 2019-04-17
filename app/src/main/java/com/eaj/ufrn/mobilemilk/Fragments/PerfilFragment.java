package com.eaj.ufrn.mobilemilk.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Activity.AtualizarClienteActivity;
import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment {

    private TextView nomeClientePerfil;
    private TextView emailClientePerfil;
    private TextView cpfClientePerfil;
    private TextView telefoneClientePerfil;

    private Button bAlterarPerfil;
    private Button bListarFazendas;

    //private Usuario usuario;

    private FrameLayout frameLayout;

    private CircleImageView circular;

    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveIsntanceState){

        View view = inflater.inflate(R.layout.fragment_perfil, container, false); // Infla o layout do fragment

        // Incia componentes da view
        this.nomeClientePerfil = view.findViewById(R.id.nomeCLientePerfil);
        this.emailClientePerfil = view.findViewById(R.id.emailCLientePerfil);
        this.cpfClientePerfil = view.findViewById(R.id.cpfCLientePerfil);
        this.telefoneClientePerfil = view.findViewById(R.id.telefoneCLientePerfil);

        this.bListarFazendas = view.findViewById(R.id.bListarfazendas);
        this.bAlterarPerfil = view.findViewById(R.id.bAlterarDados);

        // implementação de Button para listar fazendas
        this.bListarFazendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getContext(), ListarFazendaActivity.class);
                startActivity(t);
            }
        });

        // implementação de Button para tela de atualizar informações
        this.bAlterarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getActivity().getApplicationContext(), AtualizarClienteActivity.class);
                startActivity(t);
            }
        });

        return view;
    }

}
