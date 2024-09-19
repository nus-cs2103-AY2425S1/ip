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
    private String taskListFilePath;
    private String archiveListFilePath;
    private Storage taskListStorage;
    private Storage archiveListStorage;
    private TaskList tasks;
    private TaskList archiveTasks;
    private Ui ui;

    /**
     * Constructs a new {@code Snipe} instance.
     * Initialises the storage, user interface, and task list by reading from the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Snipe(String filePath) {
        this.ui = new Ui();
        this.taskListFilePath = filePath + "/taskList.txt";
        this.archiveListFilePath = filePath + "/archiveList.txt";
        this.taskListStorage = new Storage(this.taskListFilePath);
        this.archiveListStorage = new Storage(this.archiveListFilePath);
        try {
            this.tasks = new TaskList(taskListStorage.readTaskList());
            this.archiveTasks = new TaskList(archiveListStorage.readTaskList());
        } catch (SnipeException | IOException e) {
            System.out.print(e.getMessage());
            this.ui.showLoadingError();
            this.tasks = new TaskList();
            this.archiveTasks = new TaskList();
        }

        // Assertions to check that essential objects are initialized properly
        assert ui != null : "Ui should be initialized";
        assert taskListStorage != null : "Storage should be initialized";
        assert tasks != null : "TaskList should be initialized";
        assert archiveListStorage != null : "Storage should be initialized";
    }

    /**
     * Initialises the chat interface with the user, greets the user, and handles user input in a loop.
     * The method continues to accept and execute commands until the user issues an exit command.
     *
     * @throws IOException    If an input or output exception occurs.
     * @throws SnipeException If an application-specific error occurs during command processing.
     */
    public void initChat() throws IOException, SnipeException {
        taskListStorage.initialise();
        archiveListStorage.initialise();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);

                // Assert that a valid command is always returned by the parser
                assert c != null : "Parsed command should never be null";

                String response = c.getResponse(tasks, ui, taskListStorage,archiveTasks, archiveListStorage);
                ui.printWithLines(response);
                isExit = c.isExit();
            } catch (SnipeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws IOException, SnipeException {
        try {
            Command c = Parser.parse(input);

            // Assert that the command is not null
            assert c != null : "Parsed command should never be null";

            return c.getResponse(tasks, ui, taskListStorage, archiveTasks, archiveListStorage);
        } catch (SnipeException e) {
            return e.getMessage();
        }

    }

    public String getWelcomeMessage() {
        return Ui.WELCOME_MESSAGE;
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
        Snipe snipe = new Snipe("../data");
        snipe.initChat();
    }
}


