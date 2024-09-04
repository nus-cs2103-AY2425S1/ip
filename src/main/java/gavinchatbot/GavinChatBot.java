package gavinchatbot;

import java.io.IOException;

import gavinchatbot.command.Command;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Parser;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;
import javafx.scene.layout.VBox;

/**
 * Represents the main chatbot class.
 */
public class GavinChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new GavinChatBot.
     *
     * @param filePath The file path where the tasks are stored.
     * @param dialogContainer The VBox to be used in the UI.
     */
    public GavinChatBot(String filePath, VBox dialogContainer) {
        ui = new Ui(dialogContainer);
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
            ui.showLoadingError("Failed to load tasks from file: " + e.getMessage());
        }
    }

    /**
     * Processes the input from the user and returns the response from the chatbot.
     *
     * @param input The user's input.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command command = new Parser().parse(input);
            return command.execute(tasks, ui, storage);
        } catch (GavinException | IOException e) {
            return ui.showError("Error: " + e.getMessage());
        }
    }
}
