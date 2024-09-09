package dook;

import java.io.FileNotFoundException;
import java.io.IOException;

import dook.commands.Command;
import dook.parser.Parser;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The main entry point for the Dook application.
 * Manages the overall flow of the application, including user interaction and task management.
 */
public class Dook {

    private static Ui ui = new Ui();
    private static Storage storage = new Storage("data/Tasks.txt");
    private static TaskList tasks;
    private static Parser parser = new Parser();

    /**
     * The main method that runs the Dook application.
     * Initializes the storage, loads tasks, and starts the user interface loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            storage.setup();
            tasks = new TaskList(storage.load());
            ui.greet();

            boolean isExit = false;
            while (!isExit) {
                String input = ui.readCommand();

                try {
                    Command command = parser.parse(input);
                    command.execute(tasks, ui, storage);
                    isExit = command.isExit();
                } catch (DookException e) {
                    ui.errorMessage(e.getMessage());
                } catch (IOException e) {
                    ui.errorMessage(e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found");
        } catch (IOException e) {
            ui.errorMessage("IO Error");
        }
    }

    /**
     * Initializes the Dook application.
     * Sets up storage, loads tasks, and greets the user.
     */
    public void initialize() {
        try {
            storage.setup();
            tasks = new TaskList(storage.load());
            ui.greet();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input The user input string.
     * @return The response from executing the command parsed from the input.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input).execute(tasks, ui, storage);
        } catch (DookException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Greets the user.
     *
     * @return The greeting message from the UI.
     */
    public String greet() {
        return ui.greet();
    }
}
