package com.anzhelika.spring_boot_application.controller;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public String getMemberById(@PathVariable UUID memberId) {
        return "oops";
    }

    @GetMapping("/search")
    public List<String> getMemberByName(@RequestParam(required = true) String name) {
        return null;
    }

    @PutMapping
    public String updateMemberData(@RequestBody MemberDTO member) {
        return null;
    }

    @DeleteMapping("/{id})")
    public Boolean removeMember(@PathVariable UUID memberId) {
        return false;
    }
}