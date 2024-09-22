package cloud;

import cloud.command.Command;
import cloud.exception.CloudException;
import cloud.exception.DateFormatException;
import cloud.exception.StorageException;
import cloud.util.CloudResponse;
import cloud.util.Parser;
import cloud.util.ResponseStatus;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * The main class for the Cloud application.
 * Manages the interaction between the user interface, storage, and task list.
 */
public class Cloud {

    private static final String EXIT_COMMAND = "bye";
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private boolean isNewStorage;

    /**
     * Constructs a Cloud object.
     */
    public Cloud() {
        this.parser = new Parser();
        this.ui = new Ui();
        initializeStorage();
    }

    private void initializeStorage() {
        try {
            this.storage = new Storage();
            this.isNewStorage = storage.isNewFile();
            this.tasks = storage.readData();
        } catch (StorageException e) {
            System.err.println("Error initializing storage: " + e.getMessage());
            // You might want to set a flag or take some action when this occurs
            this.storage = null;
            this.tasks = new TaskList(); // Create an empty TaskList as a fallback
            this.isNewStorage = false;
        }
    }

    /**
     * Generates a startup message.
     *
     * @return The startup message with any potential error messages
     */
    public String getStartUp() {
        StringBuilder greeting = new StringBuilder("Hi I am Cloud! Your personal assistant!\n");
        if (storage == null) {
            greeting.append("Warning: Storage could not be initialized. Some features may not work.\n");
        } else if (isNewStorage) {
            greeting.append("I've just created a new storage file for you.\n");
        }
        greeting.append("Enter \"help\" to view all commands");
        return greeting.toString();
    }

    /**
     * Processes user input and returns an appropriate response.
     *
     * @param userInput The input string from the user.
     * @return A CloudResponse object containing the response and its status.
     */
    public CloudResponse getResponse(String userInput) {
        try {
            return parseInput(userInput);
        } catch (DateFormatException e) {
            return new CloudResponse(e.getMessage(), ResponseStatus.DATE_ERROR);
        } catch (CloudException e) {
            return new CloudResponse(e.getMessage(), ResponseStatus.INPUT_ERROR);
        }
    }

    private CloudResponse parseInput(String input) throws CloudException {
        if (storage == null) {
            return new CloudResponse(
                    "Storage is not initialized. Some features may not work.",
                    ResponseStatus.STORAGE_ERROR
            );
        }
        Command command = parser.parse(input);
        return new CloudResponse(command.execute(tasks, ui, storage), ResponseStatus.OK);
    }
}
