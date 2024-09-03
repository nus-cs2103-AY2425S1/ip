package bob.commands;

import java.time.LocalDateTime;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.EventTask;
import bob.tasks.Task;
import bob.ui.Ui;

/**
 * Class representing an event command.
 */
public class Event extends Command {
    private String description;
    private LocalDateTime from;
    private String to;

    /**
     * Creates an event command.
     *
     * @param description the description of the event.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     */
    public Event(String description, LocalDateTime from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    private static void taskAdded(TaskList list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list."
                : " tasks in the list."));
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = new EventTask(description, from, to);
        list.add(t);
        taskAdded(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
