package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to add a new <code>Task</code>
 * to the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand to add <code>ToDo</code> task with given title.
     *
     * @param title The title of the task.
     */
    public AddCommand(String title) {
        task = new ToDo(title);
    }

    /**
     * Constructs an AddCommand to add <code>Deadline</code> task with given
     * title and deadline.
     *
     * @param title The title of the task.
     * @param deadline The deadline of the task.
     */
    public AddCommand(String title, String deadline) {
        task = new Deadline(title, deadline);
    }

    /**
     * Constructs an AddCommand to add <code>Event</code> task with given
     * title, start and end time.
     *
     * @param title The title of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public AddCommand(String title, String from, String to) {
        task = new Event(title, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.addTask(task);
        ui.showAddMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
