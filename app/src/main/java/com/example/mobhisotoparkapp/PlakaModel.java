package com.example.mobhisotoparkapp;

public class PlakaModel {
    int PLAKA_ID;
    String PLAKA;
    String GIRIS_SAATI;


    public PlakaModel(int plakaID, String plaka, String girisSaati){
        this.PLAKA_ID=plakaID;
        this.PLAKA=plaka;
        this.GIRIS_SAATI=girisSaati;

    }
    public PlakaModel(){

    }

   /* @Override
    public String toString() {
        return "PlakaModel{" +
                "PLAKA_ID=" + PLAKA_ID +
                ", PLAKA='" + PLAKA + '\'' +
                ", GIRIS_SAATI='" + GIRIS_SAATI + '\'' +
                '}';
    }*/
   @Override
   public String toString() {
       return PLAKA;
   }
    public int getPLAKA_ID() {
        return PLAKA_ID;
    }

    public void setPLAKA_ID(int PLAKA_ID) {
        this.PLAKA_ID = PLAKA_ID;
    }

    public String getPLAKA() {
        return PLAKA;
    }

    public void setPLAKA(String PLAKA) {
        this.PLAKA = PLAKA;
    }

    public String getGIRIS_SAATI() {
        return GIRIS_SAATI;
    }

    public void setGIRIS_SAATI(String GIRIS_SAATI) {
        this.GIRIS_SAATI = GIRIS_SAATI;
    }

}