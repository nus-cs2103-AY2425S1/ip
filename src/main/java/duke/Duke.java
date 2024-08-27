package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the main application class for Duke, which handles the initialization,
 * user interactions, and task management.
 * It connects the user interface, task storage, and command parsing components.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes the Duke application with the specified file path for task storage.
     * Sets up the user interface, storage, task list, and parser.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    /**
     * Starts the Duke application, displaying the greeting message and continuously
     * processing user input until the "bye" command is received.
     * Saves the tasks to the storage after each command is processed.
     */
    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            parser.parse(input);
            try {
                storage.save(tasks.getTasks());
            } catch (IOException e) {
                ui.showSaveError(e.getMessage());
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Entry point of the application.
     * Creates an instance of Duke with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}



