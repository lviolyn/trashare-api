package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Kegiatan;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.KegiatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class KegiatanController {
    private final KegiatanRepository kegiatanRepository;
    private final BankSampahRepository bankSampahRepository;

    @Autowired
    public KegiatanController(KegiatanRepository kegiatanRepository, BankSampahRepository bankSampahRepository) {
        this.kegiatanRepository = kegiatanRepository;
        this.bankSampahRepository = bankSampahRepository;
    }


    @GetMapping("/kegiatan")
    public List<Kegiatan> getAllKegiatan(){
        return kegiatanRepository.findAll();
    }

    @GetMapping("/kegiatan/{id}")
    public Kegiatan getOneKegiatan(@PathVariable String id){
        int idKegiatan = Integer.parseInt(id);
        return kegiatanRepository.getOne(idKegiatan);
    }

    @PostMapping("/kegiatan")
    public Kegiatan createKegiatan(@RequestBody Map<String, String> body){
        String namaKegiatan = body.get("namaKegiatan");
        String deskripsiKegiatan = body.get("deskripsiKegiatan");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date tanggalKegiatan = new Date();
        try {
            tanggalKegiatan = dateFormat.parse(body.get("tanggalKegiatan"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int idBankSampah = Integer.parseInt(body.get("idBankSampah"));
        BankSampah bs = bankSampahRepository.getOne(idBankSampah);
        return kegiatanRepository.save(new Kegiatan(namaKegiatan, deskripsiKegiatan, tanggalKegiatan, bs));
    }

    @PutMapping("/kegiatan/{id}")
    public Kegiatan updateKegiatan(@PathVariable String id, @RequestBody Map<String, String> body){
        int idKegiatan = Integer.parseInt(id);
        Kegiatan kegiatan = kegiatanRepository.getOne(idKegiatan);
        kegiatan.setNamaKegiatan(body.get("namaKegiatan"));
        kegiatan.setDeskripsiKegiatan(body.get("deskripsiKegiatan"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date tanggalKegiatan = new Date();
        try {
            tanggalKegiatan = dateFormat.parse(body.get("tanggalKegiatan"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        kegiatan.setTanggalKegiatan(tanggalKegiatan);
        int idBankSampah = Integer.parseInt(body.get("idBankSampah"));
        BankSampah bs = bankSampahRepository.getOne(idBankSampah);
        kegiatan.setIdBankSampah(bs);
        return kegiatanRepository.save(kegiatan);
    }

    @DeleteMapping("/kegiatan/{id}")
    public boolean deleteKegiatan(@PathVariable String id){
        int idKegiatan = Integer.parseInt(id);
        Kegiatan kegiatan = kegiatanRepository.getOne(idKegiatan);
        kegiatanRepository.delete(kegiatan);
        return true;
    }
}
