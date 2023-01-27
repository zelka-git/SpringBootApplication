package com.anzhelika.spring_boot_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.anzhelika.spring_boot_application.dto.MemberSalaryDTO;
import com.anzhelika.spring_boot_application.dto.MembersSalaryDTO;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class SalaryServiceTest {

    static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SalaryService.class);

    ListAppender<ILoggingEvent> listAppender;

    @InjectMocks
    SalaryService salaryService;

    @Mock
    WebClient webClient;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriMock;

    @BeforeEach
    void init() {
        setField(salaryService, "url", "/");
    }

    @Test
    void getSalaryByPersonIds_WithBadRequestFromClient_Failure() {
        listAppender = new ListAppender<>();
        listAppender.start();
        LOGGER.addAppender(listAppender);

        ClientResponse clientResponse = ClientResponse
                .create(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        when(webClient.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri(Mockito.anyString())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.exchange()).thenReturn(Mono.just(clientResponse));

        salaryService.getSalaryByPersonIds(Set.of(UUID.randomUUID()));

        Assertions.assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage, ILoggingEvent::getLevel)
                .containsExactly(Tuple.tuple("Salary can't be got", Level.ERROR));
    }

    @Test
    void getSalaryByPersonIds_WithGoodRequestFromClient_Success() {
        MemberSalaryDTO memberSalary = MemberSalaryDTO.builder()
                .id(UUID.randomUUID())
                .salary(50000)
                .build();

        ClientResponse clientResponse = ClientResponse
                .create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{\"memberSalary\":[{\"id\":\"" + memberSalary.getId() + "\",\"salary\":" + memberSalary.getSalary() + "}]}")
                .build();

        when(webClient.get()).thenReturn(requestHeadersUriMock);
        when(requestHeadersUriMock.uri(Mockito.anyString())).thenReturn(requestHeadersMock);
        when(requestHeadersMock.exchange()).thenReturn(Mono.just(clientResponse));

        assertEquals(new MembersSalaryDTO(List.of(memberSalary)), salaryService.getSalaryByPersonIds(Set.of(memberSalary.getId())));
    }
}