package com.exostaz.data;

import com.exostaz.data.db.PseudoCacheDB;
import com.exostaz.domain.Configuration;

import java.util.List;
import java.util.Map;

public class ConfigurationDAO {

    public Configuration get(String id) {
        Map<String, Integer> config = PseudoCacheDB.BOWLING_CONFIG.get(id);

        return new Configuration(
                config.get("numberOfFrames"),
                config.get("numberOfSkittles"),
                config.get("numberOfBallsPerFrame"),
                config.get("bonusBallsForStrike"),
                config.get("bonusBallsForSpare")
        );
    }

    public List<String> getAllGameTypes() {
        return PseudoCacheDB.BOWLING_CONFIG.keySet().stream().toList();
    }
}
