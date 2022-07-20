package com.exostaz.domain;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Frame {

    private final List<Integer> balls = new ArrayList<>();

    private int numberOfSkittles;
    private int numberOfBalls;

    public Frame(int numberOfSkittles, int numberOfBalls) {
        this.numberOfSkittles = numberOfSkittles;
        this.numberOfBalls = numberOfBalls;
    }

    public List<Integer> getBalls() {
        return balls;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public void setNumberOfSkittles(int numberOfSkittles) {
        this.numberOfSkittles = numberOfSkittles;
    }

    public boolean isSpare() {
        return this.getScore() == numberOfSkittles && this.getBalls().size() > 1;
    }

    public boolean isStrike() {
        return this.getScore() == numberOfSkittles && this.getBalls().size() == 1;
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
