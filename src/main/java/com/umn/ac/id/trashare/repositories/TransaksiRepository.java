package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
}
