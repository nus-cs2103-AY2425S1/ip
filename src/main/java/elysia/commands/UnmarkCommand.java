package elysia.commands;

import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

/**
 * Represents a command to unmark a task in the task list.
 * Extends the {@code Command} class and handles the parsing and validation of task arguments for unmarking tasks.
 */
public class UnmarkCommand extends Command {
    private String[] args;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list from which the task will be unmarked.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second is the task number to unmark.
     */
    public UnmarkCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code UnmarkCommand} to unmark a task in the task list.
     * Validates the command arguments and attempts to unmark the specified task.
     *
     * @return a string indicating the result of unmarking the task, including confirmation and the task details.
     * @throws EmptyArgumentException if no task number is provided for unmarking.
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
            taskList.unmarkTask(taskNumber);
            output.append("Making a pretty girl undo her work is not good for her health! \n");
            output.append(taskList.printTask(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            output.append("Uh oh, this task number does not exist...");
        } catch (NullPointerException e) {
            output.append("You haven't even added anything to your list!");
        }

        return output.toString();
    }
}
