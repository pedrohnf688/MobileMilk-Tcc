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

import com.eaj.ufrn.mobilemilk.Activity.AnalisesActivity;
import com.eaj.ufrn.mobilemilk.Activity.DetalheSolicitacaoActivity;
import com.eaj.ufrn.mobilemilk.Activity.QrCodeActivity;
import com.eaj.ufrn.mobilemilk.Adapters.SolicitacaoAdapter;
import com.eaj.ufrn.mobilemilk.Gesture.MeuRecyclerViewClickListener;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QrCodeFragment extends Fragment {


    private List<SolicitacaoGetDto> listaSolicitacoes = new ArrayList<>();
    private RecyclerView recyclerSolicitacao;

    public QrCodeFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.activity_leitor, group, false);

        recyclerSolicitacao = view.findViewById(R.id.recyclerViewSolicitacao);


        // Implementando listener de cliques
        recyclerSolicitacao.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getActivity().getApplicationContext(), recyclerSolicitacao, new MeuRecyclerViewClickListener.OnItemClickListener(){
                    @Override
                    public void onItemLongClick(View view, int position) {
                        //Toast.makeText(getContext(), "Clicou LONGO", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity().getApplicationContext(), QrCodeActivity.class));
                    }

                    @Override
                    public void onItemClick(View view, int position){
                        //Toast.makeText(getContext(), "Clicou CURTO", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity().getApplicationContext(), AnalisesActivity.class);
                        i.putExtra("recyclerPosition", position);
                        startActivity(i);

                    }
                })
        );


        recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getContext()));


        return view;

    }

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

                    recyclerSolicitacao.setAdapter(new SolicitacaoAdapter(listaSolicitacoes, getContext()));

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    recyclerSolicitacao.setLayoutManager(layout);
                    recyclerSolicitacao.setItemAnimator(new DefaultItemAnimator());

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