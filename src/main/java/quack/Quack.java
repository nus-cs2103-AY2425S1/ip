package quack;

import quack.command.Command;

import quack.exception.InvalidCommandException;

/**
 * The Quack chatbot program implements the functionality needed
 * to help users keep track of tasks for them.
 */
public class Quack {

    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** Determine if the bot should continue or stop running */
    private boolean isRunning;
    /** To store all of the users tasks */
    private TaskList toDoList;
    /** Sotrage object to load and save data */
    private Storage storage;
    /** Paser object to handle user inputs */
    private Paser paser;

    
    /**
     * Creates a Quack chatbot object.
     */
    public Quack() {

        this.isRunning = true;
        this.toDoList = new TaskList();
        this.storage = new Storage(this.toDoList);
        this.ui = new Ui();
        this.paser = new Paser(this, toDoList, storage, ui);
    }

    /**
     * Gets the running state of Quack.
     * @return The running state of the chatbot as a boolean.
     */
    public boolean getIsRunning() {
        return this.isRunning;
    }

    /**
     * Stops quack from taking more inputs from the user and stops the chatbot.
     */
    public void shutDown() {
        this.isRunning = false;
    }

    /**
     * Runs the chatbot and start taking inputs from the user.
     */
    private void run() {

        ui.printgreeting();

        // Keep taking inputs from the user as long as the chatbot is running
        while (isRunning) {
            try {
                Command command = paser.getUserInput();
                command.execute();
            } catch (InvalidCommandException commandError) {
                ui.printExceptionMessage(commandError);
            }
        }
        ui.printFarewell();
        ui.closeScanner();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
