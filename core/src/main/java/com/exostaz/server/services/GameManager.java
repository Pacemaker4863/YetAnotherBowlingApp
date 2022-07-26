package com.exostaz.server.services;

import com.exostaz.server.data.ConfigurationDAO;
import com.exostaz.server.domain.Configuration;
import com.exostaz.server.domain.Game;

import java.util.List;

public class GameManager {

    public static Configuration configuration;
    public static Game game;

    public static List<String> getAllGameTypes() {
        return new ConfigurationDAO().getAllGameTypes();
    }

    public static Configuration getGameConfiguration(String which) {
        configuration = new ConfigurationDAO().get(which);
        return configuration;
    }

    public static Game createGame(Configuration conf) {
        return (game = new Game(configuration));
    }

    public static boolean playAndValidates(int pins) {
        var gameSize = game.getFrames().size();
        var currentFrame = game.getFrames().get(gameSize - 1);
        System.out.println(currentFrame);
        game.play(pins);
        System.out.println(currentFrame);
        return currentFrame.isValid();
    }

    public static boolean isGameFinished() {
        return game.isFinished();
    }
}
