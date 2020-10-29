package com.anzhelika.spring_boot_application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@ToString(exclude = {"team"})
@EqualsAndHashCode(exclude = {"team"})
public class MemberDTO {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private TeamDTO team;
}