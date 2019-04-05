package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

public class FazendaAdapter extends RecyclerView.Adapter {

    private List<Fazenda> listaFazendas;
    private Fazenda fazendaEscolhida;
    private Context context;

    public FazendaAdapter(List<Fazenda> listaFazendas, Context context) {
        this.listaFazendas = listaFazendas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FazendaViewHolder extends RecyclerView.ViewHolder{

        /*
        *   ADICIONAR OS ELEMENTOS DA VIEW DE LISTAR FAZENDAS
        * */
        final TextView empresaFazendaList;
        final TextView estadoFazendaList;
        final ImageView imagefazendaList;

        public FazendaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.empresaFazendaList = itemView.findViewById(R.id.empresaFazendaList);
            this.estadoFazendaList = itemView.findViewById(R.id.estadoFazendaList);
            this.imagefazendaList = itemView.findViewById(R.id.imageFazendaList);
        }
    }

}
