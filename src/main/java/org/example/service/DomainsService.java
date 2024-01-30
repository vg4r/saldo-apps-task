package org.example.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class DomainsService {
    private final Function<List<String>, Map<String, Integer>> counter;
    private final DomainRanker domainRanker;

    public DomainsService(Function<List<String>, Map<String, Integer>> counter, DomainRanker domainRanker) {
        this.counter = counter;
        this.domainRanker = domainRanker;
    }

    Collection<Map.Entry<String, Integer>> rankDomainsInEmails (List<String> emails, int topN) {
        final Map<String, Integer> domainStats = counter.apply(emails);
        return domainRanker.rank(domainStats, topN);
    }
}
