package oyster;

import oyster.commands.Command;
import oyster.tasks.TaskList;
import oyster.utils.FileHandler;
import oyster.utils.Parser;

import java.io.File;
import java.util.Scanner;

public class LogicController {
    private static final Display display = new Display();
    private static boolean isRunning = false;
    private static boolean isAwaitingInput = false;

    private static Scanner inputScanner = new Scanner(System.in);

    private static TaskList taskList;

    public static void begin() {
        taskList = FileHandler.loadTaskList();

        display.output(new String[]{
                "Hello! I'm " + Oyster.CHATBOT_NAME,
                "What can I do for you?"
        });

        isRunning = true;

        awaitInput();
    }

    public static void close() {
        isRunning = false;
    }

    private static void awaitInput() {
        if (!isRunning) return;

        isAwaitingInput = true;

        if (!inputScanner.hasNext()) {
            display.output("Oops, please type something!");
            isAwaitingInput = false;
            awaitInput();
        }
        String input = inputScanner.nextLine();

        isAwaitingInput = false;

        Command command = Parser.parseCommand(input);
        command.execute();
        FileHandler.saveTaskList(taskList);
        display.output(command.getMessage());

        awaitInput();
    }

    public static TaskList getTaskList() {
        return taskList;
    }
}
