package com.exostaz.data;

import com.exostaz.server.domain.Configuration;
import com.exostaz.server.data.ConfigurationDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationDAOTest {

    @DisplayName("Get configuration for an archaic bowling")
    @Test
    void getArchaic() {
        ConfigurationDAO dao = new ConfigurationDAO();
        Configuration rules = dao.get("Archaic");
        assertEquals(15, rules.numberOfPins());
        assertEquals(3, rules.numberOfBallsPerFrame());
        assertEquals(2, rules.bonusRollsForSpare());
        assertEquals(3, rules.bonusRollsForStrike());
        assertEquals(5, rules.numberOfFrames());
    }

    @DisplayName("Get configuration for a plain old classic bowling")
    @Test
    void getClassic() {
        ConfigurationDAO dao = new ConfigurationDAO();

        Configuration rules = dao.get("Classic");
        assertEquals(10, rules.numberOfPins());
        assertEquals(2, rules.numberOfBallsPerFrame());
        assertEquals(1, rules.bonusRollsForSpare());
        assertEquals(2, rules.bonusRollsForStrike());
        assertEquals(10, rules.numberOfFrames());
    }


    @DisplayName("Get Get the different types of bowling games")
    @Test
    void getGameConfigs() {
        ConfigurationDAO dao = new ConfigurationDAO();
        List<String> gameTypes = dao.getAllGameTypes();
        assertEquals(2, gameTypes.size());
        assertTrue(gameTypes.contains("Classic"));
        assertTrue(gameTypes.contains("Archaic"));
    }
}