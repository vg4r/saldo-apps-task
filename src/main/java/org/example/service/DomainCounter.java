package org.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DomainCounter implements Function<List<String>, Map<String, Integer>> {

    private final Function<String, String> emailParser;

    public DomainCounter(Function<String, String> emailParser) {
        this.emailParser = emailParser;
    }

    @Override
    public Map<String, Integer> apply(List<String> emails) {
        final Map<String, Integer> domainStats = new HashMap<>();
        emails.stream().map(emailParser).forEach( domain -> domainStats.compute(domain, (key, value) -> value == null ? 1 : value + 1));
        return domainStats;
    }
}
