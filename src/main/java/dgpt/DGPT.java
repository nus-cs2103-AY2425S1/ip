package dgpt;

import dgpt.exception.DgptFileNotFoundException;
import dgpt.exception.IncorrectInputException;
import dgpt.exception.TaskNotFoundException;
import dgpt.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * The DGPT class represents a simple task management system.
 * It allows users to add tasks, mark them as done, unmark them, view the list of tasks, as well as find tasks using
 * keywords. It is able to save and load existing data using the local hard drive.
 */
public class DGPT {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a DGPT instance with the specified file path for data storage and initialization.
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
    public DGPT(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DgptFileNotFoundException | IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;
        String input;

        // bot is ready for user
        while (isActive) {
            ui.showUser();
            input = scanner.nextLine();
            if (input.equals("bye")) {
                isActive = false;
            } else {
                try {
                    Parser.parse(input, tasks, ui);
                } catch (TaskNotFoundException | IncorrectInputException e) {
                    ui.showError(e);
                } catch (Exception e) {
                    ui.showUnknownError();
                }
            }
        }

        scanner.close();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }
        ui.showBye();
    }



    /**
     * Main method to start the program and interact with the user.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new DGPT("./data/dgpt.txt").run();
    }
}
