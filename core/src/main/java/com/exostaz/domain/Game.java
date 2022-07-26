package com.exostaz.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Game {

    private final List<Frame> frames;
    private final List<Integer> balls;
    private boolean finished;
    private final int totalNumberOfPins;
    private final int numberOfFrames;
    private final int numberOfBallsPerFrame;
    private final int bonusBallsForStrike;
    private final int bonusBallsForSpare;

    public Game(@NotNull Configuration rules) {
        this.totalNumberOfPins = rules.numberOfPins();
        this.numberOfBallsPerFrame = rules.numberOfBallsPerFrame();
        this.bonusBallsForStrike = rules.bonusRollsForStrike();
        this.numberOfFrames = rules.numberOfFrames();
        this.bonusBallsForSpare = rules.bonusRollsForSpare();
        this.frames = new ArrayList<>(numberOfFrames);
        this.balls = new ArrayList<>(numberOfBallsPerFrame);
        frames.add(new Frame(totalNumberOfPins, numberOfBallsPerFrame));
    }

    // todo just for test purpose
    public List<Frame> getFrames() {
        return frames;
    }

    public boolean isFinished() {
        return finished;
    }

    public void play(final int pins) {
        boolean gameIsNotFinished = !this.isFinished();
        if (gameIsNotFinished) {
            balls.add(pins);
            var currentFrame = frames.get(frames.size() - 1);
            currentFrame.getBalls().add(pins);
            executeRules(currentFrame);
        }
    }

    private void executeRules(@NotNull Frame currentFrame) {
        int playedBallsForFrame = currentFrame.getBalls().size();
        boolean isLastFrame = frames.size() == numberOfFrames;

        if (currentFrame.isStrike()) {
            currentFrame.setStrike(true);
            if (isLastFrame) {
                finishGame(playedBallsForFrame, numberOfBallsPerFrame + 1);
            } else {
                updateFrame(currentFrame, bonusBallsForStrike);
            }
        } else if (currentFrame.isFlaggedAsSpare()) {
            if (!currentFrame.isSpare()) {
                currentFrame.setSpare(true);
            }
            if (isLastFrame) {
                finishGame(playedBallsForFrame, numberOfBallsPerFrame + 1);
            } else {
                updateFrame(currentFrame, bonusBallsForSpare);
            }
        } else if (currentFrame.isHole()) {
            currentFrame.setHole(true);
            if (isLastFrame) {
                finishGame(playedBallsForFrame, numberOfBallsPerFrame);
            } else {
                updateFrame(currentFrame, 0);
            }
        }
    }

    private void finishGame(int playedBallsForFrame, int numberOfBallsPerFrame) {
        if (playedBallsForFrame == numberOfBallsPerFrame) {
            this.finished = true;
        }
    }

    private void updateFrame(Frame currentFrame, int numberOfBonusBalls) {
        currentFrame.setNumberOfBonusBalls(numberOfBonusBalls);
        frames.add(new Frame(totalNumberOfPins, numberOfBallsPerFrame));
    }

    private List<Frame> updateFramesBonus() {
        int totalNumberOfBalls = balls.size();
        AtomicInteger ballsIndex = new AtomicInteger();
        IntStream.range(0, frames.size()).forEach(i -> {
            var currentFrame = frames.get(i);
            ballsIndex.addAndGet(currentFrame.getBalls().size());
            int moreBalls = totalNumberOfBalls - ballsIndex.get();
            if (currentFrame.getNumberOfBonusBalls() > 0) {
                int to = ballsIndex.get() + Math.min(moreBalls, currentFrame.getNumberOfBonusBalls());
                currentFrame.setBonusBalls(balls.subList(ballsIndex.get(), to));
            }
        });
        return frames;
    }

    public int calculateScore() {
//        getAdjustedFrames().forEach(System.out::println);
        return updateFramesBonus().stream().mapToInt(Frame::getScore).sum();
    }
}
