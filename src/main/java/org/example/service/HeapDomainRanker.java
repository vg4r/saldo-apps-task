package org.example.service;

import java.util.*;

public class HeapDomainRanker implements DomainRanker {

    @Override
    public Collection<Map.Entry<String, Integer>> rank(Map<String, Integer> domainStats, int top) {
        if (top <= 0){
            throw new IllegalArgumentException();
        }

        if (top >= domainStats.size()){
            return domainStats.entrySet();
        }

        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(top, Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> entry: domainStats.entrySet()){
            heap.offer(entry);
            if (heap.size() > top){
                heap.poll();
            }
        }
        return heap;
    }
}
