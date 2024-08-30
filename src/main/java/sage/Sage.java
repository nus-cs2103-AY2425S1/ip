package sage;

import sage.storage.Storage;
import sage.ui.Ui;
import sage.task.TaskList;
import sage.parser.Parser;
import sage.command.Command;
import sage.exception.SageException;

import java.util.Scanner;

/**
 * The main class for the Sage task management application
 * This class initializes the application components, manages user interactions,
 * and controls the overall flow of the application
 */
public class Sage {
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/sage.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a Sage instance with the specified file path
     * Initializes the UI, storage, parser, and task list.
     * Attempts to load tasks from the file; if unsuccessful, initializes with an empty task list
     *
     * @param filePath The path to the file where tasks are saved
     */
    public Sage(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showMessage("Unable to load tasks. Starting with an empty list");
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application
     * Continuously reads user commands, parses them into commands, and executes them.
     * Ends the loop when an exit command is received
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SageException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the application
     * Creates an instance of Sage and starts the application
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Sage chatbot = new Sage(FILE_PATH);
        chatbot.run();
    }
}
