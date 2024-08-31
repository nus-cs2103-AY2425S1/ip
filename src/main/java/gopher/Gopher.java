package gopher;

import gopher.parser.Parser;
import gopher.storage.TaskManager;
import gopher.task.Task;
import gopher.task.TaskList;
import gopher.ui.UI;

import java.util.Scanner;

/**
 * Represents the chatbot Gopher.
 */
public class Gopher {
    /** Scanner which takes in user input during main event loop */
    private static Scanner inputReader;

    /** TaskList object used by Gopher to track user tasks */
    private static TaskList taskList;

    /**
     * Initialize the Scanner, TaskManager and TaskList
     * before user interaction.
     */
    private static void initialize() {
        inputReader = new Scanner(System.in);
        TaskManager.initialize();
        taskList = new TaskList(TaskManager.loadTasks());
    }

    /**
     * Start the main event loop.
     */
    private static void run() {
        UI.printGreetMessage();

        // Main event loop
        String userInput = "";
        boolean isRunning = true;
        while (isRunning) {
            userInput = inputReader.nextLine();

            UI.printHorizontalSeparator();
            System.out.println();

            if (userInput.equalsIgnoreCase("bye")) {
                UI.printExitMessage();
                isRunning = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                UI.printTaskList(taskList);
            } else if (userInput.toLowerCase().startsWith("mark")) {
                int taskNumber = Parser.parseMarkCommand(userInput);
                taskList.markAsDone(taskNumber);
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                int taskNumber = Parser.parseUnMarkCommand(userInput);
                taskList.markAsUndone(taskNumber);
            } else if (userInput.toLowerCase().startsWith("delete")) {
                int taskNumber = Parser.parseDeleteCommand(userInput);
                taskList.delete(taskNumber);
            } else {
                Task task = Parser.parseCreateTaskCommand(userInput);
                if (task != null) {
                    taskList.add(task);
                }
            }

            System.out.println();
            UI.printHorizontalSeparator();
        };
    }

    public static void main(String[] args) {
        initialize();
        run();
    }
}
