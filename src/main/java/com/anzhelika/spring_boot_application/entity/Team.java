package com.anzhelika.spring_boot_application.entity;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = " team")
public class Team {
    @Id
    @GeneratedValue
    private UUID id;
    String name;
    String city;
    @OneToMany
    Set<MemberDTO> members;
}
