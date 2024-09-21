package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.ParserException;
import task.Converter;
import task.Event;
import task.Task;
import tasklist.TaskList;

/**
 * Handles related issues to the event command
 */
public class EventCommand extends Command {
    private Task event;
    /**
     * Constructor for the command from command line
     * @param cmdline The command line from user
     * @throws ParserException If invalid format found in the command line
     */
    public EventCommand(String cmdline) throws ParserException {
        String[] args = cmdline.split(" ", 2);
        if (args.length == 1) {
            throw new ParserException("Missing argument description for event command");
        }

        String[] args2 = args[1].split(" /from | /to ");
        if (args2.length <= 2) {
            throw new ParserException("Missing argument start for event command");
        }
        if (args2.length >= 4) {
            throw new ParserException("Too much argument end for event command");
        }
        String description = args2[0];
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = Converter.inputToDateTime(args2[1]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument start");
        }

        try {
            end = Converter.inputToDateTime(args2[2]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument end");
        }
        this.event = new Event(description, start, end);
    }

    @Override
    public String getResponse(TaskList tasks) {
        String response = "";
        tasks.add(this.event);
        response += "A task is added\n";
        response += this.event.toString() + "\n";
        return response;
    }
}
