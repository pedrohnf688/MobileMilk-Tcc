package com.eaj.ufrn.mobilemilk.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.eaj.ufrn.mobilemilk.Activity.Leitor;
import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Adapters.SolicitacaoAdapter;
import com.eaj.ufrn.mobilemilk.Enum.Status;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitacoesFragment extends Fragment {

   private List<SolicitacaoGetDto> listaSolicitacoes = new ArrayList<>();
   private RecyclerView recyclerSolicitacao;

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
        this.recyclerSolicitacao = view.findViewById(R.id.recyclerViewListarSolicitacoes);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerSolicitacao.setLayoutManager(layout);
        recyclerSolicitacao.setItemAnimator(new DefaultItemAnimator());

        // Implementando listener de cliques
        recyclerSolicitacao.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getActivity().getApplicationContext(), recyclerSolicitacao, new MeuRecyclerViewClickListener.OnItemClickListener(){
                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getContext(), "Clicou LONGO", Toast.LENGTH_SHORT).show();
                        Intent t = new Intent(getContext(), Leitor.class);
                        startActivity(t);
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

        SharedPreferences prefs = getActivity().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

        Call<List<SolicitacaoGetDto>> call = new RetrofitConfig().getClienteService().buscarSolicitacoes(
                prefs.getString("accessId", "default"),
                prefs.getString("accessToken", "default")
        );
        call.enqueue(new Callback<List<SolicitacaoGetDto>>() {
            @Override
            public void onResponse(Call<List<SolicitacaoGetDto>> call, Response<List<SolicitacaoGetDto>> response) {
                listaSolicitacoes.clear();
                if(response.isSuccessful()){
                    listaSolicitacoes = response.body();

                    recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getActivity().getApplicationContext()));
                }
                else {
                    Log.i("ResponseError", "Message: " + response.message());
                    Log.i("ResponseError", "Error Body: " + response.errorBody());
                    Log.i("ResponseError", "Http Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SolicitacaoGetDto>> call, Throwable t) {
                Log.i("failure", "Message: " + t.getMessage());
                Log.i("failure", "Vause: " + t.getCause());
            }
        });
    }
}
