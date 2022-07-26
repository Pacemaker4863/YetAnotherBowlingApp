package com.exostaz.domain;

import com.exostaz.server.domain.Game;
import com.exostaz.server.services.GameManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Set of tests for a classic bowling game")
class ClassicBowlingTest {

    private Game game;

    @NotNull
    static Stream<Arguments> generateData() {
        return Stream.of(
                // hole tests
                Arguments.of(Arrays.asList(1, 1, 1), 3),
                Arguments.of(Arrays.asList(8, 2, 1), 12),
                // 2 frames starting with a strike
                Arguments.of(Arrays.asList(10, 7), 24),
                Arguments.of(Arrays.asList(10, 7, 2), 28),
                // just strike tests
                Arguments.of(Arrays.asList(10, 10), 30),
                Arguments.of(Arrays.asList(10, 10, 10), 60),
                Arguments.of(Arrays.asList(10, 10, 10, 10), 90),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10), 120),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10), 150),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10), 180),
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10), 210),
                // spare tests
                // spare in first frame in 2 balls
                Arguments.of(Arrays.asList(8, 2), 10),
                Arguments.of(Arrays.asList(8, 2, 3), 16),
                Arguments.of(Arrays.asList(8, 2, 3, 4), 20),
                // 2 spares in 4 balls
                Arguments.of(Arrays.asList(8, 2, 3, 7), 23),
                Arguments.of(Arrays.asList(8, 2, 0, 10, 8, 1), 37),

                Arguments.of(Arrays.asList(8, 2, 3, 6), 22),
                Arguments.of(Arrays.asList(8, 2, 3, 4, 7), 27),

                //  at the border
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 9), 299)
        );
    }

    @BeforeEach
    void setUp() {
        game = new Game(GameManager.getGameConfiguration("Classic"));
    }

    @Test
    @DisplayName("throw 1 ball")
    void throwOneBall() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        assertFalse(game.getFrames().get(0).isHole());
        assertFalse(game.getFrames().get(0).isFinished());
        assertEquals(5, game.calculateScore());
    }

    @Test
    @DisplayName("throw 2 balls, hole")
    void throwTwoBalls() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(4);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isHole());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(9, game.calculateScore());
    }

    @Test
    @DisplayName("throw 2 balls, spare")
    void throwTwoBallsAndSpare() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isSpare());
        assertEquals(10, game.calculateScore());
    }

    @Test
    @DisplayName("throw 1 ball and strike")
    void throwOneBallStrike() {
        assertEquals(1, game.getFrames().size());
        game.play(10);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isStrike());
        assertEquals(10, game.calculateScore());
    }

    @Test
    @DisplayName("throw 4 balls, hole")
    void throwSixBalls() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 1; i++) {
            game.play(4);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(8, game.calculateScore());

        for (int i = 0; i <= 1; i++) {
            game.play(3);
        }
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(14, game.calculateScore());
    }

    @Test
    @DisplayName("throw 4 balls, spare")
    void throwSixBallsSpare() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 1; i++) {
            game.play(4);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(8, game.calculateScore());

        for (int i = 0; i <= 1; i++) {
            game.play(5);
        }
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertTrue(game.getFrames().get(1).isSpare());
        assertEquals(18, game.calculateScore());
    }

    @Test
    @DisplayName("throw 4 balls starting with a spare")
    void throwSixBallsStartingWithSpare() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 1; i++) {
            game.play(5);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isSpare());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(10, game.calculateScore());

        game.play(4);
        assertEquals(18, game.calculateScore());

        game.play(4);

        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertFalse(game.isFinished());
        assertEquals(22, game.calculateScore());
    }

    @Test
    @DisplayName("throw 4 balls starting with a strike")
    void throwSixBallsStartingWithStrike() {
        assertEquals(1, game.getFrames().size());

        game.play(10);

        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isStrike());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(10, game.calculateScore());

        game.play(4);
        assertEquals(18, game.calculateScore());

        game.play(4);

        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(26, game.calculateScore());
    }

    @Test
    @DisplayName("10 frames, holes")
    void playFiveFrames() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 19; i++) {
            game.play(4);
        }
        assertEquals(10, game.getFrames().size());
        assertEquals(80, game.calculateScore());
    }

    @Test
    @DisplayName("10 frames, all strikes")
    void allStrikes() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 11; i++) {
            assertFalse(game.isFinished());
            game.play(10);
        }
        assertTrue(game.isFinished());
        assertEquals(10, game.getFrames().size());
        assertEquals(300, game.calculateScore());
    }

    @Test
    @DisplayName("10 frames, second one is a spare")
    void playFiveFramesWithOneSpare() {
        assertEquals(1, game.getFrames().size());
        game.play(4);
        game.play(1); // 5
        game.play(5);
        game.play(5); // 12
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7
        game.play(2);
        game.play(5); // 7

        assertEquals(73, game.calculateScore());
    }

    @ParameterizedTest(name = "{index} => calculates the score of {0} and expects {1}")
    @MethodSource("generateData")
    void shouldComputeCorrectScore(List<Integer> balls, int expected) {
        for (Integer ball : balls) {
            game.play(ball);
        }
        assertEquals(expected, game.calculateScore());
    }

}
