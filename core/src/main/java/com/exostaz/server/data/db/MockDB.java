package com.exostaz.server.data.db;

import java.util.HashMap;
import java.util.Map;

public class MockDB {
    private static final Map<String, Integer> archaic = new HashMap<>() {{
        put("numberOfFrames", 5);
        put("numberOfPins", 15);
        put("numberOfBallsPerFrame", 3);
        put("bonusBallsForStrike", 3);
        put("bonusBallsForSpare", 2);
    }};
    private static final Map<String, Integer> classic = new HashMap<>() {{
        put("numberOfFrames", 10);
        put("numberOfPins", 10);
        put("numberOfBallsPerFrame", 2);
        put("bonusBallsForStrike", 2);
        put("bonusBallsForSpare", 1);
    }};
    public static final Map<String, Map<String, Integer>> BOWLING_CONFIG = new HashMap<>() {{
        put("Archaic", archaic);
        put("Classic", classic);
    }};
}


