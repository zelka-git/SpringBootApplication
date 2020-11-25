package com.anzhelika.spring_boot_application.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "member")
@ToString(exclude = {"team"})
@EqualsAndHashCode(exclude = {"team"})
public class Member implements Serializable {
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