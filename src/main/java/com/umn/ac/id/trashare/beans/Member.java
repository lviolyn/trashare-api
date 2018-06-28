package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "password", "salt"})
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_member")
    private int idMember;

    @Column(name = "nama_lengkap", length = 50)
    private String namaLengkap;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "no_telp", length = 15)
    private String noTelp;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "salt", length = 10)
    private String salt;

    @Column(name = "poin")
    private int poin;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "saldo")
    private int saldo;

    @Column(name = "foto_profil")
    private String fotoProfil;

    @Column(name = "foto_identitas")
    private String fotoIdentitas;

    // Banyak BS cuma bisa ke 1 member (1 member terdaftar di 1 BS)
    @ManyToOne(targetEntity = BankSampah.class)
    @JoinColumn(name = "id_bank_sampah", nullable = false, referencedColumnName = "id_bank_sampah")
    private BankSampah idBankSampah;

    // Constructor
    public Member() {
    }

    public Member(String namaLengkap, String email, String noTelp, String alamat, String password, String salt, int poin, String sessionToken, int saldo, String fotoProfil, String fotoIdentitas, BankSampah idBankSampah) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.password = password;
        this.salt = salt;
        this.poin = poin;
        this.sessionToken = sessionToken;
        this.saldo = saldo;
        this.fotoProfil = fotoProfil;
        this.fotoIdentitas = fotoIdentitas;
        this.idBankSampah = idBankSampah;
    }

    // Setter
    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public void setFotoIdentitas(String fotoIdentitas) {
        this.fotoIdentitas = fotoIdentitas;
    }

    public void setIdBankSampah(BankSampah idBankSampah) {
        this.idBankSampah = idBankSampah;
    }

    // Getter
    public int getIdMember() {
        return idMember;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public int getPoin() {
        return poin;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public String getFotoIdentitas() {
        return fotoIdentitas;
    }

    public BankSampah getIdBankSampah() {
        return idBankSampah;
    }
}
