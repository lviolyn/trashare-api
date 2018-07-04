package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "setor_sampah")
public class SetorSampah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_setor_sampah")
    private int idSetorSampah;

    @OneToOne
    @JoinColumn(name = "id_sampah")
    private Sampah sampah;

    @OneToOne
    @JoinColumn(name = "id_member")
    private Member member;

    @Column(name = "nama_sampah")
    private String namaSampah;

    @Column(name = "berat")
    private int berat;

    @Column(name = "tanggal_setor")
    private Date tanggalSetor;

    public SetorSampah() {
    }

    public SetorSampah(Sampah sampah, Member member, String namaSampah, int berat, Date tanggalSetor) {
        this.sampah = sampah;
        this.member = member;
        this.namaSampah = namaSampah;
        this.berat = berat;
        this.tanggalSetor = tanggalSetor;
    }

    public int getIdSetorSampah() {
        return idSetorSampah;
    }

    public void setIdSetorSampah(int idSetorSampah) {
        this.idSetorSampah = idSetorSampah;
    }

    public Sampah getSampah() {
        return sampah;
    }

    public void setSampah(Sampah sampah) {
        this.sampah = sampah;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public Date getTanggalSetor() {
        return tanggalSetor;
    }

    public void setTanggalSetor(Date tanggalSetor) {
        this.tanggalSetor = tanggalSetor;
    }

    public String getNamaSampah() {
        return namaSampah;
    }

    public void setNamaSampah(String namaSampah) {
        this.namaSampah = namaSampah;
    }
}
