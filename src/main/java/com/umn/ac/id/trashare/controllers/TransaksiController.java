package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.Hadiah;
import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.beans.Sampah;
import com.umn.ac.id.trashare.beans.Transaksi;
import com.umn.ac.id.trashare.repositories.HadiahRepository;
import com.umn.ac.id.trashare.repositories.MemberRepository;
import com.umn.ac.id.trashare.repositories.SampahRepository;
import com.umn.ac.id.trashare.repositories.TransaksiRepository;
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
public class TransaksiController {
    private final HadiahRepository hadiahRepository;
    private final MemberRepository memberRepository;
    private final TransaksiRepository transaksiRepository;

    @Autowired
    public TransaksiController(HadiahRepository hadiahRepository, MemberRepository memberRepository, TransaksiRepository transaksiRepository) {
        this.hadiahRepository = hadiahRepository;
        this.memberRepository = memberRepository;
        this.transaksiRepository = transaksiRepository;
    }

    @PostMapping("/transaksi")
    public Transaksi createTransaksi(@RequestBody Map<String, String> body){
        Integer idMember = Integer.parseInt(body.get("idMember"));
        Member member = memberRepository.getOne(idMember);
        Integer idHadiah = Integer.parseInt(body.get("idHadiah"));
        Hadiah hadiah = hadiahRepository.getOne(idHadiah);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date tanggalPenukaran = new Date();
        try {
            tanggalPenukaran = dateFormat.parse(body.get("tanggalPenukaran"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        member.setPoin(member.getPoin() - hadiah.getPoin());
        memberRepository.save(member);
        return transaksiRepository.save(new Transaksi(member, hadiah, tanggalPenukaran));
    }
}
