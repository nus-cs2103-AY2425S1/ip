package kobe;

import kobe.command.Command;
import kobe.task.TaskList;
import kobe.util.Parser;
import kobe.util.Storage;
import kobe.util.Ui;
import java.io.IOException;

/**
 * The main class of the Kobe chatbot application.
 * Handles the initialization of the application and provides response handling.
 */
public class Kobe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Kobe chatbot application with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Kobe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "TaskList should be initialized and not null after loading";
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and returns the chatbot's response.
     *
     * @param input The user's input command.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        if (input.equals("welcome")) {
            return ui.getWelcomeMessage();
        }
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return ui.getLatestResponse(); // Return the latest response set by the command
        } catch (KobeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
