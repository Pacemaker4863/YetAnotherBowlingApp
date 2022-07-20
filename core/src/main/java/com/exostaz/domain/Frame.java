package com.exostaz.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Frame {

    private final List<Integer> balls = new ArrayList<>();

    private final int numberOfSkittles;
    private final int numberOfBalls;

    public Frame(int numberOfSkittles, int numberOfBalls) {
        this.numberOfSkittles = numberOfSkittles;
        this.numberOfBalls = numberOfBalls;
    }

    public List<Integer> getBalls() {
        return balls;
    }

    public boolean isSpare() {
        return this.getScore() == numberOfSkittles && this.getBalls().size() > 1;
    }

    public boolean isStrike() {
        return !this.getBalls().isEmpty() && this.getBalls().get(0) == numberOfSkittles;
    }

    public boolean isHole() {
        return this.getScore() != numberOfSkittles && this.getBalls().size() == numberOfBalls;
    }

    public boolean isFinished() {
        return isHole() || isSpare() || isStrike();
    }

    public int getScore() {
        return balls.stream().reduce(0, Integer::sum);
    }

}
