package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompatSideChannelService;
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
        mvn.nomeFazendaSolicitacao.setText(this.solicitacaoEscolhida.getFazenda().getNome());
        mvn.numeroAnaliseSolicitacao.setText("Numero de Análises: " + this.solicitacaoEscolhida.getListaAnalise().size());
        mvn.statusSolicitacao.setText(this.solicitacaoEscolhida.getStatus());

        if(mvn.statusSolicitacao.getText().toString().equals("PENDENTE"))
            mvn.statusSolicitacao.setTextColor(context.getResources().getColor(R.color.RedFloat));
        else if(mvn.statusSolicitacao.getText().toString().equals("APROVADO"))
            mvn.statusSolicitacao.setTextColor(context.getResources().getColor(R.color.YELLOW));
        else
            mvn.statusSolicitacao.setTextColor(context.getResources().getColor(R.color.GreenB));
    }

    @Override
    public int getItemCount() {
        return listaSolicitacoes == null ? 0 : listaSolicitacoes.size();
    }

    public class SolicitacaoViewHolder extends RecyclerView.ViewHolder {

        final TextView nomeFazendaSolicitacao;
        final TextView numeroAnaliseSolicitacao;
        final TextView statusSolicitacao;

        public SolicitacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeFazendaSolicitacao = itemView.findViewById(R.id.nomeFazendaSolicitacao);
            numeroAnaliseSolicitacao = itemView.findViewById(R.id.qtdAnalisesCadastradasSolicitacao);
            statusSolicitacao = itemView.findViewById(R.id.statusSolicitacao);
        }
    }

}
