package fence.ui;

import fence.task.Task;
import fence.tasklist.TaskList;

/**
 * Represents a user interface that is in charge of interactions with the user.
 */
public class Ui {

    /**
     * Returns the default greeting.
     * @return Default greeting message.
     */
    public String greet() {
        return "nihao! I'm Fence |=|=|=|=|=|";
    }

    /**
     * Returns the default goodbye message.
     * @return Default goodbye message.
     */
    public String exit() {
        return "have good day :)";
    }

    /**
     * Returns message containing the tasks in the task list line by line. The starting index of the list is 1.
     * @param tasks List of tasks currently.
     * @return Message displaying the task list.
     */
    public String list(TaskList tasks) {
        String list = "!plans:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            list += i + 1 + ". " + tasks.getTask(i) + "\n";
        }
        return list;
    }

    /**
     * Returns message containing the size of the task list.
     * @param size Size of the task list.
     * @return Message displaying the size of the task list.
     */
    public String count(int size) {
        return "Now you have " + size + ((size == 1) ? " item " : " items ") + "in the list.";
    }

    /**
     * Returns the default message if command is not recognised.
     * @return Default message for unrecognised command.
     */
    public String printUnknownCommand() {
        return "fence is programmed to track your tasks and has long lost all ability " + "to do other things ";
    }

    /**
     * Returns the error message for an input with invalid format.
     * @return Error message for input with invalid format.
     */
    public String printMissingFieldError() {
        return "doing nothing (field missing/invalid format)";
    }

    /**
     * Returns the error message for a date with invalid format.
     * @return Error message for date with invalid format.
     */
    public String printInvalidDateError() {
        return "fence only understands yyyy-mm-dd";
    }

    /**
     * Returns the error message for a number with invalid format.
     * @return Error message for number with invalid format.
     */
    public String printInvalidNumberError() {
        return "fence has only learnt numerical integers";
    }

    /**
     * Returns the error message for a data file with invalid format.
     * @return Error message for data file with invalid format.
     */
    public String printLoadingError() {
        return "Data file corrupted";
    }

    /**
     * Returns the default message that a task has been added to the task list.
     * @param task Task that was added.
     * @return Message acknowledging that said task has been added.
     */
    public String add(Task task) {
        return "added: " + task;
    }

    /**
     * Returns the default message that a task has been marked as done.
     * @param task Task that was marked complete.
     * @return Message acknowledging that said task has been marked as done.
     */
    public String mark(Task task) {
        return "good job\n" + task;
    }

    /**
     * Returns the default message that a task has been marked as undone.
     * @param task Task that was marked incomplete.
     * @return Message acknowledging that said task has been marked as undone.
     */
    public String unmark(Task task) {
        return "huh?\n" + task;
    }

    /**
     * Returns the default message that a task has been removed.
     * @param task Task that was deleted.
     * @return Message acknowledging that said task has been removed.
     */
    public String delete(Task task) {
        return "removed: " + task + "\n" + "(we never make plans)";
    }
}
