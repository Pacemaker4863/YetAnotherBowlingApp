package com.exostaz.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Configuration rules;
    private final List<Frame> frames;
    private final List<Integer> balls;

    public Game(@NotNull Configuration rules) {
        this.rules = rules;
        frames = new ArrayList<>(rules.numberOfFrames());
        balls = new ArrayList<>(rules.numberOfBallsPerFrame());
        frames.add(new Frame(rules.numberOfSkittles(), rules.numberOfBallsPerFrame()));
    }

    // todo just for test purposes
    public List<Frame> getFrames() {
        return frames;
    }

    public void play(final int pins) {
        balls.add(pins);
        var currentFrame = frames.get(frames.size() - 1);
        currentFrame.getBalls().add(pins);

        if (currentFrame.isFinished() && frames.size() <= rules.numberOfFrames() - 1) {
            frames.add(new Frame(rules.numberOfSkittles(), rules.numberOfBallsPerFrame()));
        }
    }

//    public boolean isFinished() {
//        if (this.isLastFrame()) {
//            var lastFrame = frames.get(rules.numberOfFrames() - 2);
//            if (lastFrame.isStrike() || lastFrame.isSpare()) {
//                return lastFrame.getBalls().size() == rules.numberOfBallsPerFrame() + 1;
//            } else {
//                return lastFrame.getBalls().size() == rules.numberOfBallsPerFrame() ;
//            }
//        }
//        return false;
//    }

    private int getBonusPointsForFrame(int numberOfBonusBalls, int moreBalls, int from) {
        int to = from + Math.min(moreBalls, numberOfBonusBalls);
//        System.out.println("sublist from " + from + " to " + to);
        return balls.subList(from, from + Math.min(moreBalls, numberOfBonusBalls))
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

            if (currentFrame.isHole() || !currentFrame.isFinished() || i == rules.numberOfFrames() - 1) {

                System.out.println("hole? " + currentFrame.isHole());
                System.out.println("strike? " + currentFrame.isStrike());
                System.out.println("spare? " + currentFrame.isSpare());
                System.out.println("finished? " + currentFrame.isFinished());

                score += currentFrame.getScore();
            } else if (currentFrame.isSpare()) {
                score += rules.numberOfSkittles() + getBonusPointsForFrame(rules.bonusRollsForSpare(),
                        moreBalls,
                        currentNumberOfBalls);
            } else if (currentFrame.isStrike()) {
                System.out.printf("Strike number %d%n", i + 1);
                score += rules.numberOfSkittles() + getBonusPointsForFrame(rules.bonusRollsForStrike(),
                        moreBalls,
                        currentNumberOfBalls);
            }
            System.out.println();
        }
        return score;
    }

}
