package main;

import exception.KukiShinobuException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import command.Command;

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

    private final Storage storage;
    private TaskList tasks;

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
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KukiShinobuException e) {
            //TODO: Show more informative error message depending on the error that was thrown
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

    public boolean isExit() {
        return this.isExit;
    }

    public String generateWelcomeMessage() {
        return "Hey Traveller! I'm Kuki Shinobu, deputy leader of the Arataki Gang."
                + System.lineSeparator()
                + "Just let me know if you ever find yourself in a pinch. I can help you out.";
    }

    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            response = c.execute(tasks, storage);
        } catch (KukiShinobuException e) {
            response = e.getMessage();
        } finally {
            this.storage.write(this.tasks.getTasks());
        }

        return response;
    }
}
