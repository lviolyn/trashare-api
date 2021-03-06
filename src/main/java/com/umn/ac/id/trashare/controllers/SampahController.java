package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.Sampah;
import com.umn.ac.id.trashare.repositories.SampahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SampahController {
    @Autowired
    SampahRepository sampahRepository;

    @GetMapping("/sampah")
    public List<Sampah> getAllSampah(){
        return sampahRepository.findAll();
    }

    @GetMapping("/sampah/{id}")
    public Sampah getOneSampah(@PathVariable String id){
        int idSampah = Integer.parseInt(id);
        return sampahRepository.getOne(idSampah);
    }

    @PostMapping("/sampah")
    public Sampah createSampah(@RequestBody Map<String, String> body){
        int hargaBeliLapak = Integer.parseInt(body.get("hargaBeliLapak"));
        int hargaBeliNasabah = Integer.parseInt(body.get("hargaBeliNasabah"));
        String namaSampah = body.get("namaSampah");
        String tipeSampah = body.get("tipeSampah");
        return sampahRepository.save(new Sampah(hargaBeliLapak, hargaBeliNasabah, namaSampah, tipeSampah));
    }

    @PutMapping("/sampah/{id}")
    public Sampah updateSampah(@PathVariable String id, @RequestBody Map<String, String> body){
        int idSampah = Integer.parseInt(id);
        Sampah sampah = sampahRepository.getOne(idSampah);
        if(body.get("hargaBeliLapak") != null && !body.get("hargaBeliLapak").equals("")){
            sampah.setHargaBeliLapak(Integer.parseInt(body.get("hargaBeliLapak")));
        }
        if(body.get("hargaBeliNasabah") != null && !body.get("hargaBeliNasabah").equals("")){
            sampah.setHargaBeliNasabah(Integer.parseInt(body.get("hargaBeliNasabah")));
        }
        if(body.get("namaSampah") != null && !body.get("namaSampah").equals("")){
            sampah.setNamaSampah(body.get("namaSampah"));
        }
        if(body.get("tipeSampah") != null && !body.get("tipeSampah").equals("")) {
            sampah.setTipeSampah(body.get("tipeSampah"));
        }
        return sampahRepository.save(sampah);
    }

    @DeleteMapping("/sampah/{id}")
    public boolean deleteSampah(@PathVariable String id){
        int idSampah = Integer.parseInt(id);
        Sampah sampah = sampahRepository.getOne(idSampah);
        sampahRepository.delete(sampah);
        return true;
    }
}
