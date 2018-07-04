package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.Utils.StringUtils;
import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.beans.Yayasan;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Autowired
    public BankSampahController(BankSampahRepository bankSampahRepository, YayasanRepository yayasanRepository, MemberRepository memberRepository){
        this.bankSampahRepository = bankSampahRepository;
        this.yayasanRepository = yayasanRepository;
        this.memberRepository = memberRepository;
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
        String sessionToken = "";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int idYayasan = Integer.parseInt(body.get("idYayasan"));
        Yayasan ys = yayasanRepository.getOne(idYayasan);
        double latitude = Double.parseDouble(body.get("latitude"));
        double longitude = Double.parseDouble(body.get("longitude"));
        return bankSampahRepository.save(new BankSampah(namaBankSampah, namaKetua, alamat, wilayah, noTelp, email, deskripsiBankSampah, newPassword, salt, sessionToken, fotoProfil, ys, latitude, longitude));
    }

    @PutMapping("/bank-sampah/{id}")
    public BankSampah updateBankSampah(@PathVariable String id, @RequestBody Map<String, String> body){
        int idBankSampah = Integer.parseInt(id);
        BankSampah bankSampah = bankSampahRepository.getOne(idBankSampah);
        if(body.get("namaBankSampah") != null && !body.get("namaBankSampah").equals("")) {
            bankSampah.setNamaBankSampah(body.get("namaBankSampah"));
        }
        if(body.get("namaKetua") != null && !body.get("namaKetua").equals("")) {
            bankSampah.setNamaKetua(body.get("namaKetua"));
        }
        if(body.get("alamat") != null && !body.get("alamat").equals("")) {
            bankSampah.setAlamat(body.get("alamat"));
        }
        if(body.get("wilayah") != null && !body.get("wilayah").equals("")) {
            bankSampah.setWilayah(body.get("wilayah"));
        }
        if(body.get("noTelp") != null && !body.get("noTelp").equals("")) {
            bankSampah.setNoTelp(body.get("noTelp"));
        }
        if(body.get("email") != null && !body.get("email").equals("")) {
            bankSampah.setEmail(body.get("email"));
        }
        if(body.get("deskripsiBankSampah") != null && !body.get("deskripsiBankSampah").equals("")) {
            bankSampah.setDeskripsiBankSampah(body.get("deskripsiBankSampah"));
        }
        if(body.get("password") != null && !body.get("password").equals("")) {
            String newSalt = BCrypt.gensalt();
            bankSampah.setSalt(newSalt);
            String newPassword = body.get("password");
            bankSampah.setPassword(BCrypt.hashpw(newPassword, newSalt));
        }
        if(body.get("fotoProfil") != null && !body.get("fotoProfil").equals("")) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] fotoProfil = null;
            try {
                fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bankSampah.setFotoProfil(fotoProfil);
        }
        if(body.get("idYayasan") != null && !body.get("idYayasan").equals("")) {
            int idYayasan = Integer.parseInt(body.get("idYayasan"));
            Yayasan ys = yayasanRepository.getOne(idYayasan);
            bankSampah.setIdYayasan(ys);
        }
        if(body.get("latitude") != null && !body.get("latitude").equals("")) {
            bankSampah.setLatitude(Double.parseDouble(body.get("latitude")));
        }
        if(body.get("longitude") != null && !body.get("longitude").equals("")) {
            bankSampah.setLatitude(Double.parseDouble(body.get("longitude")));
        }
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
        BankSampah bankSampah = bankSampahRepository.findOneByEmail(email);
        if (bankSampah != null) {
            if (BCrypt.checkpw(password, bankSampah.getPassword())) {
                bankSampah.setSessionToken(StringUtils.randomAlphaNumeric(128));
                bankSampahRepository.save(bankSampah);
                return bankSampah;
            }
        }
        return null;
    }

    @GetMapping("/bank-sampah/{id}/member")
    public BankSampah getBankSampahbyMember(@PathVariable String id){
        int idMember = Integer.parseInt(id);
        Member member = memberRepository.getOne(idMember);
        return bankSampahRepository.findOneByMembers(member);
    }
}
