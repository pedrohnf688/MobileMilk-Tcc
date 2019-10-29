package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FotoSolicitacaoAdapter extends RecyclerView.Adapter{

    List<Arquivo> arquivoList;
    private Arquivo arquivoEscolhido;
    private Context context;

    public FotoSolicitacaoAdapter(List<Arquivo> arquivoList, Context context) {
        this.arquivoList = arquivoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_fotos_amostras_solicitacoes, viewGroup, false);
        FotoSolicitacaoAdapter.FotoSolicitacaoViewHolder viewHolder = new FotoSolicitacaoAdapter.FotoSolicitacaoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final FotoSolicitacaoAdapter.FotoSolicitacaoViewHolder holder = (FotoSolicitacaoAdapter.FotoSolicitacaoViewHolder) viewHolder;
        arquivoEscolhido = arquivoList.get(position);


        SharedPreferences prefs2 = context.getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<Arquivo>> call3 = new RetrofitConfig().getArquivoService()
                .fileUrlSolicitacao(arquivoEscolhido.getFotoSolicitacao().getId(),
                        prefs2.getString("accessToken", "default"));


        call3.enqueue(new Callback<List<Arquivo>>() {
            @Override
            public void onResponse(Call<List<Arquivo>> call, Response<List<Arquivo>> response) {
                if (response.isSuccessful()) {

                    Log.i("aaaaaa",""+response.body());
                    loadProfileIcon(response.body().get(position).getFileDownloadUri(), holder.imageViewFotoSolicitacao);
                    //Toast.makeText(getApplicationContext(),"Só Sucesso :)", Toast.LENGTH_SHORT).show();
                }else {

                    Log.i("URL ERRADA:", "" + response.body());
                    Log.i("URL ToString:", "" + response.toString());
                    // Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Arquivo>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                Log.i("falha 2 message:", t.getMessage());
                Log.i("falha 2 stackTrace:",t.getStackTrace().toString());
                Log.i("falha 2 localize:",t.getLocalizedMessage());


            }
        });


    }

    @Override
    public int getItemCount() {
        return arquivoList.isEmpty() ? 0 : arquivoList.size();
    }


    public class FotoSolicitacaoViewHolder extends RecyclerView.ViewHolder{

        final ImageView imageViewFotoSolicitacao;

        public FotoSolicitacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFotoSolicitacao = itemView.findViewById(R.id.imageViewfotoSolicitacao);
        }
    }


    public static void loadProfileIcon(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_insert_drive_file_black_24dp)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
    }
}
