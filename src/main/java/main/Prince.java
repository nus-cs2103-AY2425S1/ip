package main;

import java.nio.file.Path;
import java.nio.file.Paths;

import main.command.Command;
import main.exceptions.PrinceException;
import main.tasks.TaskList;
import main.util.Parser;
import main.util.Storage;
import main.util.Ui;

/**
 * Prince is a chatbot that interacts with users.
 */
public class Prince {
    private static final Path FILE_PATH = Paths.get("data", "storage.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for Prince.
     * @param filePath Path that represents a relative path to the storage file.
     */
    public Prince() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (PrinceException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Prince.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (PrinceException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Return a string containing Prince's response to the user's input
     * @param input Input by the user.
     * @return Prince's response to the user.
     */
    public String getResponse(String input) {
        return "Prince heard: " + input;      
    }
}
