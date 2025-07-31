package com.skillerwhale.wordle.core;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

class EvaluateGuessTest {

    @Test
    void shouldReturnAllGrayColors_WhenNoLettersMatch() {
        List<String> result = EvaluateGuess.evaluateGuess("xxxxx");
        assertThat(result).isEqualTo(Arrays.asList("gray", "gray", "gray", "gray", "gray"));
    }

    @Test
    @org.junit.jupiter.api.Disabled("TODO: enable this test")
    void shouldReturnGreenColors_ForLettersInTheCorrectPlace() {

    }

    @Test
    @org.junit.jupiter.api.Disabled("TODO: enable this test")
    void shouldReturnYellowColor_WhenCorrectLetterIsInWrongPosition() {

    }
}
