package wiggly;

import java.io.FileNotFoundException;

import wiggly.commands.Command;
import wiggly.exception.WigglyException;
import wiggly.parser.Parser;
import wiggly.task.TaskList;
import wiggly.util.Storage;
import wiggly.util.Ui;

/**
 * Represents the main class of Wiggly
 */
public class Wiggly {
    private static final String DEFAULT_FILE_PATH = "./data/Wiggly.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String commandType;

    // Overloaded constructor
    public Wiggly() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Creates a new instance of Wiggly
     *
     * @param filePath The filePath of the textfile to load
     */
    public Wiggly(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (WigglyException | FileNotFoundException e) {
            ui.printWrappedString("Cannot load storage from " + filePath);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Wiggly instance
     */
    public void run() {

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (WigglyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return response;
        } catch (WigglyException e) {
            return "Error: " + e.getMessage();
        }
    }
    public String getCommandType() {
        return commandType;
    }

    public static void main(String[] args) {
        new Wiggly("./data/Wiggly.txt").run();
    }

}
