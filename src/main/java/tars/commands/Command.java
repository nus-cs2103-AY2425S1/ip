package tars.commands;

import tars.tasks.TaskList;

/**
 * An abstract class that represents a command to be executed on a {@link TaskList}.
 *
 * <p>The {@code Command} class serves as a base for all specific command types
 * that can manipulate a {@link TaskList}. Each subclass must provide
 * its own implementation of the {@link #execute(String, TaskList)} method to define
 * the specific behavior of the command.</p>
 */
public abstract class Command {

    /**
     * Executes a command based on the given input and modifies the provided task list accordingly.
     *
     * @param input The input string representing the command to be executed.
     * @param tasks The {@link TaskList} object containing the list of tasks to be modified or accessed.
     * @return A {@link String} representing the result or response message after executing the command.
     */
    public abstract String execute(String input, TaskList tasks);



}
