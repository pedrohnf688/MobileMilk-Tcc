package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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
        final FazendaViewHolder holder = (FazendaViewHolder) viewHolder;

        this.fazendaEscolhida = this.listaFazendas.get(position);

        holder.nomeFazendaCard.setText(this.fazendaEscolhida.getNomeFazenda());
        holder.localizacaoFazendaCard.setText(this.fazendaEscolhida.getCidade() + "/" + this.fazendaEscolhida.getEstado());

        String descricao = "Fazenda pertence ao " + this.fazendaEscolhida.getCliente().getNome();

        holder.descricaoFazendaCard.setText(descricao);



        SharedPreferences prefs2 = context.getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<Arquivo> call3 = new RetrofitConfig().getArquivoService()
                .fileUrlFazenda(fazendaEscolhida.getId().toString(),
                        prefs2.getString("accessToken", "default"));

        call3.enqueue(new Callback<Arquivo>() {
            @Override
            public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {

                if (response.isSuccessful()) {
                    Log.i("asaa",""+response.body());
                    loadProfileIcon(response.body().getFileDownloadUri(),holder.imageView2);

                }else {
                    Log.i("URL ERRADA:", "" + response.body());
                }
            }
            @Override
            public void onFailure(Call<Arquivo> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "Falha 2:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("falha 2 message:", t.getMessage());
                Log.i("falha 2 stackTrace:",t.getStackTrace().toString());
                Log.i("falha 2 localize:",t.getLocalizedMessage());

            }
        });






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
                        .placeholder(R.drawable.fazenda)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
    }
}
