package com.anzhelika.spring_boot_application.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class TeamDTO {
    private UUID id;
    String name;
    String city;
    Set<MemberDTO> members;
}