package snipe.core;

import snipe.util.Parser;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.util.Ui;
import snipe.command.Command;
import snipe.task.Task;

import java.util.ArrayList;
import java.io.IOException;

/**
 * The {@code snipe.core.Snipe} class represents a task management system that interacts with the user via command-line input.
 * It supports commands for adding, deleting, marking, unmarking, and listing tasks.
 * The application reads from and writes to a task list file and provides a simple text-based interface to manage tasks.
 */
public class Snipe {
    private Storage taskListStorage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new {@code Snipe} instance.
     * Initialises the storage, user interface, and task list by reading from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Snipe(String filePath) {
        this.ui = new Ui();
        this.taskListStorage = new Storage(filePath);
        try {
            this.tasks = new TaskList(taskListStorage.readTaskList());
        } catch (SnipeException | IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Initialises the chat interface with the user, greets the user, and handles user input in a loop.
     * The method continues to accept and execute commands until the user issues an exit command.
     *
     * @throws IOException    If an input or output exception occurs.
     * @throws SnipeException If an application-specific error occurs during command processing.
     */
    public void initChat() throws IOException, SnipeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, taskListStorage);
                isExit = c.isExit();
            } catch (SnipeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method that starts the Snipe application.
     * Initialises the application with the path to the task list file and starts the user interface.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException    If an input or output exception occurs.
     * @throws SnipeException If an application-specific error occurs during initialisation.
     */
    public static void main(String[] args) throws IOException, SnipeException {
        Snipe snipe = new Snipe("src/main/txt/taskList.txt");
        snipe.initChat();
    }
}


