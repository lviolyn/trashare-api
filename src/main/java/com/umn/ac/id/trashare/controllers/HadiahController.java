package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.Hadiah;
import com.umn.ac.id.trashare.repositories.HadiahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class HadiahController {
    @Autowired
    HadiahRepository hadiahRepository;

    @GetMapping("/hadiah")
    public List<Hadiah> getAllHadiah(){
        return hadiahRepository.findAll();
    }

    @GetMapping("/hadiah/{id}")
    public Hadiah getOneHadiah(@PathVariable String id){
        int idHadiah = Integer.parseInt(id);
        return hadiahRepository.getOne(idHadiah);
    }

    @PostMapping("/hadiah")
    public Hadiah createHadiah(@RequestBody Map<String, String> body){
        String namaHadiah = body.get("namaHadiah");
        int poin = Integer.parseInt(body.get("poin"));
        String deskripsiHadiah = body.get("deskripsiHadiah");
        String sponsor = body.get("sponsor");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date periodeTukar = new Date();
        try {
            periodeTukar = dateFormat.parse(body.get("periodeTukar"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoHadiah = null;
        try {
            fotoHadiah = decoder.decodeBuffer(body.get("fotoHadiah"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hadiahRepository.save(new Hadiah(namaHadiah, poin, deskripsiHadiah, sponsor, periodeTukar, fotoHadiah));
    }

    @PutMapping("/hadiah/{id}")
    public Hadiah updateHadiah(@PathVariable String id, @RequestBody Map<String, String> body){
        int idHadiah = Integer.parseInt(id);
        Hadiah hadiah = hadiahRepository.getOne(idHadiah);
        if(body.get("namaHadiah") != null && !body.get("namaHadiah").equals("")) {
            hadiah.setNamaHadiah(body.get("namaHadiah"));
        }
        if(body.get("poin") != null && !body.get("poin").equals("")) {
            hadiah.setPoin(Integer.parseInt(body.get("poin")));
        }
        if(body.get("deskripsiHadiah") != null && !body.get("deskripsiHadiah").equals("")) {
            hadiah.setDeskripsiHadiah(body.get("deskripsiHadiah"));
        }
        if(body.get("sponsor") != null && !body.get("sponsor").equals("")) {
            hadiah.setSponsor(body.get("sponsor"));
        }
        if(body.get("periodeTukar") != null && !body.get("periodeTukar").equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
            DateFormat dateFormat = simpleDateFormat;
            Date periodeTukar = new Date();
            try {
                periodeTukar = dateFormat.parse(body.get("periodeTukar"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hadiah.setPeriodeTukar(periodeTukar);
        }
        if(body.get("fotoHadiah") != null && !body.get("fotoHadiah").equals("")) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] fotoHadiah = null;
            try {
                fotoHadiah = decoder.decodeBuffer(body.get("fotoHadiah"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            hadiah.setFotoHadiah(fotoHadiah);
        }
        return hadiahRepository.save(hadiah);
    }

    @DeleteMapping("/hadiah/{id}")
    public boolean deleteHadiah(@PathVariable String id){
        int idHadiah = Integer.parseInt(id);
        Hadiah hadiah = hadiahRepository.getOne(idHadiah);
        hadiahRepository.delete(hadiah);
        return true;
    }
}
