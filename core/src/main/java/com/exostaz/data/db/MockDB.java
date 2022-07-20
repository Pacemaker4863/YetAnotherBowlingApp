package com.exostaz.data.db;

import java.util.HashMap;
import java.util.Map;

public class MockDB {

  private static final Map<String, Integer> archaic = new HashMap<String, Integer>() {{
    put("numberOfFrames", 5);
    put("numberOfSkittles", 15);
    put("numberOfBallsPerFrame", 3);
    put("bonusBallsForStrike", 3);
    put("bonusBallsForSpare", 2);
  }};
  private static final Map<String, Integer> classic = new HashMap<String, Integer>() {{
    put("numberOfFrames", 10);
    put("numberOfSkittles", 10);
    put("numberOfBallsPerFrame", 2);
    put("bonusBallsForStrike", 2);
    put("bonusBallsForSpare", 1);
  }};
  public static final Map<String, Map<String, Integer>> BOWLING_CONFIG = new HashMap<String, Map<String, Integer>>() {{
    put("archaic", archaic);
    put("classic", classic);
  }};

}


