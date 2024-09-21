package elysia.commands;

import elysia.exceptions.ElysiaException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Abstract class representing a command in the Elysia application.
 * Defines the structure for commands that operate on a task list and interact with file storage.
 * Subclasses should implement the {@code execute} method to define specific command behavior.
 */
public abstract class Command {

    protected TaskList taskList;
    protected FileReaderWriter fileReaderWriter;

    /**
     * Constructs a new {@code Command} object with the specified task list and file reader/writer.
     *
     * @param taskList the task list to be manipulated by the command.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     */
    public Command(TaskList taskList, FileReaderWriter fileReaderWriter) {
        this.taskList = taskList;
        this.fileReaderWriter = fileReaderWriter;
    }

    /**
     * Executes the command and returns the result as a string.
     * The specific behavior of the command is defined in subclasses.
     *
     * @return a string representing the outcome or message of the executed command.
     * @throws ElysiaException if an error occurs during command execution.
     */
    public abstract String execute() throws ElysiaException;
}
