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

    @Column(name = "email", length = 60, unique = true)
    private String email;

    @Column(name = "deskripsi_bank_sampah")
    private String deskripsiBankSampah;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "session_token", unique = true)
    private String sessionToken;

    @Column(name = "foto_profil")
    private byte[] fotoProfil;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "push_token")
    private String pushToken;

    // Banyak (Yayasan) ke 1 BS --> Banyak BS dimiliki 1 Yayasan
    @ManyToOne(targetEntity = Yayasan.class)
    @JoinColumn(name = "id_yayasan", nullable = false, referencedColumnName = "id_yayasan")
    private Yayasan idYayasan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBankSampah")
    private Set<Member> members = new HashSet<Member>(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBankSampah")
    private Set<Kegiatan> kegiatans = new HashSet<Kegiatan>(0);

    // Constructor
    public BankSampah() {
    }

    public BankSampah(String namaBankSampah, String namaKetua, String alamat, String wilayah, String noTelp, String email, String deskripsiBankSampah, String password, String salt, String sessionToken, byte[] fotoProfil, Yayasan idYayasan, double latitude, double longitude, String pushToken) {
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
        this.idYayasan = idYayasan;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pushToken = pushToken;
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

    public void setFotoProfil(byte[] fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public void setIdYayasan(Yayasan idYayasan) {
        this.idYayasan = idYayasan;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void setKegiatans(Set<Kegiatan> kegiatans) {
        this.kegiatans = kegiatans;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
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

    public byte[] getFotoProfil() {
        return fotoProfil;
    }

    // Bank sampah gak bisa tau Yayasan dia apa
    @JsonIgnore
    public Yayasan getIdYayasan() {
        return idYayasan;
    }

    // Bank Sampah tau dia punya member siapa aja
    public Set<Member> getMembers() {
        return members;
    }

    // Bank Sampah tau dia punya event apa aja
    public Set<Kegiatan> getKegiatans() {
        return kegiatans;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPushToken() {
        return pushToken;
    }
}
