package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.exception.EmptyDescriptionException;
import elysia.exception.InvalidDateTimeInputException;
import elysia.task.Task;


/**
 * Represents a command in the application. Manages the task lists with commands.
 */
public abstract class Command {

    public Command() {

    }

    /**
     * Executes the command on the provided list of tasks. This method must be implemented by subclasses to define
     * specific command behavior.
     *
     * @param tasks The list of tasks to be managed by the command.
     * @return A response or result after executing the command.
     * @throws EmptyDescriptionException If the description provided is empty.
     * @throws IOException               If an I/O error occurs during command execution.
     */
    public abstract String execute(ArrayList<Task> tasks)
            throws EmptyDescriptionException, IOException, InvalidDateTimeInputException;

    public boolean isExit() {
        return false;
    }
}
