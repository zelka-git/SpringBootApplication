package com.anzhelika.spring_boot_application.service;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService implements CommonService<MemberDTO, UUID> {

    @Override
    public List<MemberDTO> findAll() {
        return null;
    }

    @Override
    public Optional<MemberDTO> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<MemberDTO> findByName(String name) {
        return null;
    }

    @Override
    public Optional<MemberDTO> update(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(UUID uuid) {
        return false;
    }
}