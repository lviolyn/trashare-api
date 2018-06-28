package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaksi")
    private int idTransaksi;

    @Column(name = "tanggal_transaksi")
    private Date tanggalTransaksi;
}
