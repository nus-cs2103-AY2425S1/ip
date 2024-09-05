package morgana;

import morgana.commands.ByeCommand;
import morgana.commands.Command;
import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Entry point of the Morgana application.
 * Initializes the application and starts the interaction with the user.
 */
public class Morgana {
    private static final String NAME = "Morgana";
    private static final String DEFAULT_FILE_PATH = "./data/morgana.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String styleClass;

    public Morgana() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Constructs a {@code Morgana} object with the specified file path for storage.
     * Initializes the {@link Ui}, {@link Storage}, and {@link TaskList} objects,
     * and loads the tasks from the storage file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Morgana(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (MorganaException e) {
            ui.showToUser(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application, which continuously reads user input,
     * parses it into a command, and executes the command. The loop runs until the
     * user issues a {@link ByeCommand}.
     */
    public void run() {
        ui.showToUser("Hello! I'm %s.".formatted(NAME), "What can I do for you?");
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command command = Parser.parse(input);
                ui.showToUser(command.execute(tasks, storage));
                isExit = command.isExit();
            } catch (MorganaException e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Morgana(DEFAULT_FILE_PATH).run();
    }

    /**
     * Processes the user input and returns the corresponding feedback message.
     *
     * @param input The input string entered by the user.
     * @return The feedback message to be displayed after the command is executed.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, storage);
            styleClass = command.getStyleClass();
            return response;
        } catch (MorganaException e) {
            styleClass = "error-label";
            return e.getMessage();
        }
    }

    public String getStyleClass() {
        return styleClass;
    }
}
