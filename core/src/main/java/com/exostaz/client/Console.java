package com.exostaz.client;

import com.exostaz.client.validation.InputValidation;
import com.exostaz.server.domain.Configuration;
import com.exostaz.server.domain.Game;
import com.exostaz.server.services.GameManager;
import com.exostaz.client.ui.Display;

import java.util.List;
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        Display.displayTitle();

        Scanner scanner = new Scanner(System.in);

        List<String> gameTypes = Display.displayGameList();

        Display.ask(String.format("Please choose your game (Enter 1 to %d): ", gameTypes.size()), true, true);

        int type = InputValidation.validateGameType(scanner, gameTypes);
        String gameChosen = gameTypes.get(type - 1);
        Configuration config = GameManager.getGameConfiguration(gameChosen);

        Display.goodChoice(gameChosen);

        Game game = GameManager.createGame(config);

        var frameIndex = 0;
        var ballIndex = 0;
        while (!GameManager.isGameFinished()) {
            var currentFrame = game.getFrames().get(frameIndex);
            Display.displayNewFrameStarted(frameIndex, ballIndex);

            Display.displayFrameStatus(frameIndex,ballIndex);
            // minimal client validation
            int shoot = InputValidation.validateShoot(scanner, config.numberOfPins());
            // todo pseudo server validation
            GameManager.playAndValidates(shoot);
            ballIndex++;

            if (currentFrame.isFinished()) {
                if (frameIndex <= config.numberOfFrames() - 2) {
                    Display.displayFrameFinished(game.calculateScore(), frameIndex);
                    Display.displayFrameWhenItIsFinished(currentFrame);
                    frameIndex++;
                    ballIndex = 0;
                }
            }
        }
        Display.displayGameOver(game.calculateScore());
        Display.displayGame(game);
    }
}
