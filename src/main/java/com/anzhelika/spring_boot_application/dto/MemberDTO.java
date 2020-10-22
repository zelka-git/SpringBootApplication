package com.anzhelika.spring_boot_application.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private TeamDTO team;
}