package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

/**
 * The {@code Commands} class represents a collection of commands that can be executed
 * in a task management application. Commands include actions like adding tasks,
 * marking tasks as done or undone, deleting tasks, and more. The class also provides
 * support for handling user inputs and running the appropriate command based on the input.
 */
public abstract class Command {
    protected String input;
    protected boolean canExit;
    protected String response;

    public abstract void execute(TaskList tasks, Storage storage);

    public enum Types {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        BYE,
        HELP,
        DELETE,
        FIND,
    }

    /**
     * Initializes a new {@code Commands} object with the exit flag set to false.
     */
    public Command() {
        this.canExit = false;
    }

    /**
     * Checks if the program should exit.
     *
     * @return {@code true} if the exit flag is set, {@code false} otherwise.
     */
    public boolean isCanExit() {
        return canExit;
    }

    public String getResponse() {
        return response;
    }
    protected void setResponse(String res) {
        response = res;
    }


    /**
     * Checks and returns the command type based on the user's input string.
     *
     * @param command The input string representing the command.
     * @return The corresponding {@code Types} enumeration.
     * @throws RuntimeException if the command is not recognized.
     */
    public static Types checkType(String command) {
        for(Types t : Types.values()) {
            if (t.name().equalsIgnoreCase(command)) return t;
        }
        throw new RuntimeException("Neigh, the command don't exist");
    }

}
