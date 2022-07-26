package com.exostaz.server.domain;

public record Configuration(int numberOfFrames,
                            int numberOfPins,
                            int numberOfBallsPerFrame,
                            int bonusRollsForStrike,
                            int bonusRollsForSpare) {
}
