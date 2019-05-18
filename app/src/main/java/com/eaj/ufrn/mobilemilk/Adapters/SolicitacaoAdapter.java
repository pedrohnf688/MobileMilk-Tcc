package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

public class SolicitacaoAdapter extends RecyclerView.Adapter {

    private List<SolicitacaoGetDto> listaSolicitacoes;
    private SolicitacaoGetDto solicitacaoEscolhida;
    private Context context;

    public SolicitacaoAdapter(List<SolicitacaoGetDto> lista, Context context){
        this.context = context;
        this.listaSolicitacoes = lista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_listar_solicitacoes, viewGroup, false);
        SolicitacaoViewHolder holder = new SolicitacaoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SolicitacaoViewHolder mvn = (SolicitacaoViewHolder) viewHolder;
        this.solicitacaoEscolhida = this.listaSolicitacoes.get(position);
        mvn.numeroSolicitacao.setText(this.solicitacaoEscolhida.getFazenda().getNome());
        mvn.statusSolicitacao.setText(this.solicitacaoEscolhida.getStatus());
    }

    @Override
    public int getItemCount() {
        return listaSolicitacoes == null ? 0 : listaSolicitacoes.size();
    }

    public class SolicitacaoViewHolder extends RecyclerView.ViewHolder {

        final ImageView imagem;
        final TextView numeroSolicitacao;
        final TextView statusSolicitacao;

        public SolicitacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.image);
            numeroSolicitacao = itemView.findViewById(R.id.numeracaoSolicitacao);
            statusSolicitacao = itemView.findViewById(R.id.statusSolicitacao);
        }
    }

}
