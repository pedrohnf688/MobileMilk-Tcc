package com.eaj.ufrn.mobilemilk.ModeloDTO;

public class AmostrasDetalhes {

    private int totalAmostras;
    private int amostrasColetadas;

    public AmostrasDetalhes(int totalAmostras, int amostrasColetadas) {
        this.totalAmostras = totalAmostras;
        this.amostrasColetadas = amostrasColetadas;
    }

    public int getTotalAmostras() {
        return totalAmostras;
    }

    public void setTotalAmostras(int totalAmostras) {
        this.totalAmostras = totalAmostras;
    }

    public int getAmostrasColetadas() {
        return amostrasColetadas;
    }

    public void setAmostrasColetadas(int amostrasColetadas) {
        this.amostrasColetadas = amostrasColetadas;
    }

    @Override
    public String toString() {
        return "AmostrasDetalhes{" +
                "totalAmostras=" + totalAmostras +
                ", amostrasColetadas=" + amostrasColetadas +
                '}';
    }
}
