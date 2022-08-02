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

    public static void playAndValidates(int pins) {
        game.play(pins);
    }

    public static boolean isGameFinished() {
        return game.isFinished();
    }
}
