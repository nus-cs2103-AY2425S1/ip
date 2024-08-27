package chatbot;

import chatbot.exception.InputException;

/**
 * Represents the chatbot
 * This class contains the main method that serves as the entry point to the program
 */
public class Bobby {
//    private final Scanner input;
    private Ui ui;
//    private ArrayList<Bobby.Task> tasks;
    private TaskList taskList;
    private Storage storage;
    private static final String DIR_PATH = "./data/";
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
     * @param args Currently serves no use
     */
    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.run();
    }
}
