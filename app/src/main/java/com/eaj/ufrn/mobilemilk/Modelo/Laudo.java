package com.eaj.ufrn.mobilemilk.Modelo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Laudo {

    private Integer laudo_id;
    private String batchId;
    private String sequence;
    private LocalDate date;
    private String sampleid;
    private String fat;
    private String trupro;
    private String totpro;
    private String casein;
    private String solids;
    private String snf;
    private String fpd;
    private String urea;
    private String ccs;
    private String cel;
    private String ph;
    private String den;
    private String rant;
    private String cbt;
    private String cmt;
    private String gord;
    private String prot;
    private String lact;
    private String esd;
    private String pc;

    public Laudo() {
    }

    public Integer getLaudo_id() {
        return laudo_id;
    }

    public void setLaudo_id(Integer laudo_id) {
        this.laudo_id = laudo_id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSampleid() {
        return sampleid;
    }

    public void setSampleid(String sampleid) {
        this.sampleid = sampleid;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getTrupro() {
        return trupro;
    }

    public void setTrupro(String trupro) {
        this.trupro = trupro;
    }

    public String getTotpro() {
        return totpro;
    }

    public void setTotpro(String totpro) {
        this.totpro = totpro;
    }

    public String getCasein() {
        return casein;
    }

    public void setCasein(String casein) {
        this.casein = casein;
    }

    public String getSolids() {
        return solids;
    }

    public void setSolids(String solids) {
        this.solids = solids;
    }

    public String getSnf() {
        return snf;
    }

    public void setSnf(String snf) {
        this.snf = snf;
    }

    public String getFpd() {
        return fpd;
    }

    public void setFpd(String fpd) {
        this.fpd = fpd;
    }

    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getCcs() {
        return ccs;
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    public String getRant() {
        return rant;
    }

    public void setRant(String rant) {
        this.rant = rant;
    }

    public String getCbt() {
        return cbt;
    }

    public void setCbt(String cbt) {
        this.cbt = cbt;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGord() {
        return gord;
    }

    public void setGord(String gord) {
        this.gord = gord;
    }

    public String getProt() {
        return prot;
    }

    public void setProt(String prot) {
        this.prot = prot;
    }

    public String getLact() {
        return lact;
    }

    public void setLact(String lact) {
        this.lact = lact;
    }

    public String getEsd() {
        return esd;
    }

    public void setEsd(String esd) {
        this.esd = esd;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }


    @Override
    public String toString() {
        return "Laudo [laudo_id=" + laudo_id + ", batchId=" + batchId + ", sequence=" + sequence + ", date=" + date
                + ", sampleid=" + sampleid + ", fat=" + fat + ", trupro=" + trupro + ", totpro=" + totpro + ", casein="
                + casein + ", solids=" + solids + ", snf=" + snf + ", fpd=" + fpd + ", urea=" + urea + ", ccs=" + ccs
                + ", cel=" + cel + ", ph=" + ph + ", den=" + den + ", rant=" + rant + ", cbt=" + cbt + ", cmt=" + cmt
                + ", gord=" + gord + ", prot=" + prot + ", lact=" + lact + ", esd=" + esd + ", pc=" + pc + "]";
    }

}
