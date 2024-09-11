package ipman.commands;

import java.time.LocalDate;

import ipman.models.Event;
import ipman.models.Task;
import ipman.models.TaskList;
import ipman.ui.Ui;

/**
 * Creates an <code>Event</code> inside <code>Context</code>'s
 * <code>TaskList</code>
 *
 * @see Event
 * @see Context
 * @see TaskList
 */
public class CreateEventCommand implements Command {
    private final String name;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates command that creates a particular event
     *
     * @param name event name
     * @param from event start date
     * @param to event end date
     * @see Event
     */
    public CreateEventCommand(String name, LocalDate from, LocalDate to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        Task task = new Event(this.name, this.from, this.to);
        tasks.add(task);

        Ui ui = context.ui();
        ui.showMessage(String.format("""
            Got it. I've added this task:
            %s
            Now you have %d tasks in the list.
        """, task, tasks.size()));
    }
}
