package com.reza.jenazah;

/**
 * Created by REZA on 07/01/2018.
 */

public class hitung {
    private int pulau;
    private int waktu;
    private int via;
    private int kurir;
    private int berat;
    private int total_tarif;


    public int getPulau() {
        return pulau;
    }

    public void setPulau(int pulau) {
        this.pulau = pulau;
    }

    public int getKurir() {
        return kurir;
    }

    public void setKurir(int kurir) {
        this.kurir = kurir;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = (3000000/waktu);
    }
    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = 10000 * berat;
    }

    public int getVia() {
        return via;
    }

    public void setVia(int via) {
        this.via = via;
    }

    public int getTotal_tarif() {
        return total_tarif;
    }

    public void setTotal_tarif(int kurir ,int pulau, int waktu, int via, int berat) {
        this.total_tarif = (berat+kurir+pulau+waktu+via);
    }
}
