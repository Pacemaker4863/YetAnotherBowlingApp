package com.exostaz;

import com.exostaz.domain.Configuration;
import com.exostaz.domain.Game;
import com.exostaz.services.GameManager;
import com.exostaz.util.ConsoleColors;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleUI {

    public static void main(String[] args) {
        System.out.println();
        String title = """
                                
                 /$$$$$$$                          /$$ /$$                            /$$$$$$                    \s
                | $$__  $$                        | $$|__/                           /$$__  $$                   \s
                | $$  \\ $$  /$$$$$$  /$$  /$$  /$$| $$ /$$ /$$$$$$$   /$$$$$$       | $$  \\ $$  /$$$$$$   /$$$$$$\s
                | $$$$$$$  /$$__  $$| $$ | $$ | $$| $$| $$| $$__  $$ /$$__  $$      | $$$$$$$$ /$$__  $$ /$$__  $$
                | $$__  $$| $$  \\ $$| $$ | $$ | $$| $$| $$| $$  \\ $$| $$  \\ $$      | $$__  $$| $$  \\ $$| $$  \\ $$
                | $$  \\ $$| $$  | $$| $$ | $$ | $$| $$| $$| $$  | $$| $$  | $$      | $$  | $$| $$  | $$| $$  | $$
                | $$$$$$$/|  $$$$$$/|  $$$$$/$$$$/| $$| $$| $$  | $$|  $$$$$$$      | $$  | $$| $$$$$$$/| $$$$$$$/
                |_______/  \\______/  \\_____/\\___/ |__/|__/|__/  |__/ \\____  $$      |__/  |__/| $$____/ | $$____/\s
                                                                     /$$  \\ $$                | $$      | $$     \s
                                                                    |  $$$$$$/                | $$      | $$     \s
                                                                     \\______/                 |__/      |__/     \s
                """;

        System.out.print(ConsoleColors.PURPLE_BOLD);
        System.out.println(title);
        System.out.print(ConsoleColors.RESET);

        Scanner scanner = new Scanner(System.in);
        System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT);
        System.out.println("Please choose your game (press 1 or 2): ");
        System.out.print(ConsoleColors.RESET);
        System.out.println();
        List<String> gameTypes = GameManager.getAllGameTypes();
        IntStream.range(0, gameTypes.size()).forEach(i -> {
            System.out.printf("\t%d. %s%n", i + 1, gameTypes.get(i));
        });
        System.out.println();
        System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT);
        System.out.print("Enter your configuration: ");
        System.out.print(ConsoleColors.RESET);
        var type = scanner.nextInt();
        Configuration config = GameManager.getGameConfiguration(gameTypes.get(type - 1));
        Game game = new Game(config);

        var frameIndex = 0;
        var ballIndex = 0;
        while (!game.isFinished()) {
            var currentFrame = game.getFrames().get(frameIndex);
            System.out.println(ConsoleColors.WHITE_UNDERLINED);
            System.out.printf("FRAME NUMBER %d (SHOOT %d)%n", frameIndex + 1, ballIndex + 1);
            System.out.print(ConsoleColors.RESET);

            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT);
            System.out.print("Play: ");
            System.out.print(ConsoleColors.RESET);

            int shoot = scanner.nextInt();
            game.play(shoot);
            ballIndex++;

            if (currentFrame.isFinished()) {
                if (frameIndex <= config.numberOfFrames() - 2) {
                    frameIndex++;
                    System.out.println();
                    System.out.print(ConsoleColors.BLUE_BOLD);
                    System.out.printf("FRAME NUMBER %d IS FINISHED AND YOUR CURRENT SCORE IS %d%n", frameIndex + 1, game.calculateScore());
                    System.out.println(ConsoleColors.RESET);
                    System.out.println(currentFrame);
                    ballIndex = 0;
                }
            }
        }

        System.out.println();
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT);
        System.out.printf("THE GAME IS OVER AND YOUR FINAL SCORE IS %d%n", game.calculateScore());
        System.out.println(ConsoleColors.RESET);
        for (var f : game.getFrames()) {
            System.out.println(f);
        }
    }
}
