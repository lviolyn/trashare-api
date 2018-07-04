package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.Yayasan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface YayasanRepository extends JpaRepository<Yayasan, Integer>, Repository<Yayasan, Integer> {

    Yayasan findByEmail(String email);
    Yayasan findBySessionToken(String sessionToken);
}
