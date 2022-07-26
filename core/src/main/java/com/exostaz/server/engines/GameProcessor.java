package com.exostaz.server.engines;

import com.exostaz.server.domain.Game;

public class GameProcessor {

    private static Game game;

    private GameProcessor() {

    }

    public static GameProcessor getInstance() {
        return new GameProcessor();
    }


}
