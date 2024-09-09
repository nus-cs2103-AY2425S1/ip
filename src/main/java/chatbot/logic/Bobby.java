package chatbot.logic;

import chatbot.exception.InputException;

/**
 * Represents the chatbot
 * This class contains the main method that serves as the entry point to the program
 */
public class Bobby {
    /** String constant representing the directory path of the file */
    private static final String DIR_PATH = "./data/";
    /** String constant representing the name of the file */
    private static final String FILE_NAME = "tasks.txt";
    /** TaskList class encapsulated by Bobby */
    private TaskList taskList;
    /** Storage class encapsulated by Bobby */
    private Storage storage;

    /**
     * Constructor for the Bobby class
     * Initialises the ui, storage and taskList variables
     */
    public Bobby() {
        this.storage = new Storage(DIR_PATH, FILE_NAME);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Gets the bot's response to a string entered by the user
     *
     * @param command The command string entered by the user
     * @return The bot's reponse to the user's entered string
     */
    public String getResponse(String command) {
        assert this.storage != null : "storage should not be null";
        assert this.taskList != null : "taskList should not be null";

        try {
            return Parser.processInput(command, this.taskList, this.storage);
        } catch (InputException e) {
            return e.getMessage();
        }
    }
}
