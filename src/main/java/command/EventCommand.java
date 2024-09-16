package command;

import task.Task;
import task.Event;
import exception.ParserException;
import task.Converter;
import tasklist.TaskList;
import ui.Ui;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    private Task event;
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
            start = Converter.InputToDateTime(args2[1]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument start");
        }

        try {
            end = Converter.InputToDateTime(args2[2]);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid format for argument end");
        }
            
        this.event = new Event(description, start, end);
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.event);
        ui.println("A task is added");
        ui.println(this.event);
    }

    public boolean isExit() { return true; }
}
