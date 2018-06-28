package com.umn.ac.id.trashare.repositories;

import com.umn.ac.id.trashare.beans.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
