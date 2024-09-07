package chatbuddy;

import chatbuddy.command.Command;
import chatbuddy.exception.ChatBuddyException;
import chatbuddy.parser.Parser;
import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * The main class for the ChatBuddy program.
 * Handles the initialization of UI, storage, and task list components, and runs the chatbot.
 */
public class ChatBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new ChatBuddy instance with a specified file path for task storage.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public ChatBuddy(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        ui.showWelcome();

        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBuddyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user input and returns the response generated by the chatbot.
     *
     * @param input The input provided by the user.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        ui.clearOutput();
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;

        } catch (ChatBuddyException e) {
            ui.showError(e.getMessage());
            return ui.getOutput();
        }
    }

    /**
     * Retrieves the welcome message displayed by the chatbot.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getOutput();
    }
}
