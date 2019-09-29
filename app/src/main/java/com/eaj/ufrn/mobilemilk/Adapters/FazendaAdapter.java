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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
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
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_listar_fazendas, viewGroup, false);
        FazendaViewHolder holder = new FazendaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        FazendaViewHolder holder = (FazendaViewHolder) viewHolder;

        this.fazendaEscolhida = this.listaFazendas.get(position);
        boolean foto = fazendaEscolhida.getFotoFazenda().getFileDownloadUri() != null;

        holder.nomeFazendaCard.setText(this.fazendaEscolhida.getNomeFazenda());
        holder.localizacaoFazendaCard.setText(this.fazendaEscolhida.getCidade() + "/" + this.fazendaEscolhida.getEstado());

        String descricao = "Fazenda pertence ao " + this.fazendaEscolhida.getCliente().getNome();

        holder.descricaoFazendaCard.setText(descricao);

        if(foto){
            loadProfileIcon(fazendaEscolhida.getFotoFazenda().getFileDownloadUri(),holder.imageView2);
        }else{
            holder.imageView2.setImageResource(R.drawable.fazenda);
        }


    }

    @Override
    public int getItemCount() {
        return this.listaFazendas == null ? 0: listaFazendas.size();
    }

    public class FazendaViewHolder extends RecyclerView.ViewHolder{

        /*
        *   ADICIONAR OS ELEMENTOS DA VIEW DE LISTAR FAZENDAS
        * */
        final TextView nomeFazendaCard;
        final TextView localizacaoFazendaCard;
        final TextView descricaoFazendaCard;
        final ImageView imageView2;

        public FazendaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeFazendaCard = itemView.findViewById(R.id.nomeFazendaCard);
            localizacaoFazendaCard = itemView.findViewById(R.id.localizacaoFazendaCard);
            descricaoFazendaCard = itemView.findViewById(R.id.descricaoFazendaCard);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }

    }

    public static void loadProfileIcon(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.userlogin)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
    }
}
