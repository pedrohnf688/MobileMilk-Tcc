package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
        for(Fazenda f: listaFazendas)
            Log.i("fazenda", "fazenda nome: " + f.getNome());
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_listar_fazendas, viewGroup, false);
        FazendaViewHolder holder = new FazendaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        FazendaViewHolder holder = (FazendaViewHolder) viewHolder;
        this.fazendaEscolhida = this.listaFazendas.get(position);
        Log.i("ECOLHIDA", "nome: "+fazendaEscolhida.getNome());
        holder.empresaFazendaList.setText(this.fazendaEscolhida.getNome());
        //holder.imagefazendaList.setImageResource();
    }

    @Override
    public int getItemCount() {
        return this.listaFazendas == null ? 0: listaFazendas.size();
    }

    public class FazendaViewHolder extends RecyclerView.ViewHolder{

        /*
        *   ADICIONAR OS ELEMENTOS DA VIEW DE LISTAR FAZENDAS
        * */
        final TextView empresaFazendaList;
        //final ImageView imagefazendaList;

        public FazendaViewHolder(@NonNull View itemView) {
            super(itemView);
            empresaFazendaList = itemView.findViewById(R.id.empresaFazendaList);
            //this.imagefazendaList = itemView.findViewById(R.id.imageFazendaList);
        }

    }

}
