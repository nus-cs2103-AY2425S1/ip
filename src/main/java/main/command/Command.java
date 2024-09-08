package main.command;

import main.exceptions.PrinceException;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command is an abstract class containing the
 * abstract method execute.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * A constructor for the Command class.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns a boolean value.
     * @return Boolean value.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets isExit value to true.
     */
    protected void toggleIsExit() {
        this.isExit = true;
    }

    /**
     * Returns an integer representing the index of a task in an array.
     * @param input Input by the user.
     * @return Index of the task.
     */
    protected int getIndex(String input) {
        String indexAsString = "";
        if (input.contains("unmark")) {
            indexAsString = input.substring(7);
        }
        if (input.contains("mark")) {
            indexAsString = input.substring(5);
        }
        if (input.contains("delete")) {
            indexAsString = input.substring(7);
        }
        if (indexAsString.equals("")) {
            return -1;
        }
        int index = Integer.valueOf(indexAsString) - 1;
        return index;
    }

    /**
     * Abstract method that executes a command based on the
     * input of the user. Requires overriding in subclasses.
     * @param tasks List of tasks.
     * @param ui Ui that was initialised in main.
     * @param storage Storage that was initialised in main.
     * @throws PrinceException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException;
}
