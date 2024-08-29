package bob.commands;

import bob.data.TaskList;
import bob.tasks.DeadlineTask;
import bob.tasks.Task;
import bob.storage.Storage;
import bob.ui.Ui;

import java.time.LocalDateTime;

/**
 * Adds a deadline task to the list.
 */
public class Deadline extends Command {
    String description;
    LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task to the list.
     * Shows the user the task that has been added and the number of tasks in the list.
     *
     * @param list The list of tasks.
     * @param t The task to be added.
     */
    private static void taskAdded(TaskList list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
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
