package max.main;

import max.exception.MaxException;
import max.task.TaskList;

/**
 * The Max class is the entry point of the application. It initializes the necessary components
 * and handles the main logic of the application.
 */
public class Max {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;
    private static final String FILE_PATH = "./data/max.txt";


    /**
     * Constructor for the Max class. Initializes the UI, storage, and task list.
     */
    public Max() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadList());

    }

    /**
     * Processes user input by parsing commands and interacting with the task list and storage.
     * It manages the application's main loop, handles commands, and returns the output to be displayed
     * through the user interface.
     *
     * @param text The user input text containing commands and data to be processed.
     * @return The response message generated after processing the input. If the "bye" command is issued,
     *         the method returns an empty string, indicating the termination of the application.
     */
    public String runMax(String text) {
        if (isExit) {
            return "";
        }

        Parser parser = new Parser(tasks, ui, storage);
        try {
            ui.resetOutput();
            isExit = parser.parseText(text);
        } catch (MaxException e) {
            return e.getMessage();
        }

        return ui.getOutput();

    }


}
