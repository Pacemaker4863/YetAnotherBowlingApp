package com.exostaz.domain;

import com.exostaz.services.GameManager;
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

@DisplayName("Set of tests for an Archaic bowling game")
class ArchaicBowlingTest {

    private Game game;

    @NotNull
    static Stream<Arguments> generateData() {
        return Stream.of(
                // hole tests
                Arguments.of(Arrays.asList(1, 1, 1), 3),
                Arguments.of(Arrays.asList(8, 2, 1), 11),
                // 2 frames starting with a strike
                Arguments.of(Arrays.asList(15, 7, 3), 35),
                Arguments.of(Arrays.asList(15, 7, 3, 2), 39),
                // just strike tests
                Arguments.of(Arrays.asList(15, 15), 45),
                Arguments.of(Arrays.asList(15, 15, 15), 90),
                Arguments.of(Arrays.asList(15, 15, 15, 15), 150),
                Arguments.of(Arrays.asList(15, 15, 15, 15, 15), 210),
                Arguments.of(Arrays.asList(15, 15, 15, 15, 15, 15), 255),
                Arguments.of(Arrays.asList(15, 15, 15, 15, 15, 15, 15), 285),
                Arguments.of(Arrays.asList(15, 15, 15, 15, 15, 15, 15, 15), 300),
                // spare tests
                // spare in first frame in 2 balls
                Arguments.of(Arrays.asList(8, 7), 15),
                Arguments.of(Arrays.asList(8, 7, 3), 21),
                Arguments.of(Arrays.asList(8, 7, 3, 9), 39),
                Arguments.of(Arrays.asList(8, 7, 3, 9, 7), 46),
                // 2 spares in 2 balls
                Arguments.of(Arrays.asList(8, 7, 3, 12), 45),
                Arguments.of(Arrays.asList(8, 7, 3, 12, 8, 4), 69),
                // spare in first frame in 3 balls
                Arguments.of(Arrays.asList(8, 2, 5), 15),
                Arguments.of(Arrays.asList(8, 2, 5, 9), 33),
                Arguments.of(Arrays.asList(8, 2, 5, 9, 3, 2), 41),

                Arguments.of(Arrays.asList(8, 7, 3, 9), 39),
                Arguments.of(Arrays.asList(8, 7, 3, 9, 7), 46),

                //  at the border
                Arguments.of(Arrays.asList(15, 15, 15, 15, 15, 15, 15, 14), 299),
                Arguments.of(Arrays.asList(15, 15, 15, 15, 14, 0, 0), 206)
        );
    }

    @BeforeEach
    void setUp() {
        game = new Game(GameManager.getGameConfiguration("archaic"));
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
    @DisplayName("throw 2 balls")
    void throwTwoBalls() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(4);
        assertEquals(1, game.getFrames().size());
        assertFalse(game.getFrames().get(0).isHole());
        assertFalse(game.getFrames().get(0).isFinished());
        assertEquals(9, game.calculateScore());
    }

    @Test
    @DisplayName("throw 2 balls, spare")
    void throwTwoBallsAndSpare() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(10);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isSpare());
        assertEquals(15, game.calculateScore());
    }

    @Test
    @DisplayName("throw 3 balls, hole")
    void throwThreeBalls() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(4);
        assertEquals(1, game.getFrames().size());
        game.play(2);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isHole());
        assertEquals(11, game.calculateScore());
    }

    @Test
    @DisplayName("throw 3 balls, spare")
    void throwThreeBallsAndSpare() {
        assertEquals(1, game.getFrames().size());
        game.play(5);
        assertEquals(1, game.getFrames().size());
        game.play(4);
        assertEquals(1, game.getFrames().size());
        game.play(6);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isSpare());
        assertEquals(15, game.calculateScore());
    }

    @Test
    @DisplayName("throw 1 ball and strike")
    void throwOneBallStrike() {
        assertEquals(1, game.getFrames().size());
        game.play(15);
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertTrue(game.getFrames().get(0).isStrike());
        assertEquals(15, game.calculateScore());
    }

    @Test
    @DisplayName("throw 6 balls, hole")
    void throwSixBalls() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 2; i++) {
            game.play(4);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(12, game.calculateScore());

        for (int i = 0; i <= 2; i++) {
            game.play(3);
        }
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(21, game.calculateScore());
    }

    @Test
    @DisplayName("throw 6 balls, spare")
    void throwSixBallsSpare() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 2; i++) {
            game.play(4);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(12, game.calculateScore());

        for (int i = 0; i <= 2; i++) {
            game.play(5);
        }
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(27, game.calculateScore());
    }

    @Test
    @DisplayName("throw 6 balls starting with a spare")
    void throwSixBallsStartingWithSpare() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 2; i++) {
            game.play(5);
        }
        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isSpare());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(15, game.calculateScore());

        game.play(4);
        assertEquals(23, game.calculateScore());

        game.play(4);
        assertEquals(31, game.calculateScore());

        game.play(2);
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(33, game.calculateScore());
    }

    @Test
    @DisplayName("throw 6 balls starting with a strike")
    void throwSixBallsStartingWithStrike() {
        assertEquals(1, game.getFrames().size());

        game.play(15);

        assertEquals(2, game.getFrames().size());
        assertTrue(game.getFrames().get(0).isStrike());
        assertTrue(game.getFrames().get(0).isFinished());
        assertEquals(15, game.calculateScore());

        game.play(4);
        assertEquals(23, game.calculateScore());

        game.play(4);
        assertEquals(31, game.calculateScore());

        game.play(2);
        assertEquals(3, game.getFrames().size());
        assertTrue(game.getFrames().get(1).isFinished());
        assertEquals(35, game.calculateScore());
    }

    @Test
    @DisplayName("5 frames, holes")
    void playFiveFrames() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 14; i++) {
            game.play(4);
        }
//        assertTrue(game.isLastFrame());
//        assertTrue(game.isFinished());
        assertEquals(5, game.getFrames().size());
        assertEquals(60, game.calculateScore());
    }

    @Test
    @DisplayName("5 frames, all strikes")
    void allStrikes() {
        assertEquals(1, game.getFrames().size());
        for (int i = 0; i <= 7; i++) {
            game.play(15);
        }
        assertEquals(5, game.getFrames().size());
        assertEquals(300, game.calculateScore());
    }

    @Test
    @DisplayName("5 frames, second one is a spare")
    void playFiveFramesWithOneSpare() {
        assertEquals(1, game.getFrames().size());
        game.play(4);
        game.play(7);
        game.play(1); // 12
        game.play(3);
        game.play(5);
        game.play(7); // 15 spare -> 18
        game.play(1);
        game.play(2);
        game.play(3); // 6
        game.play(4);
        game.play(3);
        game.play(2); // 9
        game.play(5);
        game.play(3);
        game.play(2); // 10
        assertEquals(55, game.calculateScore());
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
