package chatbot;

import chatbot.exception.InputException;

/**
 * Represents the chatbot
 * This class contains the main method that serves as the entry point to the program
 */
public class Bobby {
    /** Ui class encapsulated by Bobby */
    private Ui ui;
    /** TaskList class encapsulated by Bobby */
    private TaskList taskList;
    /** Storage class encapsulated by Bobby */
    private Storage storage;
    /** String constant representing the directory path of the file */
    private static final String DIR_PATH = "./data/";
    /** String constant representing the name of the file */
    private static final String FILE_NAME = "tasks.txt";

    /**
     * Constructor for the Bobby class
     * Initialises the ui, storage and taskList variables
     */
    public Bobby() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_NAME);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Runs the core chat loop for the chatbot
     */
    public void run() {
        this.ui.sayHi();
        while (this.ui.runStatus()) {
            try {
                Parser.processInput(this.ui.getInput(), this.taskList, this.storage, this.ui);
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main method that serves as the entry point to the program
     *
     * @param args Currently serves no use
     */
    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.run();
    }
}
