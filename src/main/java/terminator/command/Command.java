package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

/**
 * Abstract base class to represent a command by the user.
 */
public abstract class Command {

    public Command() {
    }

    /**
     * Executes the command to perform an operation.
     *
     * @param todoList The task list.
     * @throws TerminatorException
     */
    public abstract void execute(ArrayList<Task> todoList) throws TerminatorException;
}
