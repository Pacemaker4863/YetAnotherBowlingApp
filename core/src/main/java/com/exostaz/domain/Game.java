package com.exostaz.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Configuration rules;
    private final List<Frame> frames;
    private final List<Integer> balls;
    private boolean finished;

    public Game(@NotNull Configuration rules) {
        this.rules = rules;
        frames = new ArrayList<>(rules.numberOfFrames());
        balls = new ArrayList<>(rules.numberOfBallsPerFrame());
        frames.add(new Frame(rules.numberOfPins(), rules.numberOfBallsPerFrame()));
    }

    // todo just for test purposes
    public List<Frame> getFrames() {
        return frames;
    }

    public void play(final int pins) {

        balls.add(pins);
        var currentFrame = frames.get(frames.size() - 1);
        currentFrame.getBalls().add(pins);

        int firstBallOfTheFrame = currentFrame.getBalls().get(0);
        int totalPins = rules.numberOfPins();
        int numberOfBallsPerFrame = rules.numberOfBallsPerFrame();
        int bonusBallsForStrike = rules.bonusRollsForStrike();
        int currentPointsForFrame = currentFrame.getBalls().stream().mapToInt(Integer::intValue).sum();
        int playedBalls = currentFrame.getBalls().size();
        boolean spare = playedBalls <= numberOfBallsPerFrame && currentPointsForFrame == totalPins;
        boolean lastFrame = this.frames.size() == rules.numberOfFrames();
        boolean hole = playedBalls == numberOfBallsPerFrame && currentPointsForFrame <= totalPins;

        if (firstBallOfTheFrame == totalPins) {
            currentFrame.setStrike(true);
            if (lastFrame) {
                if (playedBalls == rules.numberOfBallsPerFrame() + 1) {
                    this.finished = true;
                }
            } else {
                currentFrame.setBonusBalls(bonusBallsForStrike);
                frames.add(new Frame(totalPins, numberOfBallsPerFrame));
            }
        } else if (spare) {
            currentFrame.setSpare(true);
            if (lastFrame) {
                if (currentFrame.getBalls().size() == rules.numberOfBallsPerFrame() + 1) {
                    this.finished = true;
                }
            } else {
                currentFrame.setBonusBalls(rules.bonusRollsForSpare());
                frames.add(new Frame(totalPins, numberOfBallsPerFrame));
            }
        } else if (hole) {
            currentFrame.setHole(true);
            if (lastFrame) {
                this.finished = true;
            } else {
                frames.add(new Frame(totalPins, numberOfBallsPerFrame));
            }
        }
        System.out.println("Game is finished? " + this.finished);
        System.out.println();
    }

    private int getBonusPointsForFrame(int numberOfBonusBalls, int moreBalls, int from) {
        int to = from + Math.min(moreBalls, numberOfBonusBalls);
        return balls.subList(from, to)
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateScore() {
        int score = 0;
        int currentNumberOfBalls = 0;
        int totalNumberOfBalls = balls.size();

        for (int i = 0; i <= frames.size() - 1; i++) {
            var currentFrame = frames.get(i);
            currentNumberOfBalls += currentFrame.getBalls().size();
            int moreBalls = totalNumberOfBalls - currentNumberOfBalls;
            System.out.printf("frame number %d%n", i + 1);
            boolean lastFrame = i == rules.numberOfFrames() - 1;

            if (currentFrame.getBonusBalls() > 0) {

                System.out.println("hole? " + currentFrame.isHole());
                System.out.println("strike? " + currentFrame.isStrike());
                System.out.println("spare? " + currentFrame.isSpare());
                System.out.println("finished? " + currentFrame.isFinished());

                score += rules.numberOfPins() + getBonusPointsForFrame(currentFrame.getBonusBalls(),
                        moreBalls,
                        currentNumberOfBalls);
            } else {

                System.out.println("hole? " + currentFrame.isHole());
                System.out.println("strike? " + currentFrame.isStrike());
                System.out.println("spare? " + currentFrame.isSpare());
                System.out.println("finished? " + currentFrame.isFinished());

                score += currentFrame.getScore();
            }
            System.out.println();
        }
        return score;
    }

}
