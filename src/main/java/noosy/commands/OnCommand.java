package noosy.commands;

import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.Deadline;
import noosy.task.Event;
import noosy.task.Task;
import noosy.task.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the command to list tasks due on a specific date in the Noosy task management system.
 * This command filters and displays Deadline and Event tasks that match the specified date.
 */
public class OnCommand extends Command {

    /** The date for which to list tasks. */
    private LocalDate date;


    /**
     * Constructs an OnCommand with the specified date.
     *
     * @param date The date for which to list tasks.
     */
    public OnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to list tasks due on the specified date.
     * Filters through the task list and displays matching Deadline and Event tasks.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The user interface for displaying output (not used directly in this implementation).
     * @param storage The storage for persisting tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("You needa do this on " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) {
                    System.out.println(deadline);
                    found = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStart().toLocalDate().equals(date)) {
                    System.out.println(event);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Hooray! Nothing to do on " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }
}