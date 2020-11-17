package com.anzhelika.spring_boot_application.controller;


import com.anzhelika.spring_boot_application.SpringBootApplication;
import com.anzhelika.spring_boot_application.dto.MemberDTO;
import com.anzhelika.spring_boot_application.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.yml")
class MemberControllerIntegrationTest {

    public static final String MEMBERS_ENDPOINT = "/members";
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    private static MemberDTO validMember;
    private static MemberDTO validMemberForUpdate;

    @BeforeAll
    public static void init() {
        validMember = new MemberDTO()
                .setId(UUID.fromString("9dea8838-ae07-4e98-ac8f-947fdeaabaa0"))
                .setName("Andrey")
                .setSurname("Milutin")
                .setBirthday(LocalDate.of(2001, 11, 12));
        validMemberForUpdate = new MemberDTO()
                .setId(UUID.fromString("ec2e2fda-819a-4042-a38d-9b829d6b3353"))
                .setName("Kirill")
                .setSurname("Andreev")
                .setBirthday(LocalDate.of(2001, 2, 11));
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(MEMBERS_ENDPOINT))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<Member> members = objectMapper.readValue(contentAsString,
                objectMapper.getTypeFactory().constructCollectionType(List.class, MemberDTO.class));
        Assert.assertEquals(members.size(), 24);
    }

    @Test
    void getExistingMemberById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(MEMBERS_ENDPOINT + "/" + validMember.getId().toString()))
                .andReturn();
        MemberDTO member = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MemberDTO.class);
        Assert.assertEquals(validMember, member);
    }

    @Test
    void getNotExistingMemberById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(MEMBERS_ENDPOINT + "/" + UUID.randomUUID()))
                .andReturn();
        MemberDTO member = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MemberDTO.class);
        Assert.assertEquals(new MemberDTO(), member);
    }

    @Test
    void getExistingMemberByName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(MEMBERS_ENDPOINT + "/search?name=Andrey"))
                .andReturn();
        List<MemberDTO> members = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, MemberDTO.class));
        Assert.assertEquals(validMember, members.get(0));
    }

    @Test
    void getNotExistingMemberByName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(MEMBERS_ENDPOINT + "/search?name=Joshya"))
                .andReturn();
        List<MemberDTO> members = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, MemberDTO.class));
        Assert.assertEquals(new ArrayList<MemberDTO>(), members);
    }

    @Test
    void updateExistingMemberData() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put(MEMBERS_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"ec2e2fda-819a-4042-a38d-9b829d6b3353\"," +
                        "\"name\":\"Igor\"," +
                        "\"surname\":\"Andreev\"," +
                        "\"birthday\":\"2001-06-11\"}"))
                .andReturn();
        MemberDTO member = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MemberDTO.class);
        Assert.assertEquals(validMemberForUpdate.setName("Igor").setBirthday(LocalDate.of(2001, 6, 11)), member);
    }

    @Test
    @Order(2)
    void saveMember() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put(MEMBERS_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Garry\"," +
                        "\"surname\":\"Potter\"," +
                        "\"birthday\":\"1989-02-11\"}"))
                .andReturn();
        MemberDTO member = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MemberDTO.class);
        Assert.assertNotNull(member);
    }
}