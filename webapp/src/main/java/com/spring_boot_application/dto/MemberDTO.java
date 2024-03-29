package com.spring_boot_application.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(exclude = {"team"})
@EqualsAndHashCode(exclude = {"team"})
public class MemberDTO {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private TeamDTO team;
}