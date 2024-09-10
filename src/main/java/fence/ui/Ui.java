package fence.ui;

import fence.task.Task;
import fence.tasklist.TaskList;

/**
 * Represents a user interface that is in charge of interactions with the user.
 */
public class Ui {

    /**
     * Prints the default greeting.
     */
    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    /**
     * Prints the default goodbye message.
     */
    public void exit() {
        System.out.println("have good day :)");
    }

    /**
     * Prints out the tasks in the task list line by line. The starting index of the printed list is 1.
     * @param tasks List of tasks currently.
     */
    public void list(TaskList tasks) {
        System.out.println("!plans:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
    }

    /**
     * Prints out the size of the task list.
     * @param size Size of the task list.
     */
    public void count(int size) {
        System.out.println("Now you have " + size + ((size == 1) ? " item " : " items ") + "in the list.");
    }

    /**
     * Prints out the default message if command is not recognised.
     */
    public void printUnknownCommand() {
        System.out.println("fence is programmed to track your tasks and has long lost all ability "
                + "to do other things ");
    }

    /**
     * Prints out the error message for an input with invalid format.
     */
    public void printMissingFieldError() {
        System.out.println("doing nothing (field missing/invalid format)");
    }

    /**
     * Prints out the error message for a date with invalid format.
     */
    public void printInvalidDateError() {
        System.out.println("fence only understands yyyy-mm-dd");
    }

    /**
     * Prints out the error message for a number with invalid format.
     */
    public void printInvalidNumberError() {
        System.out.println("fence has only learnt numerical integers");
    }

    /**
     * Prints out the error message for a data file with invalid format.
     */
    public void printLoadingError() {
        System.out.println("Data file corrupted");
    }

    /**
     * Prints out the default message that a task has been added to the task list.
     * @param task Task that was added.
     */
    public void add(Task task) {
        System.out.println("added: " + task);
    }

    /**
     * Prints out the default message that a task has been marked as done.
     * @param task Task that was marked complete.
     */
    public void mark(Task task) {
        System.out.println("good job");
        System.out.println(task);
    }

    /**
     * Prints out the default message that a task has been marked as undone.
     * @param task Task that was marked incomplete.
     */
    public void unmark(Task task) {
        System.out.println("huh?");
        System.out.println(task);
    }

    /**
     * Prints out the default message that a task has been removed.
     * @param task Task that was deleted.
     */
    public void delete(Task task) {
        System.out.println("removed: " + task);
        System.out.println("(we never make plans)");
    }
}
