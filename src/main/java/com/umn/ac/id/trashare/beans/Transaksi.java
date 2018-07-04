package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi")
    private int idTransaksi;

    @OneToOne
    @JoinColumn(name = "id_member")
    private Member member;

    @OneToOne
    @JoinColumn(name = "id_hadiah")
    private Hadiah hadiah;

    @Column(name = "tanggal_transaksi")
    private Date tanggalTransaksi;

    public Transaksi(Member member, Hadiah hadiah, Date tanggalTransaksi) {
        this.member = member;
        this.hadiah = hadiah;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Transaksi() {
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Hadiah getHadiah() {
        return hadiah;
    }

    public void setHadiah(Hadiah hadiah) {
        this.hadiah = hadiah;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
