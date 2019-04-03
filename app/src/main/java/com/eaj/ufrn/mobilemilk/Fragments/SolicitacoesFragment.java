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

        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));
        listaSolicitacoes.add(new Solicitacao("DEU CERTO", Status.APROVADO));

        View view = inflater.inflate(R.layout.fragment_listar_solicitacoes, container, false);

        final RecyclerView recyclerSolicitacao = view.findViewById(R.id.recyclerViewListarSolicitacoes);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerSolicitacao.setLayoutManager(layout);
        recyclerSolicitacao.setItemAnimator(new DefaultItemAnimator());
        /*
        Call<List<Solicitacao>> call = Cliente.listarSolicitacoes(1); // Faz uma chamada do tipo GET ao recurso solicitações.
        call.enqueue(new Callback<List<Solicitacao>>() {
            @Override
            public void onResponse(Call<List<Solicitacao>> call, Response<List<Solicitacao>> response) {
                if(response.isSuccessful()) {
                    Log.i("LISTOU", "Success List Solicitation");
                    listaSolicitacoes = response.body();
                    recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getContext()));
                }
                else{
                    Log.i("LISTOU", "Error List Solicitation");
                }
            }

            @Override
            public void onFailure(Call<List<Solicitacao>> call, Throwable t) {
                Log.i("ERROR", "ERROR");
                Toast.makeText(getContext(), "Sem internet", Toast.LENGTH_SHORT);
            }
        });*/
        recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getContext()));
        return view;
    }
}
