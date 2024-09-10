package presentation;

import java.util.ArrayList;

import tasks.Task;

/** A class that display to the user */
public class Ui {

    /** Creates an instance of Ui object. */
    public Ui() {
    }

    /**
     * Shows greeting dialog when the application starts.
     */
    public void greetDialog() {
        String logo =
                " __\n"
                        + "| |    _   _ _  _____\n"
                        + "| |   | | | | |/ / _ \\\n"
                        + "| |__ | |_| |   <  __/\n"
                        + "|____| \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Yo! I'm Luke.\nHow's it hanging?");
    }

    /**
     * Shows closing dialog when the application ends.
     */
    public void closeDialog() {
        System.out.println("Aight, Cya later.");
    }

    /**
     * Shows a dialog when a task is added.
     * @param t Is the task that is added.
     * @param size Is the number of tasks after adding the new task.
     */
    public void addTaskDialog(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Shows a dialog when a task is deleted.
     * @param t Is the task to be deleted.
     * @param size Is the number of tasks after deleting the task.
     */
    public void deleteTaskDialog(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Shows a dialog when listing all the tasks.
     */
    public void listTaskDialog() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Shows a dialog after marking a task.
     * @param t Is the task being marked.
     */
    public void markDialog(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Shows a dialog after unmarking a task.
     * @param t Is the task being unmarked.
     */
    public void unMarkDialog(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
    }

    /**
     * Prints the found task.
     *
     * @param foundTasks Is the task found.
     */
    public void findDialog(ArrayList<Task> foundTasks) {
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, foundTasks.get(i).toString());
        }
    }
}
