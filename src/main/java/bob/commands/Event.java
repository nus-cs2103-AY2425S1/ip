package bob.commands;

import bob.data.TaskList;
import bob.tasks.EventTask;
import bob.tasks.Task;
import bob.storage.Storage;
import bob.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Command {
    private final String description;
    private final LocalDateTime from;
    private final String to;

    public Event(String description, LocalDateTime from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the list.
     * Shows the user the task that has been added and the number of tasks in the list.
     *
     * @param list The list of tasks.
     * @param task The task to be added.
     */
    private static void taskAdded(TaskList list, Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
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
