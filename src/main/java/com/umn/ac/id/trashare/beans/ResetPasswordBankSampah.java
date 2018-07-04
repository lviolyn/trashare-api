package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "reset_password_bank_sampah")
public class ResetPasswordBankSampah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reset_password_bank_sampah")
    private int idResetPasswordBankSampah;

    @OneToOne
    @JoinColumn(name = "id_bank_sampah")
    private BankSampah idBankSampah;

    @Column(name = "token")
    private String token;

    @Column(name = "date_created")
    private Date dateCreated;

    public ResetPasswordBankSampah(BankSampah idBankSampah, String token, Date dateCreated) {
        this.idBankSampah = idBankSampah;
        this.token = token;
        this.dateCreated = dateCreated;
    }

    public ResetPasswordBankSampah() {
    }

    public int getIdResetPasswordBankSampah() {
        return idResetPasswordBankSampah;
    }

    public void setIdResetPasswordBankSampah(int idResetPasswordBankSampah) {
        this.idResetPasswordBankSampah = idResetPasswordBankSampah;
    }

    public BankSampah getIdBankSampah() {
        return idBankSampah;
    }

    public void setIdBankSampah(BankSampah idBankSampah) {
        this.idBankSampah = idBankSampah;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
