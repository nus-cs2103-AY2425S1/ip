package winner;

import java.util.Scanner;

/**
 * Main class of the Winner task tracking bot.
 */
public class Winner {

    /**
     * Main entry point of the application.
     * Initialises the task list, handles loading and saving of tasks and starts the Winner bot loop.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage.checkAndCreateFile();
        Storage.loadTasks(taskList.getTasks());
        WinnerTaskBot(taskList);
        Storage.saveTasks(taskList.getTasks());
    }

    /**
     * Main loop of the Winner task tracking bot.
     * Uses a Scanner to continuously listen for user inputs, processes commands by parsing them
     * and interacts with the user.
     * Exits when the user inputs a command containing "bye".
     *
     * @param taskList TaskList object that manages the user's tasks.
     */
    static void WinnerTaskBot(TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        Ui.winnerSaysHi();

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