package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "hadiah")
public class Hadiah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hadiah")
    private int idHadiah;

    @Column(name = "nama_hadiah", length = 50)
    private String namaHadiah;

    @Column(name = "poin")
    private int poin;

    @Column(name = "deskripsi_hadiah")
    private String deskripsiHadiah;

    @Column(name = "sponsor", length = 50)
    private String sponsor;

    @Column(name = "periode_tukar")
    private Date periodeTukar;

    public Hadiah() {
    }

    public Hadiah(String namaHadiah, int poin, String deskripsiHadiah, String sponsor, Date periodeTukar) {
        this.namaHadiah = namaHadiah;
        this.poin = poin;
        this.deskripsiHadiah = deskripsiHadiah;
        this.sponsor = sponsor;
        this.periodeTukar = periodeTukar;
    }

    public int getIdHadiah() {
        return idHadiah;
    }

    public void setIdHadiah(int idHadiah) {
        this.idHadiah = idHadiah;
    }

    public String getNamaHadiah() {
        return namaHadiah;
    }

    public void setNamaHadiah(String namaHadiah) {
        this.namaHadiah = namaHadiah;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getDeskripsiHadiah() {
        return deskripsiHadiah;
    }

    public void setDeskripsiHadiah(String deskripsiHadiah) {
        this.deskripsiHadiah = deskripsiHadiah;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Date getPeriodeTukar() {
        return periodeTukar;
    }

    public void setPeriodeTukar(Date periodeTukar) {
        this.periodeTukar = periodeTukar;
    }
}
