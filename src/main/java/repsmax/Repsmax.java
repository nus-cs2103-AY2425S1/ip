package repsmax;

import javafx.application.Application;

/**
 * The main class of the Repsmax application.
 * This class initializes the necessary components and manages the flow of the program.
 */
public class Repsmax {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes the Repsmax application.
     *
     * @param filePath The file path where task data is stored.
     */
    public Repsmax(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        try {
            storage.load(tasks);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public String getResponse(String userInput) {
        assert userInput != null : "User input cannot be null";
        if (userInput.equals("bye")) {
            storage.save(tasks);
            return ui.showGoodbye();
        } else {
            return parser.parse(userInput, tasks, ui, storage);
        }
    }

    public void parse(String userInput) {
        assert userInput != null : "User input cannot be null";
        parser.parse(userInput, tasks, ui, storage);
    }

}

