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
        return "!plans:\n" + tasks;
    }

    /**
     * Returns message containing the size of the task list.
     * @param tasks List of tasks currently.
     * @return Message displaying the size of the task list.
     */
    public String count(TaskList tasks) {
        int size = tasks.getSize();
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
        return "Data file corrupted. Any changes will not be saved";
    }

    /**
     * Returns the error message for an IO error, usually if the program lacks the necessary permission to verify,
     * create, delete or otherwise modify a file or directory.
     * @return Error message for insufficient permissions.
     */
    public String printPermissionError() {
        return "fence couldn't check if data file already exists/create, open or modify the file. "
                + "Changes may be reflected in the tasklist but not saved. Please check the necessary permissions";
    }

    /**
     * Returns the error message for an index that is outside of the range of current tasklist.
     * @return Error message if the given index is outside the range of current tasklist.
     */
    public String printInvalidIndexError() {
        return "fence couldn't find that task";
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

    /**
     * Returns the default reminder for incomplete deadline tasks due on the current day.
     * @param tasks List of incomplete deadline tasks due on the current day.
     * @return Message displaying list of incomplete deadline tasks due on the current day.
     */
    public String remind(TaskList tasks) {
        return (tasks.getSize() == 0 ? "Are you sure you didn't forget something?" : "Time to start: \n" + tasks);
    }
}
