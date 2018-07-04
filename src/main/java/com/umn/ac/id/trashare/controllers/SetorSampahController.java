package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.beans.Sampah;
import com.umn.ac.id.trashare.beans.SetorSampah;
import com.umn.ac.id.trashare.repositories.MemberRepository;
import com.umn.ac.id.trashare.repositories.SampahRepository;
import com.umn.ac.id.trashare.repositories.SetorSampahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class SetorSampahController {
    private final MemberRepository memberRepository;
    private final SampahRepository sampahRepository;
    private final SetorSampahRepository setorSampahRepository;

    @Autowired
    public SetorSampahController(MemberRepository memberRepository, SampahRepository sampahRepository, SetorSampahRepository setorSampahRepository) {
        this.memberRepository = memberRepository;
        this.sampahRepository = sampahRepository;
        this.setorSampahRepository = setorSampahRepository;
    }

    @PostMapping("/setor-sampah")
    public SetorSampah createSetorSampah(@RequestBody Map<String, String> body){
        Integer idSampah = Integer.parseInt(body.get("idSampah"));
        Sampah sampah = sampahRepository.getOne(idSampah);
        Integer idMember = Integer.parseInt(body.get("idMember"));
        Member member = memberRepository.getOne(idMember);
        Integer berat = Integer.parseInt(body.get("berat"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date tanggalSetor = new Date();
        try {
            tanggalSetor = dateFormat.parse(body.get("tanggalSetor"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        member.setPoin(berat * 10);
        memberRepository.save(member);
        return setorSampahRepository.save(new SetorSampah(sampah, member, berat, tanggalSetor));
    }
}
