package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.Hadiah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HadiahRepository extends JpaRepository<Hadiah, Integer> {
}
