package com.exostaz.domain;

public record Configuration(int numberOfFrames,
                            int numberOfSkittles,
                            int numberOfBallsPerFrame,
                            int bonusRollsForStrike,
                            int bonusRollsForSpare) {
}
