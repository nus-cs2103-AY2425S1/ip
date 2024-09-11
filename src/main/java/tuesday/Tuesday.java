package tuesday;

import tuesday.command.Command;
import tuesday.command.Parser;
import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.TuesdayException;
import tuesday.util.Ui;

/**
 * The chatbot class that the main code will run
 */
public class Tuesday {
    // variable
    private Storage storage;
    private Task tasks;
    private Ui ui;
    private String commandType;

    /**
     * Constructor for Tuesday
     *
     * @param filePath Filepath of the data file
     */
    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Add all the tasks in the data file to the Tasks class
            tasks = new Task(storage.load());
        } catch (TuesdayException e) {
            System.out.println(ui.showLoadingError());
            tasks = new Task(storage); // Create a new data file
        }
    }

    /**
     * Constructor for Tuesday
     * Used by the GUI
     */
    public Tuesday() {
        ui = new Ui();
        storage = new Storage("src/main/data/tuesday.txt");
        try {
            // Add all the tasks in the data file to the Tasks class
            tasks = new Task(storage.load());
        } catch (TuesdayException e) {
            tasks = new Task(storage); // Create a new data file
        }
    }

    /**
     * Main function
     */
    public void run() {
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TuesdayException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.commandType = c.getClass().getSimpleName();
            return c.getString();
        } catch (TuesdayException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String getCommandType() {
        assert this.commandType != null : "getResponse() should be invoked first";
        return this.commandType;
    }

    public static void main(String[] args) {
        new Tuesday("src/main/data/tuesday.txt").run();
    }
}
