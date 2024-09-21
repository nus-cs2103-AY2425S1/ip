package elysia.commands;

import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

import java.util.Objects;

/**
 * Represents a command to mark a task as completed in the task list.
 * Extends the {@code Command} class and handles the parsing and validation of task arguments.
 */
public class MarkCommand extends Command {
    private String[] args;

    /**
     * Constructs a {@code MarkCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list in which the task will be marked.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second is the task number to mark.
     */
    public MarkCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code MarkCommand} to mark a specified task as completed.
     * Validates the command arguments and updates the task status in the task list.
     *
     * @return a string indicating the result of marking the task, including confirmation or error messages.
     * @throws EmptyArgumentException if no task number is provided for the mark command.
     * @throws WrongArgumentException if the provided task number is not a valid integer.
     */
    @Override
    public String execute() throws EmptyArgumentException, WrongArgumentException {
        StringBuilder output = new StringBuilder();

        if (args.length == 1) {
            throw new EmptyArgumentException(args[0]);
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("task number");
        }

        try {
            taskList.markTask(taskNumber);
            output.append("Amazing! You've completed this task! \n");
            output.append(taskList.printTask(taskNumber));
            assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        } catch (IndexOutOfBoundsException e) {
            output.append("Uh oh, this task number does not exist...");
        } catch (NullPointerException e) {
            output.append("You haven't even added anything to your list!");
        }

        return output.toString();
    }
}
