package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

/**
 * Abstract base class to represent a command by the user.
 */
public abstract class Command {

    /**
     * The raw input string of the command.
     */
    protected final String input;

    /**
     * No-args constructor for commands that don't require any additional option inputs.
     */
    public Command() {
        this.input = "";
    }

    /**
     * Constructor for commands which require additional parsing of the user input.
     *
     * @param input The raw input string from the user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command to perform an operation.
     *
     * @param todoList The task list.
     * @throws TerminatorException
     */
    public abstract void execute(ArrayList<Task> todoList) throws TerminatorException;
}
