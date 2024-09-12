package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.Deadline;
import waterfall.task.Event;
import waterfall.task.Task;
import waterfall.task.TaskList;
import waterfall.task.ToDo;

/**
 * Represents the <code>Command</code> object to add a new <code>Task</code>
 * to the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class AddCommand extends UndoableCommand {
    private final Task task;
    private int index;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.addTask(task);
        index = tasks.getNum() - 1;
        addToUndoList(this);
        return ui.showAddMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws IOException {
        tasks.delete(index);
        storage.updateTask(tasks.getTasks());
    }
}
