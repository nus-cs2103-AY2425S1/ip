package asura;

import asura.commands.Command;
import asura.data.exception.AsuraException;
import asura.data.tasks.TaskList;
import asura.parser.Parser;
import asura.storage.Storage;
import asura.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the main program.
 */
public class Asura {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String DEFAULT_FILE_PATH = "data/asura.txt";


    /**
     * Creates an Asura with the specified file path.
     * @param filepath The file path that the user wants to save their task data at.
     */
    public Asura(String filepath) {
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (AsuraException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (AsuraException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes the main loop
     * @param args Optional arguments passed to the program
     */
    public static void main(String[] args) {
    }

    public Asura() {
    }

}
