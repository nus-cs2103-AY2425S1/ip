package presentation;

import java.util.ArrayList;

import tasks.Note;
import tasks.Task;

/** A class that display to the user */
public class Ui {

    /** Creates an instance of Ui object. */
    public Ui() {
    }

    /**
     * Shows greeting dialog when the application starts.
     */
    public String greetDialog() {
        return "Yo! I'm Luke.\n" +
        "How's it hanging?";
    }

    /**
     * Shows closing dialog when the application ends.
     */
    public String closeDialog() {
        return "Aight, Cya later.";
    }

    public String addNoteDialog(Note t) {
        return String.format("Aight, I've added the note:\n" + t);
    }

    public String deleteNoteDialog(Note n) {
        return String.format("Aight, this note is removed:\n" + n);
    }

    /**
     * Shows a dialog when a task is added.
     * @param t Is the task that is added.
     * @param size Is the number of tasks after adding the new task.
     */
    public String addTaskDialog(Task t, int size) {
        return String.format("Got it. I've added this task:\n+" +
                t + "\n" +
                "Now you have %d tasks in the list.\n", size);
    }

    /**
     * Shows a dialog when a task is deleted.
     * @param t Is the task to be deleted.
     * @param size Is the number of tasks after deleting the task.
     */
    public String deleteTaskDialog(Task t, int size) {
        return String.format("Noted. I've removed this task:\n" +
                "%s" +
                "Now you have %d tasks in the list.", t.toString(), size);
    }

    public String indexOutOfRangeDialog(int index) {
        return String.format("Dude, %d is out of range yo.", index);
    }

    /**
     * Shows a dialog when listing all the tasks.
     */
    public String listTaskDialog(ArrayList<Task> tasks) {
        String tasksList = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksList += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return "Here are the tasks in your list:\n" + tasksList;
    }

    /**
     * Shows a dialog after marking a task.
     * @param t Is the task being marked.
     */
    public String markDialog(Task t) {
        return String.format("Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Shows a dialog after unmarking a task.
     * @param t Is the task being unmarked.
     */
    public String unMarkDialog(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n" + t);
    }

    /**
     * Prints the found task.
     *
     * @param foundTasks Is the task found.
     */
    public String findDialog(ArrayList<Task> foundTasks) {
        String s = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            s += String.format("%d. %s\n", i + 1, foundTasks.get(i).toString());
        }
        return s;
    }
}
