package com.eaj.ufrn.mobilemilk.Fragments;

import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Activity.ComprovanteSolicitacaoActivity;
import com.eaj.ufrn.mobilemilk.Activity.DetalheSolicitacaoActivity;
import com.eaj.ufrn.mobilemilk.Activity.VisualizarFotoAmostrasActivity;
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


        // Implementando listener de cliques
        recyclerSolicitacao.addOnItemTouchListener(
                new MeuRecyclerViewClickListener(getActivity().getApplicationContext(), recyclerSolicitacao, new MeuRecyclerViewClickListener.OnItemClickListener(){
                    @Override
                    public void onItemLongClick(View view, final int position) {
                        final SolicitacaoGetDto s = listaSolicitacoes.get(position);

                        AlertDialog.Builder alertDeletarSolicitacao = new AlertDialog.Builder(getContext());

                        final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_comprovante_solicitacao,null);
                        alertDeletarSolicitacao.setView(dialogView);

                        Button buttonSolicitacaoAmostras = dialogView.findViewById(R.id.buttonSolicitacaoAmostras);
                        Button buttonSolicitacaoComprovante = dialogView.findViewById(R.id.buttonSolicitacaoComprovante);

                        final AlertDialog alertDialog = alertDeletarSolicitacao.create();
                        alertDialog.show();

                        buttonSolicitacaoComprovante.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(getActivity().getApplicationContext(), ComprovanteSolicitacaoActivity.class));
                                Intent i = new Intent(getActivity().getApplicationContext(), ComprovanteSolicitacaoActivity.class);
                                i.putExtra("SolicitacaoID",s.getId());
                                startActivity(i);

                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Cadastrado do Comprovante", Toast.LENGTH_SHORT).show();

                            }
                        });
                        
                        buttonSolicitacaoAmostras.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity().getApplicationContext(), VisualizarFotoAmostrasActivity.class);
                                i.putExtra("SolicitacaoID",s.getId());
                                startActivity(i);

                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Visualizar amostras recebidas", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                    @Override
                    public void onItemClick(View view, int position){
                        //Toast.makeText(getContext(), "Clicou CURTO", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity().getApplicationContext(), DetalheSolicitacaoActivity.class);
                        i.putExtra("recyclerPosition", position);
                        startActivity(i);
                    }
                })
        );



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
