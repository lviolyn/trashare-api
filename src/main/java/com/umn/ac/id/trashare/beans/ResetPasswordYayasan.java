package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "reset_password_yayasan")
public class ResetPasswordYayasan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reset_password_bank_sampah")
    private int idResetPasswordYayasan;

    @OneToOne
    @JoinColumn(name = "id_bank_sampah")
    private Yayasan idYayasan;

    @Column(name = "token")
    private String token;

    @Column(name = "date_created")
    private Date dateCreated;

    public ResetPasswordYayasan(Yayasan idYayasan, String token, Date dateCreated) {
        this.idYayasan = idYayasan;
        this.token = token;
        this.dateCreated = dateCreated;
    }

    public ResetPasswordYayasan() {
    }

    public int getIdResetPasswordYayasan() {
        return idResetPasswordYayasan;
    }

    public void setIdResetPasswordYayasan(int idResetPasswordYayasan) {
        this.idResetPasswordYayasan = idResetPasswordYayasan;
    }

    public Yayasan getIdYayasan() {
        return idYayasan;
    }

    public void setIdYayasan(Yayasan idYayasan) {
        this.idYayasan = idYayasan;
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
