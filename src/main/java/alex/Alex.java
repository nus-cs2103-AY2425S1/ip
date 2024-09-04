package alex;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import alex.command.Command;
import alex.task.Pair;

/**
 * Represents the chatbot Alex
 */
public class Alex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Alex(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (AlexException | FileNotFoundException | DateTimeParseException e) {
            ui.showError(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public Pair<String, String> getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(this.tasks, this.ui, this.storage);
            String command = c.getCommandType();
            return new Pair<>(response, command);
        } catch (AlexException | IOException e) {
            return new Pair<>(ui.showError(e), "Error");
        }
    }
}
