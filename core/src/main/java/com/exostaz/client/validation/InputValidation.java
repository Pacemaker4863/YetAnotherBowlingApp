package com.exostaz.client.validation;

import com.exostaz.client.ui.ConsoleColors;
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
        String type;
        do {
            Display.ask("Enter your configuration: ", true, false);
            type = scanner.next();
            if (!ofGameType(gameTypes, type)) {
                System.err.print("wrong Entry, ");
            } else break;
        } while (true);
        return NumberUtils.toInt(type);
    }

    public static boolean ofBall(String shoot, int numberOfPins) {
        return StringUtils.isNumeric(shoot) && NumberUtils.toInt(shoot) <= numberOfPins && NumberUtils.toInt(shoot) >= 0;
    }

    public static int validateShoot(Scanner scanner, int numberOfPins) {
        String shoot;
        String error = null;
        do {
            String play = error != null ? String.format("%s%s, Please Play again: %s", ConsoleColors.RED_BOLD, error, ConsoleColors.RESET) : "Play: ";
            Display.ask(play, false, false);
            shoot = scanner.next();
            if ((!ofBall(shoot, numberOfPins))) {
                error = "Wrong entry";
            } else break;
        } while (true);
        return NumberUtils.toInt(shoot);
    }
}
