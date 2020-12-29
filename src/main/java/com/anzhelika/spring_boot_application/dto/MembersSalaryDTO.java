package com.anzhelika.spring_boot_application.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembersSalaryDTO {
    private List<MemberSalaryDTO> memberSalary;
}
