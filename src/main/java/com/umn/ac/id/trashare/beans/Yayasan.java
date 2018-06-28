package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "password", "salt"})
@Table(name="yayasan")
public class Yayasan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_yayasan")
    private int idYayasan;

    @Column(name = "nama_yayasan", length = 50)
    private String namaYayasan;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "no_telp", length = 15)
    private String noTelp;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "salt", length = 10)
    private String salt;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "foto_profil")
    private String fotoProfil;

    // Banyak BS dimiliki 1 yayasan. 1 yayasan punya banyak BS
    @ManyToOne(targetEntity = BankSampah.class)
    @JoinColumn(name = "id_bank_sampah", nullable = false, referencedColumnName = "id_bank_sampah")
    private BankSampah idBankSampah;

    // Constructor
    public Yayasan() {
    }

    public Yayasan(String namaYayasan, String email, String noTelp, String urlFotoProfil, String password, String salt, String sessionToken, BankSampah idBankSampah) {
        this.namaYayasan = namaYayasan;
        this.email = email;
        this.noTelp = noTelp;
        this.fotoProfil = urlFotoProfil;
        this.password = password;
        this.salt = salt;
        this.sessionToken = sessionToken;
        this.idBankSampah = idBankSampah;
    }

   // Setter

    public void setIdYayasan(int idYayasan) {
        this.idYayasan = idYayasan;
    }

    public void setNamaYayasan(String namaYayasan) {
        this.namaYayasan = namaYayasan;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public void setIdBankSampah(BankSampah idBankSampah) {
        this.idBankSampah = idBankSampah;
    }

    // Getter

    public int getIdYayasan() {
        return idYayasan;
    }

    public String getNamaYayasan() {
        return namaYayasan;
    }

    public String getEmail() {
        return email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public BankSampah getIdBankSampah() {
        return idBankSampah;
    }
}

