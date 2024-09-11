package edith;

import edith.command.Command;
import edith.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents the main class for the EDITH chatbot application.
 * <p>
 * The Edith class is responsible for initializing and running the chatbot. It handles user interactions,
 * processes commands, and manages the task list. The application uses the classes Ui for user interface operations,
 * Storage for data persistence, TaskList for managing tasks, and Parser for interpreting user commands.
 * </p>
 */
public class Edith {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs an Edith instance with the specified file path for storage.
     * <p>
     * Initializes the user interface, storage, task list, and command parser. Attempts to load the existing task list
     * from the specified file. If an error occurs during loading, it initializes with an empty task list.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Edith(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (EdithException e) {
            System.err.println(e.getMessage() + " Starting with an empty list.");
            tasks = new TaskList();
        } catch (IOException e) {
            System.err.println("An error occurred while loading saved Edith.task list. Starting with an empty list.");
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    /**
     * Starts the EDITH chatbot application.
     * <p>
     * Displays a greeting message, then enters a loop to continuously prompt the user for input, parse and execute
     * commands. The loop terminates when an ExitCommand is executed. Handles any exceptions that occur during
     * command execution or date/time parsing.
     * </p>
     */
    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            try {
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.err.println(ui.invalidDateTimeError());
            } catch (EdithException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public String reply(String userInput) {
        StringBuilder response = new StringBuilder();

        try {
            Command command = parser.parse(userInput);
            response.append(command.execute(tasks, ui, storage));
            if (command.isExit()) {
                // add logic to exit the application
            }
        } catch (DateTimeParseException e) {
            response.append(ui.invalidDateTimeError());
        } catch (EdithException e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }
}