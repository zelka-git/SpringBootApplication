package com.anzhelika.spring_boot_application.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = " team")
@ToString(exclude = {"members"})
@EqualsAndHashCode(exclude = {"members"})
public class Team {
    @Id
    @GeneratedValue
    private UUID id;
    String name;
    String city;
    @OneToMany
    Set<Member> members;
}
