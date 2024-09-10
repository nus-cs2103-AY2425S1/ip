package Johnson.command;

import Johnson.task.Event;
import Johnson.utils.Utilities;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private static final String COMMAND_MSG = "Eyes peeled Chief! Got an event coming up:\n";
    private final Event event;

    public EventCommand(String task, String date, String time) {
        this.event = new Event(task, date, time);
    }

    public EventCommand(String task, String date) {
        this.event = new Event(task, date);
    }


    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + event);
        return (COMMAND_MSG + event);
    }
}
