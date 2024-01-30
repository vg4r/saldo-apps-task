package org.example.service;

import java.util.Collection;
import java.util.Map;

public interface DomainRanker {
    Collection<Map.Entry<String, Integer>> rank(Map<String, Integer> domainStats, int top);
}
