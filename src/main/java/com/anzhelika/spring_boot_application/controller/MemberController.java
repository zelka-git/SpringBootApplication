package com.anzhelika.spring_boot_application.controller;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.dto.MembersSalaryDTO;
import com.anzhelika.spring_boot_application.service.MemberService;
import com.anzhelika.spring_boot_application.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "member", description = "member API")
public class MemberController {

    private final MemberService memberService;
    private final SalaryService salaryService;

    @GetMapping
    @PageableAsQueryParam
    @Operation(summary = "Get all members")
    public Page<MemberDTO> getAll(@Parameter(hidden = true)
                                  @PageableDefault(sort = "name", size = 20) Pageable pageable) {
        return memberService.findAll(pageable);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "Find by Id", responses = {
        @ApiResponse(responseCode = "200", description = "success"),
        @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    public MemberDTO getMemberById(@PathVariable UUID memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("/search")
    public List<MemberDTO> getMemberByName(@RequestParam String name) {
        return memberService.findByName(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MemberDTO updateMemberData(@RequestBody MemberDTO member) {
        return memberService.update(member);
    }

    @DeleteMapping("/{memberId})")
    public void removeMember(@PathVariable UUID memberId) {
        memberService.deleteById(memberId);
    }

    @GetMapping("/salary/{memberIds}")
    public MembersSalaryDTO getSalary(@PathVariable Set<UUID> memberIds) {
        return salaryService.getSalaryByPersonIds(memberIds);
    }
}