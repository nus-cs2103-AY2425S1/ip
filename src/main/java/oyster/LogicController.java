package oyster;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import oyster.commands.Command;
import oyster.tasks.TaskList;
import oyster.utils.Parser;
import oyster.utils.Storage;

/**
 * Handles logic of the chatbot.
 */
public class LogicController {
    private static boolean isRunning = false;

    private static TaskList taskList;

    /**
     * Begins the logic.
     */
    public static void begin() {
        try {
            taskList = Storage.loadTaskList();
        } catch (Exception e) {
            taskList = new TaskList();
            Storage.saveTaskList(taskList);
        }

        isRunning = true;
    }

    /**
     * Ends the chatbot.
     */
    public static void close() {
        isRunning = false;

        // Closes the application window after a delay
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }

    /**
     * Computes a given input String and runs it.
     *
     * @param rawInput The input to supply.
     * @return Array of String response message.
     */
    public static String[] readInput(String rawInput) {
        if (!isRunning) {
            return null;
        }

        Scanner inputScanner = new Scanner(rawInput);

        if (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();

            if (!input.trim().isEmpty()) {
                Command command = Parser.parseCommand(input);
                command.execute();
                Storage.saveTaskList(taskList);
                return command.getMessage();
            }
        }

        return new String[] {"Oops, please type something!"};
    }

    public static TaskList getTaskList() {
        return taskList;
    }
}
