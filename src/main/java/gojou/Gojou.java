package gojou;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import gojou.command.Command;
import gojou.task.TaskList;


/**
 * Represents the chatbot Gojou which handles user interactions, processes commands, and manages tasks.
 */
public class Gojou {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of the Gojou chatbot.
     * Initializes the user interface, storage system, and task list.
     * Loads existing tasks from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Gojou(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public void loadTasksFromFile() throws GojouException {
        try {
            this.tasks = new TaskList(storage.load());
        } catch (GojouException | DateTimeParseException | IOException e) {
            throw new GojouException(e.getMessage());
        }
    }

    /**
     * Processes the user's input and generates a response based on the command.
     * Executes the command and returns a pair containing the response and the command type.
     *
     * @param input The user's chat message.
     * @return A pair where the first element is the response to the user's input and the second element is the command
     *     type.
     */
    public Pair<String, String> getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(this.tasks, this.ui, this.storage);
            String command = c.getCommandType();
            return new Pair<>(response, command);
        } catch (GojouException | IOException e) {
            return new Pair<>(ui.showError(e), "Error");
        }
    }
}
