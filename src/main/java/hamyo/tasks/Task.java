package hamyo.tasks;

import java.time.LocalDate;

import hamyo.misc.HamyoException;
import hamyo.misc.Ui;

/**
 * Represents a Task.
 *
 * @author Han Yu
 */
public class Task {

    private final String task;
    private boolean isMarked;

    /**
     * Constructor for Task instance.
     * @param input Derived from user command input. Description for the task,
     *              [Description].
     */
    public Task(String... input) {
        this.task = input[0];
    }


    /**
     * Marks a task if it was unmarked.
     *
     * @param showText Print Ui for mark onto terminal if true.
     * @throws HamyoException If task was already marked.
     */
    public void mark(boolean showText) throws HamyoException {
        if (!isMarked) {
            isMarked = true;
            if (showText) {
                Ui.setStringMarkTask(this);
            }
        } else {
            throw new HamyoException("This task was already marked as completed!");
        }
    }

    /**
     * Unmarks a task if it was marked.
     *
     * @param showText Print Ui for unmark onto terminal if true.
     * @throws HamyoException If task was already unmarked.
     */
    public void unmark(boolean showText) throws HamyoException {
        if (this.isMarked) {
            this.isMarked = false;
            if (showText) {
                Ui.setStringUnmarkTask(this);
            }
        } else {
            throw new HamyoException("This task was already marked as incomplete!");
        }
    }

    /**
     * Converts the Task representation to a standardised format for the
     * printing of TaskList.
     *
     * @return Formatted String to represent the Task.
     */
    @Override
    public String toString() {
        return (this.isMarked ? "[X]" : "[ ]") + " " + task;
    }


    /**
     * Converts the Task representation to a standardised format for the loading
     * and storing of tasks in files.
     *
     * @return Formatted String to represent the Task.
     */
    public String toFileFormat() {
        return (this.isMarked ? "1" : "0") + " | " + this.task;
    }

    /**
     * Verify if the Task occurs on the specified date. Always returns true.
     *
     * @param  date The specified date to check.
     * @return true always.
     */
    public boolean occursToday(LocalDate date) {
        return true;
    }
}
