package hoshi;

import java.io.FileNotFoundException;
import java.io.IOException;

import hoshi.task.TaskList;
import hoshi.ui.Ui;
import hoshi.utils.Parser;
import hoshi.utils.Storage;


/**
 * Main class for Hoshi
 */
public class Hoshi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Hoshi method that sets up relevant dependencies for Hoshi chatbot
     */
    public Hoshi() {

        // initialize required objects to run Hoshi
        initializeComponents();
        // get tasks from storage
        loadTaskList();
    }

    /**
     * Runs the main flow of Hoshi
     */
    public String run(String input) {

        String response = handleCommand(input);
        // save tasks to storage
        saveTaskList();
        return response;
    }

    /**
     * Loads tasks from storage
     */
    private void loadTaskList() {
        try {
            storage.load(tasks);
        } catch (FileNotFoundException e) {
            ui.displayLoadingError(e.getMessage());
        }
    }

    /**
     * Saves tasks to storage
     */
    private void saveTaskList() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.displaySavingError(e.getMessage());
        }
    }

    /**
     * Handles the user command by parsing it and returning a response
     *
     * @param input by the user
     * @return the response string output by parseCommand
     */
    private String handleCommand(String input) {
        return parser.parseCommand(input, tasks, ui);
    }

    /**
     * Initializes the necessary components for Hoshi
     */
    private void initializeComponents() {
        String filePath = "data/Hoshi.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser();
    }

}
