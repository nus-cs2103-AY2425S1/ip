package gavinchatbot;

import java.io.IOException;

import gavinchatbot.command.Command;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.GavinException;
import gavinchatbot.util.Parser;
import gavinchatbot.util.Storage;
import gavinchatbot.util.Ui;

/**
 * The main class for Gavin's Chat Bot application.
 * This class handles the initialization of the application and the main logic for running the chatbot.
 */
public class GavinChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a GavinChatBot object and initializes the necessary components.
     *
     * @param filePath The file path where the tasks will be stored and loaded from.
     */
    public GavinChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, showing a welcome message and processing user commands until the exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GavinException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method that starts the GavinChatBot application.
     *
     * @param args Command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        new GavinChatBot("data/duke.txt").run();
    }
}

