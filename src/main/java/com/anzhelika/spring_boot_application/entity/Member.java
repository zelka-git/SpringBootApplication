package com.anzhelika.spring_boot_application.entity;

import com.anzhelika.spring_boot_application.dto.TeamDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private TeamDTO team;
}