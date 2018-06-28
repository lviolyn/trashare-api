package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "setor_sampah")
public class SetorSampah {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_setor_sampah")
    private int idSetorSampah;

    @Column(name = "berat")
    private int berat;

    @Column(name = "tanggal_setor")
    private Date tanggalSetor;
}
