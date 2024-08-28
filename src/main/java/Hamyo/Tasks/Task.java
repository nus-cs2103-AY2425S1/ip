package Hamyo.Tasks;

import Hamyo.Misc.HamyoException;
import Hamyo.Misc.UI;

import java.time.LocalDate;

/**
 * Represents a Task.
 *
 * @author Han Yu
 */
public class Task {

    private final String task;
    private boolean marked;

    /**
     * Constructor for Task instance.
     * @param task Description for the task, stored in String array.
     *             [Description].
     */
    public Task(String[] task) {
        this.task = task[0];
    }


    /**
     * Marks a task if it was unmarked.
     *
     * @param showText Print UI for mark onto terminal if true.
     * @throws HamyoException If task was already marked.
     */
    public void mark(boolean showText) throws HamyoException {
        if (!marked) {
            marked = true;
            if (showText) {
                UI.markTask(this);
            }
        } else {
            throw new HamyoException("This task was already marked as completed!");
        }
    }
  
    /**
     * Unmarks a task if it was marked.
     *
     * @param showText Print UI for unmark onto terminal if true.
     * @throws HamyoException If task was already unmarked.
     */
    public void unmark(boolean showText) throws HamyoException {
        if (this.marked) {
            marked = false;
            if (showText) {
                UI.unmarkTask(this);
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
        return (marked ? "[X]" : "[ ]") + " " + task;
    }


    /**
     * Converts the Task representation to a standardised format for the loading
     * and storing of tasks in files.
     *
     * @return Formatted String to represent the Task.
     */
    public String toFileFormat() {
        return (this.marked ? "1" : "0") + " | " + this.task;
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
