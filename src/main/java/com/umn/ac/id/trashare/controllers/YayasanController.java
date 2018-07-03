package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Yayasan;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.YayasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class YayasanController {
    @Autowired
    YayasanRepository yayasanRepository;

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
    public Yayasan createYayasan(@RequestBody Map<String, String> body){
        String namaYayasan = body.get("namaYayasan");
        String email = body.get("email");
        String noTelp = body.get("noTelp");
        String password = body.get("password");
        String salt = body.get("salt");
        String sessionToken = body.get("sessionToken");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yayasanRepository.save(new Yayasan(namaYayasan, email, noTelp, fotoProfil, password, salt, sessionToken));
    }

    @PutMapping("/yayasan/{id}")
    public Yayasan updateYayasan(@PathVariable String id, @RequestBody Map<String, String> body){
        int idYayasan = Integer.parseInt(id);
        Yayasan yayasan = yayasanRepository.getOne(idYayasan);
        yayasan.setNamaYayasan(body.get("namaYayasan"));
        yayasan.setEmail(body.get("email"));
        yayasan.setNoTelp(body.get("noTelp"));
        yayasan.setPassword(body.get("password"));
        yayasan.setSalt(body.get("salt"));
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        yayasan.setFotoProfil(fotoProfil);
        return yayasanRepository.save(yayasan);
    }

    @DeleteMapping("/yayasan/{id}")
    public boolean deleteYayasan(@PathVariable String id){
        int idYayasan = Integer.parseInt(id);
        Yayasan yayasan = yayasanRepository.getOne(idYayasan);
        yayasanRepository.delete(yayasan);
        return true;
    }
}
