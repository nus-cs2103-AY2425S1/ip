package oyster;

import oyster.commands.Command;
import oyster.tasks.TaskList;
import oyster.utils.Storage;
import oyster.utils.Parser;

import java.util.Scanner;

public class LogicController {
    private static final Ui display = new Ui();
    private static boolean isRunning = false;
    private static boolean isAwaitingInput = false;

    private static Scanner inputScanner = new Scanner(System.in);

    private static TaskList taskList;

    /**
     * Begins the chatbot.
     */
    public static void begin() {
        try {
            taskList = Storage.loadTaskList();
        } catch (Exception e) {
            display.output(new String[]{
                    e.getMessage(),
                    "Save file deleted..."
            });
            taskList = new TaskList();
            Storage.saveTaskList(taskList);
        }

        display.output(new String[]{
                "Hello! I'm " + Oyster.CHATBOT_NAME,
                "What can I do for you?"
        });

        isRunning = true;

        awaitInput();
    }

    /**
     * Ends the chatbot.
     */
    public static void close() {
        isRunning = false;
    }

    private static void awaitInput() {
        if (!isRunning) return;

        isAwaitingInput = true;

        if (!inputScanner.hasNext()) {
            display.output("Oops, please type something!");
            isAwaitingInput = false;
        } else {
            String input = inputScanner.nextLine();
            isAwaitingInput = false;

            if (!input.trim().isEmpty()) {
                Command command = Parser.parseCommand(input);
                command.execute();
                Storage.saveTaskList(taskList);
                display.output(command.getMessage());
            }
        }

        awaitInput();
    }

    public static TaskList getTaskList() {
        return taskList;
    }
}
