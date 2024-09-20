package presentation;

import java.util.ArrayList;

import tasks.Note;
import tasks.Task;

/** A class that display to the user */
public class Ui {

    /** Returns a Ui object. */
    public Ui() {
    }

    /**
     * Returns greeting dialog.
     */
    public String greetDialog() {
        return "Yo! I'm Luke.\n"
                + "How's it hanging?";
    }

    /**
     * Returns closing dialog.
     */
    public String closeDialog() {
        return "Aight, Cya later.";
    }

    /**
     * Returns note added dialog.
     *
     * @param t Note that is to be added.
     * @return Note added dialog.
     */
    public String addNoteDialog(Note t) {
        return String.format("Aight, I've added the note:\n" + t);
    }

    /**
     * Returns task added dialog.
     *
     * @param t Task that is added.
     * @param size The number of tasks left after adding the new task.
     */
    public String addTaskDialog(Task t, int size) {
        return String.format("Got it. I've added this task:\n"
                + t
                + "\n"
                + "Now you have %d tasks in the list.\n", size);
    }

    /**
     * Returns a task deleted dialog.
     *
     * @param t Task to be deleted.
     * @param size The number of tasks left after deleting the task.
     */
    public String deleteTaskDialog(Task t, int size) {
        return String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", t.toString(), size);
    }

    /**
     * Returns index out of bound error message.
     *
     * @param index Index that is out of bound.
     * @return Index out of bound String error message.
     */
    public String indexOutOfRangeDialog(int index) {
        return String.format("Dude, %d is out of range yo.", index);
    }

    /**
     * Returns a dialog of list of tasks.
     */
    public String listTaskDialog(ArrayList<Task> tasks) {
        String tasksList = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksList += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return "Here are the tasks in your list:\n" + tasksList;
    }

    /**
     * Returns mark task dialog
     *
     * @param t Task being marked.
     */
    public String markDialog(Task t) {
        return String.format("Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Returns unmark task dialog.
     *
     * @param t Task being unmarked.
     */
    public String unMarkDialog(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n" + t);
    }

    /**
     * Returns found task dialog.
     *
     * @param foundTasks Is the list of tasks found.
     */
    public String findDialog(ArrayList<Task> foundTasks) {
        String s = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            s += String.format("%d. %s\n", i + 1, foundTasks.get(i).toString());
        }
        return s;
    }
}
