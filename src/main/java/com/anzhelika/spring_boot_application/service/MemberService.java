package com.anzhelika.spring_boot_application.service;

import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.entity.Member;
import com.anzhelika.spring_boot_application.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService implements CommonService<MemberDTO, UUID> {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<MemberDTO> findAll() {
        return memberRepository.findAll().stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO findById(UUID uuid) {
        return modelMapper.map(memberRepository.findById(uuid).orElse(new Member()), MemberDTO.class);
    }

    @Override
    public List<MemberDTO> findByName(String name) {
        return memberRepository.findByNameOrderBySurnameAsc(name).stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

    public MemberDTO update(MemberDTO member) {
        Member map = modelMapper.map(member, Member.class);
        return modelMapper.map(memberRepository.save(map), MemberDTO.class);
    }

    @Override
    public void deleteById(UUID uuid) {
        memberRepository.deleteById(uuid);
    }
}