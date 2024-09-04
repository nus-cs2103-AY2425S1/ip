package tohru.command;

import java.time.format.DateTimeFormatter;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents a specific operation that interacts with the to-do list, store and ui.
 */
public abstract class Command {

    /** Formatter used to parse datetime strings in arguments. */
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/y HHmm");

    /** The argument passed in to a command. */
    protected String arguments;

    /**
     * Represents a command object that takes in its argument.
     *
     * @param arguments The argument to be passed in for a command.
     */
    public Command(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the operation.
     *
     * @param list The to-do list to modify.
     * @param ui The ui for interaction.
     * @param store The storage medium to interact.
     * @throws TohruException When invalid arguments are encountered.
     */
    public abstract void execute(TodoList list, Ui ui, FileStore store) throws TohruException;

    /**
     * Checks if the application should exit after the execution of this command.
     *
     * @return A boolean representing if the application should exit.
     */
    public boolean isExit() {
        return false;
    }

}
