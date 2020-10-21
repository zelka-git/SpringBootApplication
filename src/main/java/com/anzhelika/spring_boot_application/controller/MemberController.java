package com.anzhelika.spring_boot_application.controller;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    @GetMapping
    public List<String> getAll() {
        return List.of("member1", "member2");
    }

    @GetMapping("/{id}")
    public String getMemberById(@PathVariable UUID memberId) {
        return "oops";
    }

    @GetMapping
    public List<String> getMemberByName(@RequestParam("name") String name) {
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