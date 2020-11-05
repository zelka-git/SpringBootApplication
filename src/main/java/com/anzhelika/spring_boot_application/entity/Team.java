package com.anzhelika.spring_boot_application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

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
