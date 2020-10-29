package com.anzhelika.spring_boot_application.dto;

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
    String name;
    String city;
    Set<MemberDTO> members;
}