package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Deadline;
import elysia.tasks.TaskList;
import elysia.Parser.DateTimeParser;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to create a new deadline task in the Elysia application.
 * Extends the {@code Command} class and handles the parsing and validation of deadline task arguments.
 */
public class DeadlineCommand extends Command {
    private String[] args;

    /**
     * Constructs a {@code DeadlineCommand} with the specified task list, file reader/writer, and command arguments.
     *
     * @param taskList the task list to which the deadline will be added.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second contains the task details.
     * @throws DateTimeParseException if the date format is invalid when initializing the deadline.
     */
    public DeadlineCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args)
            throws DateTimeParseException {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code DeadlineCommand} to create a new deadline task.
     * Validates the command arguments, creates the deadline task, and adds it to the task list.
     *
     * @return a string indicating the result of adding the new deadline task.
     * @throws EmptyTaskArgumentsException if no arguments are provided for the deadline command.
     * @throws ArgumentFormatException if the arguments are incorrectly formatted.
     * @throws WrongArgumentException if the date argument is of the wrong type or format.
     */
    @Override
    public String execute() throws EmptyTaskArgumentsException, ArgumentFormatException, WrongArgumentException {
        StringBuilder output = new StringBuilder();

        if (args.length == 1) {
            throw new EmptyTaskArgumentsException(args[0]);
        }

        String[] deadlineArgs = args[1].split(" /by ", 2);
        if (deadlineArgs.length != 2) {
            throw new ArgumentFormatException(args[0]);
        }

        Deadline deadline;
        try {
            deadline = new Deadline(deadlineArgs[0], DateTimeParser.parseDate(deadlineArgs[1]));
        } catch (DateTimeParseException e) {
            throw new WrongArgumentException("date");
        }

        taskList.addTask(deadline);
        output.append("Added the task below to your list~\n")
                .append(deadline.toString()).append("\n")
                .append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");

        return output.toString();
    }
}
