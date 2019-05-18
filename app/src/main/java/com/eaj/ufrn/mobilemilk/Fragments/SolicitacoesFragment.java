package com.eaj.ufrn.mobilemilk.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Adapters.SolicitacaoAdapter;
import com.eaj.ufrn.mobilemilk.Enum.Status;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitacoesFragment extends Fragment {

    private List<Solicitacao> listaSolicitacoes = new ArrayList<>();

    // Required empty constructor
    public SolicitacoesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.fragment_listar_solicitacoes, container, false);

        /*
        *   Implementação do recyclerView lista de solicitações
        *   obs = o Adapter do recycler será setado no método onStart do ciclo de vida da aplicação,
        *   após a chamada da requisição GET do recurso /cliente/{id}/solicitacao
        * */
        final RecyclerView recyclerSolicitacao = view.findViewById(R.id.recyclerViewListarSolicitacoes);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerSolicitacao.setLayoutManager(layout);
        recyclerSolicitacao.setItemAnimator(new DefaultItemAnimator());

        // Implementando listener de cliques
        recyclerSolicitacao.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getActivity().getApplicationContext(), recyclerSolicitacao, new MeuRecyclerViewClickListener.OnItemClickListener(){
                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getContext(), "Clicou LONGO", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemClick(View view, int position){
                        Toast.makeText(getContext(), "Clicou CURTO", Toast.LENGTH_SHORT).show();
                    }
                })
        );


        recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getContext()));
        return view;
    }

    /*
    *   Responsável por carregar as solicitações do Cliente ...
    * */
    @Override
    public void onStart(){
        super.onStart();


    }
}
