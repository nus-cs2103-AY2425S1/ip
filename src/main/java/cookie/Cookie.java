package cookie;

import java.io.FileNotFoundException;
import java.io.IOException;

import cookie.command.Command;
import cookie.exception.CookieException;
import cookie.parser.Parser;
import cookie.storage.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents the main application of the Cookie chatbot.
 */
public class Cookie {
    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private TaskList taskList;
    private final Parser parser = new Parser();

    /**
     * Constructs a new {@code Cookie} instance.
     * Initializes the user interface, storage, and parser, and loads the saved task list into {@code TaskList}.
     */
    public Cookie() {
        loadTaskList();
    }

    private void loadTaskList() {
        try {
            taskList = new TaskList(storage.loadFile(storage.fetchFile()));
        } catch (FileNotFoundException | CookieException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the logic when exiting the Cookie chatbot.
     * <p>
     * Saves the current tasks to a .txt file and returns an exit message.
     * </p>
     *
     * @return a {@code String} containing the exit message
     */
    public String handleQuit() {
        try {
            storage.saveFile(taskList.getTaskArrayList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ui.printQuit();
    }

    /**
     * Returns the greeting message displayed on startup.
     *
     * @return a {@code String} displaying the commands available and the initial greeting
     */
    public String getGreetingMessage() {
        return ui.printGreet();
    }

    /**
     * Processes user input and generates a response based on the parsed command and description.
     * Uses the parser to convert the input into a {@code Command} and then executes that command.
     *
     * @param input the user input string containing the command and optional description
     * @return a {@code String} response based on the parsed command and description,
     *         or an error message if the command is invalid
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseInputToCommand(input);
            String response = command.executeCommand(this.taskList, this.ui, this.storage);
            return response;

        } catch (CookieException e) {
            return e.getMessage();
        }
    }
}
