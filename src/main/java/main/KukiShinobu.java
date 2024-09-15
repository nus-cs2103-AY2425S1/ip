package main;

import exception.KukiShinobuException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import command.Command;
import ui.Ui;

import java.time.temporal.TemporalUnit;

/**
 * The main class for the Kuki Shinobu application.
 * <p>
 * The {@code KukiShinobu} class initializes the application, manages the main program flow,
 * and handles user commands. It sets up the user interface, task storage, and task list,
 * and processes commands entered by the user.
 * </p>
 * <p>
 * The application reads commands from the user, executes them, and updates the task list
 * and storage accordingly. It continues to run until an exit command is received.
 * </p>
 */
public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    // IMPORTANT: Relative Filepath Specified must always be relative to root directory of the entire project
    private static final String FILE_PATH = "./data/database.txt";

    private boolean isExit = false;

    /**
     * The entry point of the Kuki Shinobu application.
     * <p>
     * Initializes the application with the specified file path for storage and starts
     * listening for user commands.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        KukiShinobu kukiShinobu = new KukiShinobu(FILE_PATH);
        kukiShinobu.listen();
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code KukiShinobu} instance with the specified file path for storage.
     * <p>
     * Initializes the user interface, storage, and task list. If an error occurs during
     * loading the tasks, a default empty task list is created, and an error message is displayed.
     * </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public KukiShinobu(String filePath) {
        // TODO: Create constructor that takes in a filePath and then self instantiates everything else needed
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KukiShinobuException e) {
            //TODO: Show more informative error message depending on the error that was thrown
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public KukiShinobu() {
        this(KukiShinobu.FILE_PATH);
    }

    /**
     * Converts an integer to a boolean.
     *
     * @param i The integer to convert.
     * @return True if the integer is non-zero, otherwise false.
     */

    public static boolean readBoolean(int i) {
        return i != 0;
    }

    /**
     * Starts the command listening loop for the application.
     * <p>
     * Displays a welcome message, reads and executes user commands in a loop,
     * and updates the task list and storage. The loop continues until an exit
     * command is received. Displays a goodbye message when the application exits.
     * </p>
     */
    public void listen() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KukiShinobuException e) {
                this.ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                this.storage.write(this.tasks.getTasks());
            }
        }
        ui.showGoodbye();
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String generateWelcomeMessage() {
        return "Hey Traveller! I'm Kuki Shinobu, deputy leader of the Arataki Gang."
                + System.lineSeparator()
                + "Just let me know if you ever find yourself in a pinch. I can help you out.";
    }

    public String getResponse(String input) {
        //TODO: Update this code to actually query for the response\
        String response;
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            response = c.execute(tasks, ui, storage);
        } catch (KukiShinobuException e) {
            response = e.getMessage();
        } finally {
            ui.showLine();
            this.storage.write(this.tasks.getTasks());
        }

        return response;
    }
}
