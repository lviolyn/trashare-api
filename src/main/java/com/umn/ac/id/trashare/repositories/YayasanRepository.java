package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.Yayasan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface YayasanRepository extends JpaRepository<Yayasan, Integer>, Repository<Yayasan, Integer> {

    Yayasan findOneByEmail(String email);
    Yayasan findOneBySessionToken(String sessionToken);
}
