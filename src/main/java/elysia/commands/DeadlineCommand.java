package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Deadline;
import elysia.tasks.TaskList;
import elysia.Parser.DateTimeParser;
import elysia.ui.Message;

import java.time.format.DateTimeParseException;
import java.util.Objects;

public class DeadlineCommand extends Command {
    String[] args;

    public DeadlineCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args)
            throws DateTimeParseException {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

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
        assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        output.append("Added the task below to your list~\n").append(deadline.toString()).append("\n");
        output.append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");
        return output.toString();
    }
}
