package morgana;

import morgana.commands.Command;
import morgana.exceptions.MorganaException;
import morgana.parser.Parser;
import morgana.storage.Storage;
import morgana.task.TaskList;

/**
 * Core logic of the Morgana application.
 */
public class Morgana {
    private static final String DEFAULT_FILE_PATH = "./data/morgana.txt";

    private Storage storage;
    private TaskList tasks;

    private String styleClass;

    public Morgana() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Constructs a {@code Morgana} object with the specified file path for storage.
     * Initializes the {@link Storage} and {@link TaskList} objects, and loads the
     * tasks from the storage file.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Morgana(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (MorganaException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
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
