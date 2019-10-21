package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

public class AnalisesSolicitadasAdapter extends RecyclerView.Adapter {

    private List<Analise> listaAnalise;
    private Analise analiseEscolhida;
    private Context context;

    public AnalisesSolicitadasAdapter(List<Analise> listaAnalise, Context context) {
        this.listaAnalise = listaAnalise;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.inflater_analises_solicitadas, viewGroup, false);
        AnalisesSolicitadasAdapter.AnalisesSolicitadasViewHolder viewHolder = new AnalisesSolicitadasAdapter.AnalisesSolicitadasViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        AnalisesSolicitadasAdapter.AnalisesSolicitadasViewHolder holder = (AnalisesSolicitadasAdapter.AnalisesSolicitadasViewHolder) viewHolder;
        analiseEscolhida = listaAnalise.get(i);
        List<AnalisesSolicitadas> analises = analiseEscolhida.getAnalisesSolicitadas();
        String analises1 = analises.toString().replace("[","").replace("]","");

        holder.analisesSolicitadas.setText(analises1);

    }

    @Override
    public int getItemCount() {
        return this.listaAnalise.isEmpty() ? 0 : this.listaAnalise.size();
    }


    public class AnalisesSolicitadasViewHolder extends RecyclerView.ViewHolder{

        final TextView analisesSolicitadas;

        public AnalisesSolicitadasViewHolder(@NonNull View itemView) {
            super(itemView);
            analisesSolicitadas = itemView.findViewById(R.id.analisesSolicitadastexto);
        }
    }
}
