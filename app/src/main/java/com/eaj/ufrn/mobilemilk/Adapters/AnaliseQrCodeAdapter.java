package com.eaj.ufrn.mobilemilk.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumEspecie;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;
import com.eaj.ufrn.mobilemilk.Modelo.Amostra;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.R;

import java.util.List;

public class AnaliseQrCodeAdapter extends RecyclerView.Adapter {

    private List<Analise> listaAnalise;
    private Analise analiseEscolhida;
    private Context context;
    int contFalso = 0,contVerdade = 0;


    public AnaliseQrCodeAdapter(List<Analise> listaAnalise, Context context) {
        this.listaAnalise = listaAnalise;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_lista_analise_qrcode, viewGroup, false);
        AnaliseQrCodeAdapter.AnaliseQrCodeViewHolder viewHolder = new AnaliseQrCodeAdapter.AnaliseQrCodeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        AnaliseQrCodeAdapter.AnaliseQrCodeViewHolder holder = (AnaliseQrCodeAdapter.AnaliseQrCodeViewHolder) viewHolder;

        analiseEscolhida = listaAnalise.get(position);
        EnumEspecie especie = analiseEscolhida.getEspecie();
        List<Produtos> produto = analiseEscolhida.getProdutos();



       holder.tipoLeite.setText("Espécie: " + especie.name());
       holder.numAmostras.setText("Quantidade de Amostras: " + analiseEscolhida.getQuantidadeAmostras());

       String produto1 = produto.toString().replace("[","").replace("]","");

       holder.origemLeite.setText("Produto: " + produto1);
       holder.totalAmostras.setText(""+analiseEscolhida.getListaAmostras());


    }

    @Override
    public int getItemCount() {
        return this.listaAnalise.isEmpty() ? 0 : this.listaAnalise.size();    }

    public class AnaliseQrCodeViewHolder extends RecyclerView.ViewHolder{

        final TextView numAmostras;
        final TextView tipoLeite;
        final TextView origemLeite;
        final TextView amostrasFinalizadas;
        final TextView totalAmostras;

        public AnaliseQrCodeViewHolder(@NonNull View itemView) {
            super(itemView);
            numAmostras = itemView.findViewById(R.id.numAmostrasAnaliseQrCode);
            tipoLeite = itemView.findViewById(R.id.tipoLeiteAnaliseQrCode);
            origemLeite = itemView.findViewById(R.id.origemDoLeiteAnaliseQrCode);
            amostrasFinalizadas = itemView.findViewById(R.id.amostrasFinalizadas);
            totalAmostras = itemView.findViewById(R.id.totalAmostras);
        }
    }
}
