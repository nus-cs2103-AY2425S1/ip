package Johnson.command;

import Johnson.task.Event;
import Johnson.utils.Utilities;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    /**
     * The command word that identifies an EventCommand instance.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * The message that is displayed when an EventCommand instance is executed successfully.
     */
    private static final String COMMAND_MSG = "Eyes peeled Chief! Got an event coming up:\n";
    private final Event event;

    /**
     * Constructs an EventCommand with the specified task, date, time and tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param time the time of the task.
     * @param tags the tags of the task.
     */
    public EventCommand(String task, String date, String time, String... tags) {
        this.event = new Event(task, date, time, tags);
    }

    /**
     * Constructs an EventCommand with the specified task, date and tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param tags the tags of the task.
     */
    public EventCommand(String task, String date, String... tags) {
        this.event = new Event(task, date, tags);
    }

    public Event getEvent() {
        return this.event;
    }
    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG + event);
        Command.taskList.addTask(event);
        return (COMMAND_MSG + event);
    }


}
