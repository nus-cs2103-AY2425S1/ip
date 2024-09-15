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

    public EventCommand(String task, String date, String time, String... tags) {
        this.event = new Event(task, date, time, tags);
    }

    public EventCommand(String task, String date, String... tags) {
        this.event = new Event(task, date, tags);
    }


    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + event);
        Command.taskList.addTask(event);
        return (COMMAND_MSG + event);
    }
}
