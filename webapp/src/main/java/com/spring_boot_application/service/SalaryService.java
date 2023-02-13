package com.spring_boot_application.service;

import com.spring_boot_application.dto.MembersSalaryDTO;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryService {

    private static final String ENDPOINT = "/person/?ids=%s";

    private final WebClient webClient;

    @Value("${salary-server.url}")
    private String url;

//    @Async
//    public void salaryRequest(UUID personId) {
//        webClient.get()
//                .uri(url + String.format(ENDPOINT, personId))
//                .exchange()
//                .retryWhen(Retry.backoff(3, Duration.ofSeconds(40))
//                        .jitter(0d)
//                        .doAfterRetry(retrySignal -> log.info("Retried" + retrySignal.totalRetries()))
//                        .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> new TimeoutException())))
//                .doOnSuccess(clientResponse -> {
//                    log.info("Event is received");
//                })
//                .doOnError(TimeoutException.class, msg -> log.info("Message :: " + msg))
//                .subscribe();
//    }

    public MembersSalaryDTO getSalaryByPersonIds(Set<UUID> personIds) {
        String stringOfPersonIds = personIds.stream()
                .map(UUID::toString)
                .collect(Collectors.joining(","));

        ClientResponse response = webClient.get()
                .uri(url + String.format(ENDPOINT, stringOfPersonIds))
                .exchange()
                .onErrorStop()
                .block();

        if (Objects.requireNonNull(response).statusCode() == HttpStatus.OK) {
            return response.bodyToMono(MembersSalaryDTO.class).block();
        }
        log.error("Salary can't be got");
        return null;
    }
}
