package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "history_saldo")
public class HistorySaldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_history_saldo")
    private int idHistorySaldo;

    @OneToOne
    @JoinColumn(name = "id_member")
    private Member member;

    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "tanggal_transaksi")
    private Date tanggalTransaksi;


    public HistorySaldo(Member member, int jumlah, Date tanggalTransaksi) {
        this.member = member;
        this.jumlah = jumlah;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public HistorySaldo() {
    }

    public int getIdHistorySaldo() {
        return idHistorySaldo;
    }

    public void setIdHistorySaldo(int idHistorySaldo) {
        this.idHistorySaldo = idHistorySaldo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
