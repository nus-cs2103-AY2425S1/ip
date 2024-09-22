package panorama.command;

import java.time.LocalDate;

import panorama.TaskList;
import panorama.task.Event;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_SHORTHAND = "e";

    private TaskList taskList;

    private String name;
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an EventCommand with the specified task list, event name, and date range.
     *
     * @param taskList The task list to which the event task will be added.
     * @param name     The name of the event task.
     * @param from     The start date of the event.
     * @param to       The end date of the event.
     */
    public EventCommand(TaskList taskList, String name, LocalDate from, LocalDate to) {
        this.taskList = taskList;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public Response execute() {
        assert taskList != null;
        Event event = new Event(name, from, to);
        taskList.add(event);
        return new Response("Added task:\n" + event.toString(), false);
    }
}
