package org.example.service;

import java.util.function.Function;

public class EmailsParser implements Function<String, String> {
    private static final char at = '@';

    @Override
    public String apply(String s) {
        if (s == null){
            throw new NullPointerException();
        }
        int atSignIndex = s.indexOf(at);
        if (atSignIndex == -1 || atSignIndex == s.length() - 1){
            throw new IllegalArgumentException();
        }
        return s.substring(atSignIndex + 1);
    }
}
