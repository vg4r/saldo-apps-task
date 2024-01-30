package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DomainCounterTest {

    private DomainCounter domainCounter;
    private EmailsParser emailsParser;

    @BeforeEach
    void setUp() {
        emailsParser = mock(EmailsParser.class);
        domainCounter = new DomainCounter(emailsParser);
    }

    @Test
    void returnsEmptyResultWhenEmptyInput() {
        assertTrue(domainCounter.apply(Collections.emptyList()).isEmpty());
    }

    @Test
    void returnsDomainStatsWhenValidInputProvided() {
        //given
        List<String> emails = List.of("test1@example.com", "test2@example.com", "test1@example2.com", "test2@example2.com");
        //when
        when(emailsParser.apply("test1@example.com")).thenReturn("example.com");
        when(emailsParser.apply("test2@example.com")).thenReturn("example.com");
        when(emailsParser.apply("test1@example2.com")).thenReturn("example2.com");
        when(emailsParser.apply("test2@example2.com")).thenReturn("example2.com");
        assertThat(domainCounter.apply(emails)).containsEntry("example.com", 2).containsEntry("example2.com", 2);
    }
}