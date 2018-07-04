package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.BankSampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface BankSampahRepository extends JpaRepository<BankSampah, Integer>, Repository<BankSampah, Integer> {

    BankSampah findByEmail(String email);
    BankSampah findBySessionToken(String sessionToken);
}
