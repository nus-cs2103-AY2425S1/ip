package wenjiebot.commands;

import wenjiebot.exceptions.WenJieException;
import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;

/**
 * Abstract class representing a command in Wenjie chatbot.
 * Commands are used to perform various actions such as adding tasks, listing tasks, etc.
 * This class provides the basic structure for commands including whether the command
 * should exit the application and the input associated with the command.
 */
public abstract class Command {

    private boolean isExit = false;
    private String input = "";

    /**
     * Constructs a Command with the specified exit status and input.
     *
     * @param isExit boolean indicating whether this command will exit the application.
     * @param input the input associated with this command.
     */
    public Command(boolean isExit, String input) {
        this.isExit = isExit;
        this.input = input;
    }

    /**
     * Executes the command. The implementation of this method is specific to each subclass.
     *
     * @param tasks the TaskList that contains all the tasks.
     * @param ui the Ui used for interaction with the user.
     * @param storage the Storage used to store and retrieve tasks.
     * @throws WenJieException if there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException;

    /**
     * Returns whether this command is an exit command.
     *
     * @return true if this command will exit the application, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets whether this command is an exit command.
     *
     * @param isExit boolean indicating whether this command should exit the application.
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns the input associated with this command.
     *
     * @return the input associated with this command.
     */
    public String getInput() {
        return input;
    }
}
