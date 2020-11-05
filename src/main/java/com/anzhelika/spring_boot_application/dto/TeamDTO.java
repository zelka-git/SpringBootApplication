package com.anzhelika.spring_boot_application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@ToString(exclude = {"members"})
@EqualsAndHashCode(exclude = {"members"})
public class TeamDTO {
    private UUID id;
    private String name;
    private String city;
    @JsonIgnore
    private Set<MemberDTO> members;
}