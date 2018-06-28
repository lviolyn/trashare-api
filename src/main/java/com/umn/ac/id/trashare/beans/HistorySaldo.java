package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "history_saldo")
public class HistorySaldo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_history_saldo")
    private int idHistorySaldo;

    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "tanggal_transaksi")
    private Date tanggalTransaksi;

    @Column(name = "debit")
    private int debit;

    @Column(name = "kredit")
    private int kredit;
}
