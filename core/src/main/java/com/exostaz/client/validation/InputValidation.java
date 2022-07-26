package com.exostaz.client.validation;

import com.exostaz.client.ui.Display;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Scanner;

public class InputValidation {

    public static boolean ofGameType(List<String> gameTypes, String choice) {
        return StringUtils.isNumeric(choice) && NumberUtils.toInt(choice) < gameTypes.size() + 1 && NumberUtils.toInt(choice) >= 1;
    }

    public static int validateGameType(Scanner scanner, List<String> gameTypes) {
        String type = "";
        do {
            Display.ask("Enter your configuration: ", true, false);
            type = scanner.next();
        } while (!ofGameType(gameTypes, type));
        return NumberUtils.toInt(type);
    }

    public static boolean ofBall(String shoot, int numberOfPins) {
        return StringUtils.isNumeric(shoot) && NumberUtils.toInt(shoot) <= numberOfPins && NumberUtils.toInt(shoot) >= 0;
    }

    public static int validateShoot(Scanner scanner, int numberOfPins) {
        String shoot = "";
        do {
            Display.ask("Play: ", false, false);
            shoot = scanner.next();
        } while (!ofBall(shoot, numberOfPins));
        return NumberUtils.toInt(shoot);
    }
}
