package elysia.commands;

import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Represents a command for handling unrecognized input in the Elysia application.
 * Extends the {@code Command} class and provides a default response for unknown commands.
 */
public class UnknownCommand extends Command {

    /**
     * Constructs an {@code UnknownCommand} with the specified task list and file reader/writer.
     *
     * @param taskList the task list associated with the command.
     * @param fileReaderWriter the file reader/writer for managing task data.
     */
    public UnknownCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }

    /**
     * Executes the {@code UnknownCommand}, returning a message indicating that the command is unrecognized.
     *
     * @return a string response to the user, suggesting they clarify their input.
     */
    @Override
    public String execute() {
        return "What are you trying to say? You need to talk to pretty girls nicely...";
    }
}
