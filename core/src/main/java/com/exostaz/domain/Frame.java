package com.exostaz.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Frame {

    private final List<Integer> balls = new ArrayList<>();
    private List<Integer> bonusBalls = new ArrayList<>();

    private final int numberOfPins;
    private int numberOfBalls;
    private int numberOfBonusBalls;
    private boolean strike;
    private boolean spare;
    private boolean hole;
    private boolean finished;

    public Frame(int numberOfPins, int numberOfBalls) {
        this.numberOfPins = numberOfPins;
        this.numberOfBalls = numberOfBalls;
    }

    public boolean isStrike() {
        return !getBalls().isEmpty() && getBalls().get(0) == numberOfPins;
    }

    public boolean isFlaggedAsSpare() {
        return !getBalls().isEmpty() && getBalls().size() <= numberOfBalls && getScore() == numberOfPins || isSpare();
    }

    public boolean isHole() {
        return !getBalls().isEmpty() && getBalls().size() == numberOfBalls && getScore() <= numberOfPins;
    }

    public boolean isFinished() {
        return isHole() || isSpare() || isStrike();
    }

    public int getScore() {
        return Stream.concat(balls.stream(), bonusBalls.stream()).mapToInt(Integer::intValue).sum();
    }

    public String toString() {
        return balls + " " + bonusBalls + " " + this.getScore();
    }
}
