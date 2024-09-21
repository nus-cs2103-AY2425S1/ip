package elysia.commands;

import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Represents a command to list all tasks in the Elysia application.
 * Extends the {@code Command} class and provides functionality to display the current task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} with the specified task list and file reader/writer.
     *
     * @param taskList the task list to be displayed.
     * @param fileReaderWriter the file reader/writer for saving or loading task data (not used in this command).
     */
    public ListCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }

    /**
     * Executes the {@code ListCommand} and returns the current task list as a string.
     * The output includes all tasks stored in the task list.
     *
     * @return a string representing the current list of tasks.
     */
    @Override
    public String execute() {
        return "Here's your list! \n" + taskList.toString();
    }
}
