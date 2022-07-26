package com.exostaz.server.data;

import com.exostaz.server.data.db.MockDB;
import com.exostaz.server.domain.Configuration;

import java.util.List;
import java.util.Map;

public class ConfigurationDAO {

    public Configuration get(String id) {
        Map<String, Integer> config = MockDB.BOWLING_CONFIG.get(id);
        return new Configuration(
                config.get("numberOfFrames"),
                config.get("numberOfPins"),
                config.get("numberOfBallsPerFrame"),
                config.get("bonusBallsForStrike"),
                config.get("bonusBallsForSpare")
        );
    }

    public List<String> getAllGameTypes() {
        return MockDB.BOWLING_CONFIG.keySet().stream().toList();
    }
}
