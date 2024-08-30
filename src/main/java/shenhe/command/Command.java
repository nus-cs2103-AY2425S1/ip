package shenhe.command;

import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;

/**
 * Represents a command in the application.
 * <p>
 * The {@code Command} class is an abstract class that defines the structure of commands
 * that can be executed by the application. Each specific command will extend this class
 * and provide implementations for executing the command and determining whether the command
 * should terminate the application.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command.
     * <p>
     * This method is meant to be implemented by subclasses to define the specific behavior
     * of the command. It interacts with the {@code TaskList}, {@code Ui}, and {@code Storage}
     * to perform the desired action.
     * </p>
     *
     * @param tasks The current list of tasks to be manipulated by the command.
     * @param ui The user interface to display messages and receive user input.
     * @param storage The storage to save or load tasks.
     * @throws Exception If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Determines if the command should terminate the application.
     * <p>
     * This method is meant to be implemented by subclasses to specify whether the command
     * should cause the application to exit or not. Typically, commands like "exit" or "quit"
     * will return {@code true}.
     * </p>
     *
     * @return {@code true} if the command should terminate the application; {@code false} otherwise.
     */
    public abstract boolean isExit();
}
