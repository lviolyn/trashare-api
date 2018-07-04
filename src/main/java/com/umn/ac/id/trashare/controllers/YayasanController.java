package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.Utils.StringUtils;
import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Yayasan;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.YayasanRepository;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class YayasanController {

    YayasanRepository yayasanRepository;
    BankSampahRepository bankSampahRepository;

    @Autowired
    public YayasanController(YayasanRepository yayasanRepository, BankSampahRepository bankSampahRepository) {
        this.yayasanRepository = yayasanRepository;
        this.bankSampahRepository = bankSampahRepository;
    }

    @GetMapping("/yayasan")
    public List<Yayasan> getAllYayasan(){
        return yayasanRepository.findAll();
    }

    @GetMapping("/yayasan/{id}")
    public Yayasan getOneYayasan(@PathVariable String id){
        int id_yayasan = Integer.parseInt(id);
        return yayasanRepository.getOne(id_yayasan);
    }

    @PostMapping("/yayasan")
    public Yayasan createYayasan(@ApiParam("description") @RequestBody Map<String, String> body){
        String namaYayasan = body.get("namaYayasan");
        String email = body.get("email");
        String noTelp = body.get("noTelp");
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
        return yayasanRepository.save(new Yayasan(namaYayasan, email, noTelp, fotoProfil, newPassword, salt, sessionToken));
    }

    @PutMapping("/yayasan/{id}")
    public Yayasan updateYayasan(@PathVariable String id, @RequestBody Map<String, String> body){
        int idYayasan = Integer.parseInt(id);
        Yayasan yayasan = yayasanRepository.getOne(idYayasan);
        if(body.get("namaYayasan") != null && !body.get("namaYayasan").equals("")) {
            yayasan.setNamaYayasan(body.get("namaYayasan"));
        }
        if(body.get("email") != null && !body.get("email").equals("")) {
            yayasan.setEmail(body.get("email"));
        }
        if(body.get("noTelp") != null && !body.get("noTelp").equals("")) {
            yayasan.setNoTelp(body.get("noTelp"));
        }
        if(body.get("password") != null && !body.get("password").equals("")) {
            String newSalt = BCrypt.gensalt();
            yayasan.setSalt(newSalt);
            String newPassword = body.get("password");
            yayasan.setPassword(BCrypt.hashpw(newPassword, newSalt));
        }
        if(body.get("fotoProfil") != null && !body.get("fotoProfil").equals("")) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] fotoProfil = null;
            try {
                fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            yayasan.setFotoProfil(fotoProfil);
        }
        return yayasanRepository.save(yayasan);
    }

    @DeleteMapping("/yayasan/{id}")
    public boolean deleteYayasan(@PathVariable String id){
        int idYayasan = Integer.parseInt(id);
        Yayasan yayasan = yayasanRepository.getOne(idYayasan);
        yayasanRepository.delete(yayasan);
        return true;
    }

    @PostMapping("/yayasan/login")
    public Yayasan loginYayasan(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Yayasan yayasan = yayasanRepository.findOneByEmail(email);
        if (yayasan != null) {
            if (BCrypt.checkpw(password, yayasan.getPassword())) {
                yayasan.setSessionToken(StringUtils.randomAlphaNumeric(128));
                yayasanRepository.save(yayasan);
                return yayasan;
            }
        }
        return null;
    }

    @GetMapping("/yayasan/{id}/bank-sampah")
    public Yayasan getYayasanByBankSampah(@PathVariable String id) {
        int bankSampahId = Integer.parseInt(id);
        BankSampah bankSampah = bankSampahRepository.getOne(bankSampahId);
        return yayasanRepository.findOneByBankSampahs(bankSampah);
    }
}
