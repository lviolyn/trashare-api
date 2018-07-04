package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.BankSampah;
import com.umn.ac.id.trashare.beans.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer>, Repository<Member, Integer> {

    Member findOneByEmail(String email);
    Member findOneBySessionToken(String sessionToken);
}