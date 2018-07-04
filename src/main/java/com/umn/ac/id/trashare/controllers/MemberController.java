package com.umn.ac.id.trashare.controllers;

import com.umn.ac.id.trashare.Utils.StringUtils;
import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Member;
import com.umn.ac.id.trashare.repositories.BankSampahRepository;
import com.umn.ac.id.trashare.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.IOException;
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
        String salt = BCrypt.gensalt();
        String newPassword = BCrypt.hashpw(password, salt);
        int poin = body.get("poin") != null ? Integer.parseInt(body.get("poin")) : 0;
        String sessionToken = "";
        int saldo = body.get("saldo") != null ? Integer.parseInt(body.get("saldo")) : 0;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] fotoProfil = null, fotoIdentitas = null;
        try {
            fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
            fotoIdentitas = decoder.decodeBuffer(body.get("fotoIdentitas"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int idBankSampah = body.get("idBankSampah") != null ? Integer.parseInt(body.get("idBankSampah")) : 0;
        BankSampah bs = null;
        if(idBankSampah != 0){
            bs = bankSampahRepository.getOne(idBankSampah);
        }
        String pushToken = body.get("pushToken");
        //BankSampah bs = bankSampahRepository.getOne(idBankSampah);
        return memberRepository.save(new Member(namaLengkap, email, noTelp, alamat, newPassword, salt, poin, sessionToken, saldo, fotoProfil, fotoIdentitas, bs, pushToken));
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody Map<String, String> body){
        int idMember = Integer.parseInt(id);
        Member member = memberRepository.getOne(idMember);
        if(body.get("namaLengkap") != null && !body.get("namaLengkap").equals("")) {
            member.setNamaLengkap(body.get("namaLengkap"));
        }
        if(body.get("email") != null && !body.get("email").equals("")) {
            member.setEmail(body.get("email"));
        }
        if(body.get("noTelp") != null && !body.get("noTelp").equals("")) {
            member.setNoTelp(body.get("noTelp"));
        }
        if(body.get("alamat") != null && !body.get("alamat").equals("")) {
            member.setAlamat(body.get("alamat"));
        }
        if(body.get("password") != null && !body.get("password").equals("")) {
            String newPassword = body.get("password");
            String newSalt = BCrypt.gensalt();
            member.setSalt(newSalt);
            member.setPassword(BCrypt.hashpw(newPassword, newSalt));
        }
        if(body.get("poin") != null && !body.get("poin").equals("")) {
            member.setPoin(Integer.parseInt(body.get("poin")));
        }
        if(body.get("saldo") != null && !body.get("saldo").equals("")) {
            member.setSaldo(Integer.parseInt(body.get("saldo")));
        }
        if(body.get("fotoProfil") != null && !body.get("fotoProfil").equals("")) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] fotoProfil = null;
            try {
                fotoProfil = decoder.decodeBuffer(body.get("fotoProfil"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            member.setFotoProfil(fotoProfil);
        }
        if(body.get("fotoIdentitas") != null && !body.get("fotoIdentitas").equals("")){
            BASE64Decoder decoder2 = new BASE64Decoder();
            byte[] fotoIdentitas = null;
            try{
                fotoIdentitas = decoder2.decodeBuffer(body.get("fotoIdentitas"));
            } catch (IOException e){
                e.printStackTrace();
            }
            member.setFotoIdentitas(fotoIdentitas);
        }
        if(body.get("idBankSampah") != null && !body.get("idBankSampah").equals("")) {
            int idBankSampah = Integer.parseInt(body.get("idBankSampah"));
            BankSampah bs = bankSampahRepository.getOne(idBankSampah);
            member.setIdBankSampah(bs);
        }
        if(body.get("pushToken") != null && !body.get("pushToken").equals("")) {
            member.setPushToken(body.get("pushToken"));
        }
        return memberRepository.save(member);
    }

    @DeleteMapping("/member/{id}")
    public boolean deleteMember(@PathVariable String id){
        int id_member = Integer.parseInt(id);
        Member member = memberRepository.getOne(id_member);
        memberRepository.delete(member);
        return true;
    }

    @PostMapping("/member/login")
    public Member loginMember(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        Member member = memberRepository.findOneByEmail(email);
        if (member != null) {
            if (BCrypt.checkpw(password, member.getPassword())) {
                member.setSessionToken(StringUtils.randomAlphaNumeric(128));
                memberRepository.save(member);
                return member;
            }
        }
        return null;
    }
}
