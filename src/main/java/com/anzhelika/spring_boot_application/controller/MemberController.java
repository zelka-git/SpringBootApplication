package com.anzhelika.spring_boot_application.controller;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.service.MemberService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{memberId}")
    public MemberDTO getMemberById(@PathVariable UUID memberId) {
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

    @DeleteMapping("/{memberId})")
    public void removeMember(@PathVariable UUID memberId) {
        memberService.deleteById(memberId);
    }
}