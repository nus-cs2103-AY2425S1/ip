package elysia.commands;

import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Represents a command to save the current task list and terminate the Elysia application.
 * Extends the {@code Command} class and provides functionality to save the task list to a file.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand} with the specified task list and file reader/writer.
     *
     * @param taskList the task list to be saved.
     * @param fileReaderWriter the file reader/writer responsible for saving the task data to a file.
     */
    public ByeCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }

    /**
     * Executes the {@code ByeCommand} by saving the task list to a file and returning the result.
     * This method calls the {@code createFile()} and {@code writeFile()} methods from {@code FileReaderWriter}.
     *
     * @return a string indicating the result of creating and writing the task list to a file.
     */
    @Override
    public String execute() {
        return fileReaderWriter.createFile() + "\n" + fileReaderWriter.writeFile();
    }
}
