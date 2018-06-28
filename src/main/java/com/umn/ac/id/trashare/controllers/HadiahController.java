package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.Hadiah;
import com.umn.ac.id.trashare.repositories.HadiahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Date periodeTukar = new Date(body.get("periodeTukar"));
        return hadiahRepository.save(new Hadiah(namaHadiah, poin, deskripsiHadiah, sponsor, periodeTukar));
    }

    @PutMapping("/hadiah/{id}")
    public Hadiah updateHadiah(@PathVariable String id, @RequestBody Map<String, String> body){
        int idHadiah = Integer.parseInt(id);
        Hadiah hadiah = hadiahRepository.getOne(idHadiah);
        hadiah.setNamaHadiah(body.get("namaHadiah"));
        hadiah.setPoin(Integer.parseInt(body.get("poin")));
        hadiah.setDeskripsiHadiah(body.get("deskripsiHadiah"));
        hadiah.setSponsor(body.get("sponsor"));
        hadiah.setPeriodeTukar(new Date(body.get("periodeTukar")));
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
