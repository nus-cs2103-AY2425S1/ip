package dgpt;

import java.io.IOException;

import dgpt.exception.DgptFileNotFoundException;
import dgpt.exception.IncorrectInputException;
import dgpt.exception.TaskNotFoundException;
import dgpt.task.TaskList;

/**
 * The Dgpt class represents a simple task management system.
 * It allows users to add tasks, mark them as done, unmark them, view the list of tasks, as well as find tasks using
 * keywords. It is able to save and load existing data using the local hard drive.
 */
public class Dgpt {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Dgpt instance with the specified file path for data storage and initialization.
     * <p>
     * This constructor initializes the user interface (UI) and sets up the storage component.
     * It attempts to load existing tasks from the file into the task list. If the file is not found or an
     * I/O error occurs during loading, it handles the exceptions by displaying an error message through
     * the UI and initializes an empty task list.
     * </p>
     *
     * @param filepath The path to the file where task data is stored. This file is used to load existing
     *                 tasks upon initialization.
     */
    public Dgpt(String filepath) {
        assert !filepath.isEmpty() : "filepath should not be empty";
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DgptFileNotFoundException | IOException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Processes the user's input text, and returns the output that is constructed by the bot.
     * <p>
     * It is utilised by the MainWindow class to handle the user's input that is provided through the input box.
     * It will provide the return message that is meant to be the bot's response for the MainWindow to display.
     * </p>
     *
     * @param s the entire string input given by the user.
     * @return the string output that is returned by the bot.
     */
    public String getResponse(String s) {
        try {
            return Parser.parse(s, tasks, storage);
        } catch (TaskNotFoundException | IncorrectInputException e) {
            return Ui.errorUi(e);
        } catch (Exception e) {
            return "Unknown Error!";
        }
    }
}


