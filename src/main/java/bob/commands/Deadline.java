package bob.commands;

import java.time.LocalDateTime;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.DeadlineTask;
import bob.tasks.Task;
import bob.ui.Ui;

/**
 * Class representing the deadline command.
 */
public class Deadline extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Creates a new deadline command.
     *
     * @param description the description of the deadline.
     * @param by the deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    private static void taskAdded(TaskList list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list."
                : " tasks in the list."));
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = new DeadlineTask(description, by);
        list.add(t);
        taskAdded(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
