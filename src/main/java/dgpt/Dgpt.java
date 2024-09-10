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

    public String getResponse(String s) {
        try {
            return Parser.parse(s, tasks, storage);
        } catch (TaskNotFoundException | IncorrectInputException e) {
            return e.getMessage();
        }
    }
}


