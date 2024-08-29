package matcha;

import matcha.command.Command;
import matcha.exception.MatchaException;
import matcha.parser.Parser;
import matcha.storage.Storage;
import matcha.tasklist.TaskList;
import matcha.ui.Ui;

/**
 * Represents the main class of the Matcha program.
 */
public class Matcha {
    private static final String FILE_PATH = "./data/matcha.txt";
    private static boolean isExit = false;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    /**
     * Constructor for Matcha class.
     */
    public Matcha() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (MatchaException e) {
            tasks = new TaskList();
        }
    }
    /**
     * Returns the response of Matcha based on the user input.
     *
     * @param input User input to Matcha via GUI.
     * @return Response of Matcha based on user input.
     */
    public String getResponse(String input) {
        try {
            if (input.isEmpty()) {
                return "Please enter a command.";
            }
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (MatchaException e) {
            return e.toString();
        }
    }
    /**
     * Returns the welcome message of Matcha.
     *
     * @return Welcome message of Matcha.
     */
    public String getWelcomeMessage() {
        return "Hi there! I am Matcha, your personal chatbot.\nHow can I help you today?";
    }
    /**
     * Returns the exit status of Matcha.
     *
     * @return Exit status of Matcha.
     */
    public boolean hasExit() {
        return isExit;
    }
    /**
     * Sets the exit status of Matcha.
     *
     * @param exit Exit status of Matcha.
     */
    public static void setExit(boolean exit) {
        isExit = exit;
    }
}
