package com.anzhelika.spring_boot_application.repository;

import com.anzhelika.spring_boot_application.entity.Team;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
}