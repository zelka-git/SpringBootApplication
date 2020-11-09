package com.anzhelika.spring_boot_application.controller;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.service.MemberService;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MemberDTO> getMemberById(@PathVariable UUID memberId) {
        return memberService.findById(memberId);
    }

    @GetMapping("/search")
    public List<MemberDTO> getMemberByName(@RequestParam String name) {
        return memberService.findByName(name);
    }

    @PutMapping
    public MemberDTO updateMemberData(@RequestBody MemberDTO member) {
        return memberService.update(member);
    }

    @DeleteMapping("/{id})")
    public void removeMember(@PathVariable UUID memberId) {
        memberService.deleteById(memberId);
    }
}