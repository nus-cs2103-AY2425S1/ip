package kobe.command;

import kobe.util.Storage;
import kobe.task.TaskList;
import kobe.util.Ui;

import java.io.IOException;

/**
 * Represents an abstract command that can be executed in the Duke chatbot application.
 * This class provides a common interface for all command types to implement their specific behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the provided task list, user interface, and storage.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks.
     * @throws IOException If an error occurs during file operations while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
