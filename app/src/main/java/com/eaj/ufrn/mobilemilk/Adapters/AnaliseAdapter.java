package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eaj.ufrn.mobilemilk.Enum.EnumEspecie;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

public class AnaliseAdapter extends RecyclerView.Adapter {

    private List<Analise> listaAnalise;
    private Analise analiseEscolhida;
    private Context context;

    public AnaliseAdapter(List<Analise> listaAnalise, Context context) {
        this.listaAnalise = listaAnalise;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflater_listar_analise, viewGroup, false);
        AnaliseViewHolder viewHolder = new AnaliseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        AnaliseViewHolder holder = (AnaliseViewHolder) viewHolder;
        analiseEscolhida = listaAnalise.get(position);

        EnumEspecie especie = analiseEscolhida.getEspecie();
        List<Produtos> produto = analiseEscolhida.getProdutos();

        holder.numAmostras.setText("Quantidade de Amostras: " + analiseEscolhida.getQuantidadeAmostras());
        holder.tipoLeite.setText("Espécie: " + especie.name());

        String produto1 = produto.toString().replace("[","").replace("]","");

        holder.origemLeite.setText("Produto: " + produto1);
    }

    @Override
    public int getItemCount() {
        return this.listaAnalise.isEmpty() ? 0 : this.listaAnalise.size();
    }

    public class AnaliseViewHolder extends RecyclerView.ViewHolder{

        final TextView numAmostras;
        final TextView tipoLeite;
        final TextView origemLeite;

        public AnaliseViewHolder(@NonNull View itemView) {
            super(itemView);
            numAmostras = itemView.findViewById(R.id.numAmostrasAnalise);
            tipoLeite = itemView.findViewById(R.id.tipoLeiteAnalise);
            origemLeite = itemView.findViewById(R.id.origemDoLeiteAnalise);
        }
    }
}
