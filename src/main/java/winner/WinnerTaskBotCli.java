package winner;

import java.util.Scanner;

/**
 * Provides the command-line interface for the Winner task tracking bot.
 * It continuously listens for user inputs, parses commands and interacts with the user.
 * Terminates when user enters a command containing "bye".
 */
public class WinnerTaskBotCli {

    /**
     * Represents the main loop of the Winner task tracking bot.
     * Uses a Scanner to continuously listen for user inputs, processes commands by parsing them
     * and interacts with the user.
     * Exits when the user inputs a command containing "bye".
     *
     * @param taskList TaskList object that manages the user's tasks.
     * */
    public static void winnerTaskBot(TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        Ui.applyTemplate(Ui.winnerSaysHi());

        while (true) {
            String input = scanner.nextLine();
            try {
                Parser.parseInput(input, taskList);
            } catch (WinnerException e) {
                Ui.applyTemplate(e.getMessage());
            }
            if (input.matches("(?i).*bye.*")) {
                break;
            }
        }
        scanner.close();
    }
}
