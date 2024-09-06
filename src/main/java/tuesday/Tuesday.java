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
            ui.showLoadingError();
            tasks = new Task(storage); // Create a new data file
        }
    }

    /**
     * Main function
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TuesdayException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Tuesday heard: " + input;
    }

    public static void main(String[] args) {
        new Tuesday("src/main/data/tuesday.txt").run();
    }
}
