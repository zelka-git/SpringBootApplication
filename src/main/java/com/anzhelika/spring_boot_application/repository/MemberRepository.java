package com.anzhelika.spring_boot_application.repository;

import com.anzhelika.spring_boot_application.entity.Member;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByNameOrderBySurnameAsc(String name);
    List<Member> findBySurnameOrderByNameDesc(String surname);
}