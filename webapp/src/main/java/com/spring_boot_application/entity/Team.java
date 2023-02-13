package com.spring_boot_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = " team")
@ToString(exclude = {"members"})
@EqualsAndHashCode(exclude = {"members"})
public class Team implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String city;
    @OneToMany(mappedBy = "team")
    private Set<Member> members;
}
