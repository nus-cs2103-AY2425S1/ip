package elysia.commands;

import elysia.Parser.DateTimeParser;
import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Event;
import elysia.tasks.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a command to create a new event task in the Elysia application.
 * Extends the {@code Command} class and handles the parsing and validation of event task arguments.
 */
public class EventCommand extends Command {

    /**
     * Constructs an {@code EventCommand} with the specified task list, file reader/writer, and command arguments.
     * Command format: event [optional: r] [DESCRIPTION] /from [START_DATE] /to [END_DATE]
     *
     * @param taskList the task list to which the event will be added.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and the second contains the event details.
     */
    public EventCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code EventCommand} to create a new event task.
     * Validates the command arguments, creates the event task, and adds it to the task list.
     *
     * @return a string indicating the result of adding the new event task.
     * @throws EmptyTaskArgumentsException if no arguments are provided for the event command.
     * @throws ArgumentFormatException if the arguments are incorrectly formatted.
     */
    @Override
    public String execute() throws EmptyTaskArgumentsException, ArgumentFormatException, WrongArgumentException {
        StringBuilder output = new StringBuilder();

        if (args.length == 1) {
            throw new EmptyTaskArgumentsException(args[0]);
        }

        String[] splitArgs = args[1].split(" ", 2);
        boolean isRecurring;
        String otherArgs;
        if (Objects.equals(splitArgs[0], "r")) {
            isRecurring = true;
            if (splitArgs.length == 1) {
                throw new EmptyTaskArgumentsException(args[0]);
            }
            otherArgs = splitArgs[1];
        } else {
            isRecurring = false;
            otherArgs = args[1];
        }

        String[] eventArgs = otherArgs.split(" /from | /to ");
        if (eventArgs.length != 3) {
            throw new ArgumentFormatException(args[0]);
        }

        Event event;
        try {
            event = new Event(eventArgs[0],
                    DateTimeParser.parseDate(eventArgs[1]),
                    DateTimeParser.parseDate(eventArgs[2]),
                    isRecurring);
        } catch (DateTimeParseException e) {
            throw new WrongArgumentException("date");
        }

        taskList.addTask(event);
        assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        output.append("Added the task below to your list~\n").append(event).append("\n");
        output.append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");
        return output.toString();
    }
}
