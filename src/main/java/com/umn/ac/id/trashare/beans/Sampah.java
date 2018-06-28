package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="sampah")
public class Sampah {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sampah")
    private int idSampah;

    @Column(name = "harga_beli_lapak")
    private int hargaBeliLapak;

    @Column(name = "harga_beli_nasabah")
    private int hargaBeliNasabah;

    @Column(name = "nama_sampah", length = 50)
    private String namaSampah;

    @Column(name = "tipe_sampah", length = 50)
    private String tipeSampah;

    // Constructor
    public Sampah(){}

    public Sampah(int hargaBeliLapak, int hargaBeliNasabah, String namaSampah, String tipeSampah) {
        this.hargaBeliLapak = hargaBeliLapak;
        this.hargaBeliNasabah = hargaBeliNasabah;
        this.namaSampah = namaSampah;
        this.tipeSampah = tipeSampah;
    }

    // Setter
    public void setIdSampah(int idSampah) {
        this.idSampah = idSampah;
    }

    public void setHargaBeliLapak(int hargaBeliLapak) {
        this.hargaBeliLapak = hargaBeliLapak;
    }

    public void setHargaBeliNasabah(int hargaBeliNasabah) {
        this.hargaBeliNasabah = hargaBeliNasabah;
    }

    public void setNamaSampah(String namaSampah) {
        this.namaSampah = namaSampah;
    }

    public void setTipeSampah(String tipeSampah) {
        this.tipeSampah = tipeSampah;
    }

    // Getter
    public int getIdSampah() {
        return idSampah;
    }

    public int getHargaBeliLapak() {
        return hargaBeliLapak;
    }

    public int getHargaBeliNasabah() {
        return hargaBeliNasabah;
    }

    public String getNamaSampah() {
        return namaSampah;
    }

    public String getTipeSampah() {
        return tipeSampah;
    }
}
