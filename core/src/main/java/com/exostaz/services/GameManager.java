package com.exostaz.services;

import com.exostaz.data.ConfigurationDAO;
import com.exostaz.domain.Configuration;

import java.util.List;

public class GameManager {

    public static List<String> getAllGameTypes() {
        return new ConfigurationDAO().getAllGameTypes();
    }

    public static Configuration getGameConfiguration(String which) {
        return new ConfigurationDAO().get(which);
    }

}
