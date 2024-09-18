package skywalker;

import java.io.IOException;

import skywalker.command.Command;
import skywalker.parser.Parser;
import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

/**
 * The Skywalker class represents the main application that runs the chatbot.
 */
public class Skywalker {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Skywalker instance with the specified file path for storing tasks.
     *
     * @param filePath the file path to the storage file
     */
    public Skywalker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, handling user input and executing commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
    }



    /**
     * The main method that launches the Skywalker application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Skywalker("./data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);   // Get the command object from the parser
            return command.execute(tasks, ui, storage);  // Execute the command and return the response
        } catch (Exception e) {
            return "I'm sorry, I don't understand that command.";
        }
    }
}
