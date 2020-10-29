package com.anzhelika.spring_boot_application.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "member")
@ToString(exclude = {"team"})
@EqualsAndHashCode(exclude = {"team"})
public class Member {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}