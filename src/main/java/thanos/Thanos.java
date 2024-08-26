package thanos;

import java.util.Scanner;

import thanos.commands.Command;
import thanos.exceptions.InvalidCommandException;
import thanos.parser.Parser;
import thanos.storage.IStorage;
import thanos.storage.Storage;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

/**
 * The {@code Thanos} class is the main entry point for the Thanos application.
 * It handles the initialization of the necessary components such as storage,
 * task list, and user interface, and runs the main application loop.
 */
public class Thanos {
    /**
     * The {@code TaskList} that manages all the tasks.
     */
    private final TaskList taskList;

    /**
     * The {@code Ui} instance that handles user interactions.
     */
    private final Ui ui;

    /**
     * Constructs a new {@code Thanos} instance with the specified file name for data storage.
     *
     * @param fileName The name of the file to load and save tasks.
     */
    public Thanos(String fileName) {
        IStorage storage = new Storage(fileName);
        this.taskList = new TaskList(storage);
        this.ui = new Ui();
    }

    /**
     * The main method to start the Thanos application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Thanos("data.txt").run();
    }

    /**
     * Runs the main application loop, displaying the welcome message, accepting user input,
     * and executing commands until the exit command is received.
     */
    public void run() {
        this.ui.displayWelcome();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (InvalidCommandException e) {
                ui.display(e.getMessage() + "\n");
            }
        }
    }
}
