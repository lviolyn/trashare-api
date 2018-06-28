package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;
    private final BankSampahRepository bankSampahRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository, BankSampahRepository bankSampahRepository) {
        this.memberRepository = memberRepository;
        this.bankSampahRepository = bankSampahRepository;
    }

    @GetMapping("/member")
    public List<Member> getAllMember(){
        return memberRepository.findAll();
    }

    @GetMapping("/member/{id}")
    public Member getOneMember(@PathVariable String id){
        int idMember = Integer.parseInt(id);
        return memberRepository.getOne(idMember);
    }


//    @GetMapping("/member/{id}/bank-sampah")
//    public BankSampah getIdBankSampah(@PathVariable String id){
//        int idMember = Integer.parseInt(id);
//        Member member = memberRepository.getOne(idMember);
//        return bankSampahRepository.findBankSampahById(member);
//    }

    @PostMapping("/member")
    public Member createMember(@RequestBody Map<String, String> body){
        String namaLengkap = body.get("namaLengkap");
        String email = body.get("email");
        String noTelp = body.get("noTelp");
        String alamat = body.get("alamat");
        String password = body.get("password");
        String salt = body.get("salt");
        int poin = Integer.parseInt(body.get("poin"));
        String sessionToken = body.get("sessionToken");
        int saldo = Integer.parseInt(body.get("saldo"));
        String fotoProfil = body.get("fotoProfil");
        String fotoIdentitas = body.get("fotoIdentitas");
        int idBankSampah = Integer.parseInt(body.get("idBankSampah"));
        BankSampah bs = bankSampahRepository.getOne(idBankSampah);
        return memberRepository.save(new Member(namaLengkap, email, noTelp, alamat, password, salt, poin, sessionToken, saldo, fotoProfil, fotoIdentitas, bs));
    }

    @PutMapping("/member/id")
    public Member updateMember(@PathVariable String id, @RequestBody Map<String, String> body){
        int idMember = Integer.parseInt(id);
        Member member = memberRepository.getOne(idMember);
        member.setNamaLengkap(body.get("namaLengkap"));
        member.setEmail(body.get("email"));
        member.setNoTelp(body.get("noTelp"));
        member.setAlamat(body.get("alamat"));
        member.setPassword(body.get("password"));
        member.setSalt(body.get("salt"));
        member.setPoin(Integer.parseInt(body.get("poin")));
        member.setSaldo(Integer.parseInt(body.get("saldo")));
        member.setFotoProfil(body.get("fotoProfil"));
        member.setFotoIdentitas(body.get("fotoIdentitas"));
        int idBankSampah = Integer.parseInt(body.get("idBankSampah"));
        BankSampah bs = bankSampahRepository.getOne(idBankSampah);
        member.setIdBankSampah(bs);
        return memberRepository.save(member);
    }

    @DeleteMapping
    public boolean deleteMember(@PathVariable String id){
        int id_member = Integer.parseInt(id);
        Member member = memberRepository.getOne(id_member);
        memberRepository.delete(member);
        return true;
    }
}
