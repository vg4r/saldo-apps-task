package org.example.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DomainsServiceTest {
    private DomainCounter domainCounter;
    private DomainRanker domainRanker;
    private DomainsService domainsService;


    @BeforeEach
    void setUp() {
        domainCounter = mock(DomainCounter.class);
        domainRanker = mock(DomainRanker.class);
        domainsService = new DomainsService(domainCounter, domainRanker);
    }

    @Test
    void rankDomainsInEmails() {
        // given
        List<String> emails = List.of(
                "test1@example.com",
                "test2@example.com",
                "test2@example.com",
                "test1@example2.com",
                "test2@example2.com",
                "test1@example3.com",
                "test1@example4.com"
        );
        final Map<String, Integer> domainStats = Map.of("example.com", 3, "example2.com", 2, "example3.com", 1, "example4.com", 1);
        final int top = 2;
        final Collection<Map.Entry<String, Integer>> top2Domains = List.of(new AbstractMap.SimpleEntry<>("example.com", 3), new AbstractMap.SimpleEntry<>("example2.com", 2));

        //when
        when(domainCounter.apply(emails)).thenReturn(domainStats);
        when(domainRanker.rank(domainStats, 2)).thenReturn(top2Domains);

        // then
        assertThat(domainsService.rankDomainsInEmails(emails, top)).containsExactlyElementsOf(top2Domains);

    }
}