package fridayproject;

import java.io.IOException;

/**
 * Represents the main class of the program.
 * Friday is a chatbot that helps you manage your tasks.
 */
public class Friday {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Friday class.
     * @param filePathString The file path to store the tasks.
     */
    public Friday(String filePathString) {
        ui = new Ui();
        storage = new Storage(filePathString);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.displayLoadingError();
            tasks = new TaskList();
        }

        // Assertions to ensure that the task list and storage are initialized properly
        assert tasks != null : "Task list should be initialized";
        assert storage != null : "Storage should be initialized";

        parser = new Parser(tasks, storage, ui);

        // Assertion to ensure that the parser is initialized properly
        assert parser != null : "Parser should be initialized";
    }

    /*
     * Processes a single user input and returns the response as a string.
     */
    public String getResponse(String input) {
        return parser.parseCommand(input);
    }
}

// done with the guidance of ChatGPT

  