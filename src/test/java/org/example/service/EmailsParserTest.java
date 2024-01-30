package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailsParserTest {

    private EmailsParser emailsParser;

    @BeforeEach
    void setUp() {
        emailsParser = new EmailsParser();
    }

    @Test
    void returnsDomainWhenValidEmail() {
        // given
        String email = "test@example.com";
        // when
        String result = emailsParser.apply(email);

        // then
        assertEquals("example.com", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test-example.com", "test@", ""})
    void throwsIllegalArgumentExceptionWhenInValidEmail(String email) {
        assertThrows(IllegalArgumentException.class, () -> emailsParser.apply(email));
    }

    @Test
    void throwsNullPointerExceptionWhenInValidEmail() {
        assertThrows(NullPointerException.class, () -> emailsParser.apply(null));
    }
}