package elysia.commands;

import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Task;
import elysia.tasks.TaskList;

/**
 * Represents a command to delete a task from the task list in the Elysia application.
 * Extends the {@code Command} class and handles the parsing and validation of delete task arguments.
 */
public class DeleteCommand extends Command {
    private String[] args;

    /**
     * Constructs a {@code DeleteCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list from which the task will be deleted.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second contains the task number.
     */
    public DeleteCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code DeleteCommand} to remove a task from the task list.
     * Validates the command arguments and deletes the specified task if it exists.
     *
     * @return a string indicating the result of the deletion, including the deleted task details.
     * @throws EmptyArgumentException if no task number is provided.
     * @throws WrongArgumentException if the provided task number is not a valid integer.
     */
    @Override
    public String execute() throws EmptyArgumentException, WrongArgumentException {
        String output = "";

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
            Task deletedTask = taskList.deleteTask(taskNumber);
            output = "You don't need this task below anymore? Ok deleting it~\n";
            output += deletedTask.toString();
            output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
        } catch (IndexOutOfBoundsException e) {
            output = "Uh oh, this task number does not exist...";
        }

        return output;
    }
}
