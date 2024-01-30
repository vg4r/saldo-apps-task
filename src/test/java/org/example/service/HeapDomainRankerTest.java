package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeapDomainRankerTest {

    private DomainRanker domainRanker;

    @BeforeEach
    void setUp() {
        domainRanker = new HeapDomainRanker();
    }

    @ParameterizedTest
    @ValueSource(ints =  { -1, 0 })
    void rankThrowsExceptionWhenTopIsNotPositive(Integer top) {
        assertThrows(IllegalArgumentException.class, () -> domainRanker.rank(Map.of(), top));
    }


    @Test
    void rankReturnAllWhenTopIsLessThanDomainsSize(){
        //given
        final int top = 10;
        Map<String, Integer> domainStats = Map.of(
                "domain0.example.com", 1,
                "domain1.example.com", 1,
                "domain2.example.com", 3,
                "domain3.example.com", 10,
                "domain4.example.com", 11,
                "domain5.example.com", 12,
                "domain6.example.com", 10,
                "domain7.example.com", 15
        );

        //when
        Collection<Map.Entry<String, Integer>> ranked = domainRanker.rank(domainStats, top);

        assertThat(ranked).containsExactlyInAnyOrderElementsOf(domainStats.entrySet());
    }

    @Test
    void ranksTopElementsByValue(){
        //given
        final int top = 3;
        Map<String, Integer> domainStats = Map.of(
                "domain0.example.com", 1,
                "domain1.example.com", 1,
                "domain2.example.com", 3,
                "domain3.example.com", 10,
                "domain4.example.com", 11,
                "domain5.example.com", 12,
                "domain6.example.com", 10,
                "domain7.example.com", 15
        );
        // when
        Collection<Map.Entry<String, Integer>> ranked = domainRanker.rank(domainStats, top);

        //then
        assertThat(ranked).containsExactlyInAnyOrderElementsOf(Map.of(
                "domain7.example.com", 15,
                "domain5.example.com", 12,
                "domain4.example.com", 11
        ).entrySet());
    }




}