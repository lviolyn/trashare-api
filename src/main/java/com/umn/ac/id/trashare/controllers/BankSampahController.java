package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Yayasan;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.YayasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class BankSampahController {

    private final BankSampahRepository bankSampahRepository;
    private final YayasanRepository yayasanRepository;

    @Autowired
    public BankSampahController(BankSampahRepository bankSampahRepository, YayasanRepository yayasanRepository){
        this.bankSampahRepository = bankSampahRepository;
        this.yayasanRepository = yayasanRepository;
    }

    @GetMapping("/bank-sampah")
    public List<BankSampah> getAllBankSampah(){
        return bankSampahRepository.findAll();
    }

    @GetMapping("/bank-sampah/{id}")
    public BankSampah getOneBankSampah(@PathVariable String id){
        int idBankSampah = Integer.parseInt(id);
        return bankSampahRepository.getOne(idBankSampah);
    }

    @PostMapping("/bank-sampah")
    public BankSampah createBankSampah(@RequestBody Map<String, String> body){
        String namaBankSampah = body.get("namaBankSampah");
        String namaKetua = body.get("namaKetua");
        String alamat = body.get("alamat");
        String wilayah = body.get("wilayah");
        String noTelp = body.get("noTelp");
        String email = body.get("email");
        String deskripsiBankSampah = body.get("deskripsiBankSampah");
        String password = body.get("password");
        String salt = BCrypt.gensalt();
        String newPassword = BCrypt.hashpw(password, salt);
        String sessionToken = body.get("sessionToken");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int idYayasan = Integer.parseInt(body.get("idYayasan"));
        Yayasan ys = yayasanRepository.getOne(idYayasan);
        return bankSampahRepository.save(new BankSampah(namaBankSampah, namaKetua, alamat, wilayah, noTelp, email, deskripsiBankSampah, newPassword, salt, sessionToken, fotoProfil, ys));
    }

    @PutMapping("/bank-sampah/{id}")
    public BankSampah updateBankSampah(@PathVariable String id, @RequestBody Map<String, String> body){
        int idBankSampah = Integer.parseInt(id);
        BankSampah bankSampah = bankSampahRepository.getOne(idBankSampah);
        bankSampah.setNamaBankSampah(body.get("namaBankSampah"));
        bankSampah.setNamaKetua(body.get("namaKetua"));
        bankSampah.setAlamat(body.get("alamat"));
        bankSampah.setWilayah(body.get("wilayah"));
        bankSampah.setNoTelp(body.get("noTelp"));
        bankSampah.setEmail(body.get("email"));
        bankSampah.setDeskripsiBankSampah(body.get("deskripsiBankSampah"));
        String newSalt = BCrypt.gensalt();
        bankSampah.setSalt(newSalt);
        String newPassword = body.get("password");
        bankSampah.setPassword(BCrypt.hashpw(newPassword, newSalt));
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bankSampah.setFotoProfil(fotoProfil);
        int idYayasan = Integer.parseInt(body.get("idYayasan"));
        Yayasan ys = yayasanRepository.getOne(idYayasan);
        bankSampah.setIdYayasan(ys);
        return bankSampahRepository.save(bankSampah);
    }

    @DeleteMapping("/bank-sampah/{id}")
    public boolean deleteBankSampah(@PathVariable String id){
        int idBankSampah = Integer.parseInt(id);
        BankSampah bankSampah = bankSampahRepository.getOne(idBankSampah);
        bankSampahRepository.delete(bankSampah);
        return true;
    }

    @PostMapping("/bank-sampah/login")
    public BankSampah loginBankSampah(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        BankSampah bankSampah = bankSampahRepository.findByEmail(email);
        if (bankSampah != null) {
            if (BCrypt.checkpw(password, bankSampah.getPassword())) {
                return bankSampah;
            }
        }
        return null;
    }
}
