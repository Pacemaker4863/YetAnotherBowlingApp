package com.exostaz.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Frame {

    private final List<Integer> balls = new ArrayList<>();

    private final int numberOfPins;
    private int numberOfBalls;

    private int bonusBalls;

    private boolean strike;
    private boolean spare;
    private boolean hole;
    private boolean finished;

    public Frame(int numberOfPins, int numberOfBalls) {
        this.numberOfPins = numberOfPins;
        this.numberOfBalls = numberOfBalls;
    }

    public boolean isFinished() {
        return isHole() || isSpare() || isStrike();
    }

    public int getScore() {
        return balls.stream().reduce(0, Integer::sum);
    }

}
