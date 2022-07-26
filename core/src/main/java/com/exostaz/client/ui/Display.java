package com.exostaz.client.ui;

import com.exostaz.server.domain.Frame;
import com.exostaz.server.domain.Game;
import com.exostaz.server.services.GameManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

public class Display {

    public static void ask(String s, boolean newLineBefore, boolean newLineAfter) {
        if (newLineBefore) System.out.println();
        System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT);
        System.out.print(s);
        System.out.print(ConsoleColors.RESET);
        if (newLineAfter) System.out.println();
    }

    public static void displayGameOver(int score) {
        System.out.println();
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT);
        System.out.printf("THE GAME IS OVER AND YOUR FINAL SCORE IS %d%n", score);
        System.out.print(ConsoleColors.RESET);
    }

    public static void displayFrameStatus(int frameIndex, int ballIndex) {
        System.out.print(ConsoleColors.WHITE_UNDERLINED);
        System.out.printf("FRAME NUMBER %d (BALL %d)%n", frameIndex + 1, ballIndex + 1);
        System.out.print(ConsoleColors.RESET);
    }

    public static void displayFrameFinished(int score, int frameIndex) {
        System.out.println();
        System.out.print(ConsoleColors.BLUE_BOLD);
        System.out.printf("FRAME NUMBER %d IS FINISHED AND YOUR CURRENT SCORE IS %d%n", frameIndex + 1, score);
        System.out.print(ConsoleColors.RESET);
    }

    public static void displayNewFrameStarted(int frameIndex, int ballIndex) {
        if (ballIndex == 0) {
            if (frameIndex == 0) {
                System.out.println();
            }
            System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
            System.out.printf("FRAME NUMBER %d 'S JUST STARTED%n", frameIndex + 1);
            System.out.print(ConsoleColors.RESET);
        }
    }

    public static void displayFrameWhenItIsFinished(Frame currentFrame) {
        System.out.print(ConsoleColors.WHITE_BOLD);
        System.out.println(currentFrame);
        System.out.print(ConsoleColors.RESET);
        System.out.println();
    }

    public static void displayGame(Game game) {
        for (var f : game.getFrames()) {
            System.out.println(f);
        }
    }

    public static void displayTitle() {
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
    }

    @NotNull
    public static List<String> displayGameList() {
        List<String> gameTypes = GameManager.getAllGameTypes();
        IntStream.range(0, gameTypes.size()).forEach(i -> {
            System.out.printf("\t%d. %s%n", i + 1, gameTypes.get(i));
        });
        return gameTypes;
    }

    public static void goodChoice(String gameChosen) {
        System.out.println();
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT);
        System.out.printf("You've chosen the %s Bowling, LET'S START!!!!!!!!%n", gameChosen);
        System.out.print(ConsoleColors.RESET);
    }
}
