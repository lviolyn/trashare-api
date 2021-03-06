package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.time.DateTimeException;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "kegiatan")
public class Kegiatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kegiatan")
    private int idKegiatan;

    @Column(name = "nama_kegiatan", length = 50)
    private String namaKegiatan;

    @Column(name = "deskripsi_kegiatan")
    private String deskripsiKegiatan;

    @Column(name = "tanggal_kegiatan")
    private Date tanggalKegiatan;

    @Column(name = "foto_kegiatan")
    private byte[] fotoKegiatan;

    @Column(name = "tempat_kegiatan")
    private String tempatKegiatan;

    // Banyak (Kegiatan) ke 1 BS --> 1 BS punya > 1 Kegiatan
    @ManyToOne(targetEntity = BankSampah.class)
    @JoinColumn(name = "id_bank_sampah", nullable = false, referencedColumnName = "id_bank_sampah")
    private BankSampah idBankSampah;

    // Constructor
    public Kegiatan() {
    }

    public Kegiatan(String namaKegiatan, String deskripsiKegiatan, Date tanggalKegiatan, byte[] fotoKegiatan, String tempatKegiatan, BankSampah idBankSampah) {
        this.namaKegiatan = namaKegiatan;
        this.deskripsiKegiatan = deskripsiKegiatan;
        this.tanggalKegiatan = tanggalKegiatan;
        this.fotoKegiatan = fotoKegiatan;
        this.tempatKegiatan = tempatKegiatan;
        this.idBankSampah = idBankSampah;
    }

    // Setter
    public void setIdKegiatan(int idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public void setDeskripsiKegiatan(String deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }

    public void setTanggalKegiatan(Date tanggalKegiatan) {
        this.tanggalKegiatan = tanggalKegiatan;
    }

    public void setIdBankSampah(BankSampah idBankSampah) {
        this.idBankSampah = idBankSampah;
    }

    public void setFotoKegiatan(byte[] fotoKegiatan) {
        this.fotoKegiatan = fotoKegiatan;
    }

    public void setTempatKegiatan(String tempatKegiatan) {
        this.tempatKegiatan = tempatKegiatan;
    }

    // Getter
    public int getIdKegiatan() {
        return idKegiatan;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan;
    }

    public Date getTanggalKegiatan() {
        return tanggalKegiatan;
    }

    // Gak bisa lihat kegiatan itu punyanya BS mana
    @JsonIgnore
    public BankSampah getIdBankSampah() { return idBankSampah; }

    public byte[] getFotoKegiatan() {
        return fotoKegiatan;
    }

    public String getTempatKegiatan() {
        return tempatKegiatan;
    }
}
