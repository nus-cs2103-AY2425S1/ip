package yoda;

import yoda.commands.Command;
import yoda.exceptions.YodaException;

/**
 * Main class for the Yoda application, which coordinates the user interface,
 * task management, and data storage.
 */
public class Yoda {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Yoda application with the specified file path for storage.
     * Sets up the user interface, storage, and attempts to load tasks from the file.
     * If loading tasks fails, initializes an empty task list and displays an error message.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Yoda(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (YodaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Yoda application, handling user input and executing commands.
     * Displays a welcome message, processes user input in a loop, and performs
     * actions based on the commands received. Saves the task list to storage after
     * each command execution.
     */
    public void run() {
        String welcomeMessage = "Hello! For you, what can I do?";
        Parser parser = new Parser();

        ui.printLine();
        System.out.println(welcomeMessage);
        ui.printLine();

        while (true) {
            String input = ui.getNextLine();
            try {
                Command command = parser.handle(input, tasks);
                ui.printLine();
                command.run();
                ui.printLine();
                storage.saveTasks(tasks.getTasks());
            } catch (YodaException e) {
                ui.printLine();
                System.out.println(e.getMessage());
                ui.printLine();
            }
        }
    }

    /**
     * Entry point for the Yoda application.
     * Creates a new Yoda instance with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Yoda("data/tasks.txt").run();
    }

}
