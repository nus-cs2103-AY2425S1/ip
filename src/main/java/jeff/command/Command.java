package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

/**
 * An abstract class to put all command types under one class
 */
public abstract class Command {
    private String input;

    /**
     * Constructor for Command Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Getter function to get the user's input.
     *
     * @return the user's input.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns whether the program should exit or not.
     *
     * @return whether the program should exit or not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes an action based on the command type.
     *
     * @param tasks Task list.
     * @param ui UI to print statements.
     * @param storage Place to get and write the task list to the tasks text file.
     * @throws JeffException if the user's input is in the wrong format.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException;
}
