package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BankSampahController {
    @Autowired
    BankSampahRepository bankSampahRepository;

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
        String salt = body.get("salt");
        String sessionToken = body.get("sessionToken");
        String fotoProfil = body.get("fotoProfil");
        return bankSampahRepository.save(new BankSampah(namaBankSampah, namaKetua, alamat, wilayah, noTelp, email, deskripsiBankSampah, password, salt, sessionToken, fotoProfil));
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
        bankSampah.setPassword(body.get("password"));
        bankSampah.setSalt(body.get("salt"));
        bankSampah.setFotoProfil(body.get("fotoProfil"));
        return bankSampahRepository.save(bankSampah);
    }

    @DeleteMapping("/bank-sampah/{id}")
    public boolean deleteBankSampah(@PathVariable String id){
        int idBankSampah = Integer.parseInt(id);
        BankSampah bankSampah = bankSampahRepository.getOne(idBankSampah);
        bankSampahRepository.delete(bankSampah);
        return true;
    }
}
