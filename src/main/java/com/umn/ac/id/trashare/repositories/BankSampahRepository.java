package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface BankSampahRepository extends JpaRepository<BankSampah, Integer>, Repository<BankSampah, Integer> {

    BankSampah findOneByEmail(String email);
    BankSampah findOneBySessionToken(String sessionToken);
    BankSampah findOneByMembers(Member member);
}
