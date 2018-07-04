package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.HistorySaldo;
import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.repositories.HistorySaldoRepository;
import com.umn.ac.id.trashare.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class HistorySaldoController {
    private final MemberRepository memberRepository;
    private final HistorySaldoRepository historySaldoRepository;

    @Autowired
    public HistorySaldoController(MemberRepository memberRepository, HistorySaldoRepository historySaldoRepository) {
        this.memberRepository = memberRepository;
        this.historySaldoRepository = historySaldoRepository;
    }

    @PostMapping("/history-saldo")
    public HistorySaldo createSetorSampah(@RequestBody Map<String, String> body) {
        Integer idMember = Integer.parseInt(body.get("idMember"));
        Member member = memberRepository.getOne(idMember);
        Integer jumlah = Integer.parseInt(body.get("jumlah"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7:00"));
        DateFormat dateFormat = simpleDateFormat;
        Date tanggalTransaksi = new Date();
        try {
            tanggalTransaksi = dateFormat.parse(body.get("tanggalTransaksi"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        member.setSaldo(member.getSaldo() - jumlah);
        memberRepository.save(member);
        return historySaldoRepository.save(new HistorySaldo(member, jumlah, tanggalTransaksi));
    }
}
