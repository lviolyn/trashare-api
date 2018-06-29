package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","password","salt"})
@Table(name = "bank_sampah")
public class BankSampah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank_sampah")
    private int idBankSampah;

    @Column(name = "nama_bank_sampah", length = 50)
    private String namaBankSampah;

    @Column(name = "nama_ketua", length = 50)
    private String namaKetua;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "wilayah", length = 30)
    private String wilayah;

    @Column(name = "no_telp", length = 15)
    private String noTelp;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "deskripsi_bank_sampah")
    private String deskripsiBankSampah;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "salt", length = 10)
    private String salt;

    @Column(name = "session_token", length = 35)
    private String sessionToken;

    @Column(name = "foto_profil")
    private String fotoProfil;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBankSampah")
    private Set<Member> members = new HashSet<Member>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBankSampah")
    private Set<Kegiatan> kegiatans = new HashSet<Kegiatan>(0);

    // Constructor
    public BankSampah() {
    }

    public BankSampah(String namaBankSampah, String namaKetua, String alamat, String wilayah, String noTelp, String email, String deskripsiBankSampah, String password, String salt, String sessionToken, String fotoProfil) {
        this.namaBankSampah = namaBankSampah;
        this.namaKetua = namaKetua;
        this.alamat = alamat;
        this.wilayah = wilayah;
        this.noTelp = noTelp;
        this.email = email;
        this.deskripsiBankSampah = deskripsiBankSampah;
        this.password = password;
        this.salt = salt;
        this.sessionToken = sessionToken;
        this.fotoProfil = fotoProfil;
    }

    // Setter
    public void setIdBankSampah(int idBankSampah) {
        this.idBankSampah = idBankSampah;
    }

    public void setNamaBankSampah(String namaBankSampah) {
        this.namaBankSampah = namaBankSampah;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeskripsiBankSampah(String deskripsiBankSampah) {
        this.deskripsiBankSampah = deskripsiBankSampah;
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

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void setKegiatans(Set<Kegiatan> kegiatans) {
        this.kegiatans = kegiatans;
    }

    // Getter
    public int getIdBankSampah() {
        return idBankSampah;
    }

    public String getNamaBankSampah() {
        return namaBankSampah;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getWilayah() {
        return wilayah;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getEmail() {
        return email;
    }

    public String getDeskripsiBankSampah() {
        return deskripsiBankSampah;
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

    // Bank Sampah tau dia punya member siapa aja
    public Set<Member> getMembers() {
        return members;
    }

    // Bank Sampah tau dia punya event apa aja
    public Set<Kegiatan> getKegiatans() {
        return kegiatans;
    }
}
