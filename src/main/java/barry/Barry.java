package barry;

import barry.command.Command;
import barry.task.Task;
import barry.util.Parser;
import barry.util.Storage;
import barry.util.TaskList;
import barry.util.Ui;
import java.io.IOException;
import barry.Barry;

/**
 * The main class for the Clover chatbot
 */
public class Barry {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;

    /**
     * Constructs a Clover object and initialises the necessary components.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Barry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            allTasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            allTasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     * Reads the user input, parses it into commands and executes the command.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null : "command cannot be null!";

                Command c = Parser.parse(fullCommand);
                c.execute(allTasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    /**
     * Generate a welcome message when user opens the chatbot.
     */
    public String getWelcomeMessage() {
        return "bzzz~ I am Barry B Benson <3 " +
                "\n What can I do for you? " +
                "\n " +
                "\n Enter 'help' for more information~ ";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(allTasks, ui, storage);
            return ui.getLastMessage();
        } catch (IOException e) {
            return "Oops! An error occurred while executing the command.";
        } catch (Exception e) {
            return "Something went wrong: " + e.getMessage();
        }
    }

    /**
     * The main entry point for the Barry chatbot.
     * Creates a new Barry object and starts the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Barry("data/tasks.txt").run();

    }
}