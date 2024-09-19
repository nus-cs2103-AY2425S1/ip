package jeriel;

import jeriel.command.*;
import jeriel.util.*;
import java.io.IOException;

public class Jeriel {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeriel(String filePath) {
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
     * Handles a single user command and returns the response.
     * This replaces the run loop for the GUI implementation.
     *
     * @param fullCommand the user input
     * @return the result of the command execution
     */
    public String handleCommand(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            return command.execute(tasks, ui, storage);
        } catch (JerielException | IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        // If you need a CLI fallback, you can implement that here.
        // For now, it's not needed for the GUI application.
        // new Jeriel("data/tasks.txt").run();
    }
}
