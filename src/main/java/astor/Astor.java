package astor;

import astor.command.Command;
import astor.exception.*;

import java.io.IOException;

/**
 * Represents the main application for managing tasks.
 *
 * The Astor class handles the application's core functionality, including
 * initializing the user interface, managing task data, and processing commands.
 */
public class Astor {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String DESTINATION_STORAGE = "./src/main/data/astor.Astor.txt";


    /**
     * Constructs an Astor object with the specified file path for data storage.
     *
     * @param filePath the path to the file where task data is stored
     */
    public Astor(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.getData());
    }

    /**
     * Starts and runs the application.
     *
     * This method displays a welcome message, continuously reads user commands,
     * processes them, and executes the corresponding actions. It handles exceptions
     * and updates the user interface as necessary. The application continues to run
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.process(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (AstorException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while writing to the file.");
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.process(input);
            output = c.execute(taskList, ui, storage);
        } catch (AstorException e) {
            output = e.getMessage();
        } catch (IOException e) {
            output = "An error occurred while writing to the file.";
        }
        return output;
    }

    /**
     * The entry point of the application.
     *
     * This method creates an instance of Astor with the default storage file path
     * and starts the application by calling the run() method.
     *
     * @param args command-line arguments
     */

    public static void main(String[] args) {
        Astor astor = new Astor(Astor.DESTINATION_STORAGE);
        astor.run();
    }
}
