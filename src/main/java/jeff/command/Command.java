package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.TaskList;

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
     * Returns the string representation of the response by the chatbot Jeff based on a certain command.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws JeffException;
}
