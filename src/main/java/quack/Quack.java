package quack;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import quack.command.Command;
import quack.util.Parser;
import quack.util.Storage;
import quack.util.TaskList;
import quack.util.Ui;

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
    /** Parser object to handle user inputs */
    private Parser parser;

    /**
     * Creates a Quack chatbot object.
     * <p>
     * At the same time greet the user.
     * @param dialogContainer The output stream of the quack.
     * @param quackImage The profile picture of the quack chatbot.
     */
    public Quack(VBox dialogContainer, Image quackImage) {

        this.isRunning = true;
        this.toDoList = new TaskList();
        this.storage = new Storage(this.toDoList);
        this.ui = new Ui(dialogContainer, quackImage);
        this.parser = new Parser(toDoList, storage, ui);

        String greeting = ui.getGreeting();
        ui.outputToScreen(greeting);
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
     * Processes the user command.
     * @return The greeting message to be displayed to the user.
     */
    public Command processResponse(String userInput) {

        Command command = this.parser.processCommand(userInput);
        return command;
    }
}

