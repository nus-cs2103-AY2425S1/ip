package dgpt;

import java.util.List;

import dgpt.task.Task;
import dgpt.task.TaskList;

/**
 * The {@code Ui} class is responsible for generating the text-based user interface outputs
 * for various actions performed on a list of tasks.
 */
public class Ui {

    /**
     * Returns a formatted string that lists all the tasks in the given task list.
     *
     * @param taskList The list of tasks to display.
     * @return A formatted string representing the list of tasks.
     */
    public static String listUi(TaskList taskList) {
        StringBuilder output = new StringBuilder();

        output.append("Here are the following items in your list:\n");
        int index = 1;
        for (Task t : taskList.getTaskList()) {
            output.append(index)
                    .append(".")
                    .append(t)
                    .append("\n");
            index++;
        }

        return output.toString();
    }

    /**
     * Returns a formatted string indicating that the specified task has been marked as done.
     *
     * @param currTask The task that has been marked as done.
     * @return A formatted string indicating the task has been marked as done.
     */
    public static String markUi(Task currTask) {

        return "Nice! I've marked this task as done:\n"
                + currTask + "\n";
    }

    /**
     * Returns a formatted string indicating that the specified task has been unmarked as done.
     *
     * @param currTask The task that has been marked as not done.
     * @return A formatted string indicating the task has been marked as not done.
     */
    public static String unmarkUi(Task currTask) {

        return "Dgpt> OK, I've marked this task as not done yet:\n"
                + currTask + "\n";
    }

    /**
     * Returns a formatted string confirming the addition of a new task to the list.
     *
     * @param addedTask The task that has been added.
     * @param size The new size of the task list after adding the task.
     * @return A formatted string confirming the task addition.
     */
    public static String addTaskUi(Task addedTask, int size) {
        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have "
                + size
                + " tasks in the list.\n";
    }

    /**
     * Returns a formatted string representing the error message from an exception.
     *
     * @param e The exception to display the message for.
     * @return A formatted string of the exception message.
     */
    public static String errorUi(Exception e) {
        return "I'm sorry, there was an error. Error description: " + e.getMessage() + "\n";
    }

    /**
     * Returns a formatted string confirming the deletion of a task from the list.
     *
     * @param deletedTask The task that has been deleted.
     * @param size The new size of the task list after deleting the task.
     * @return A formatted string confirming the task deletion.
     */
    public static String deleteUi(Task deletedTask, int size) {
        return "OK, I've removed this task from the list:\n"
                + deletedTask + "\n"
                + "Now you have "
                + size
                + " tasks in the list.\n";
    }

    /**
     * Returns a formatted string listing all the tasks that match a find query.
     *
     * @param matchingTasks The list of tasks that match the find query.
     * @return A formatted string representing the matching tasks.
     */
    public static String findUi(List<Task> matchingTasks) {
        StringBuilder output = new StringBuilder();
        int index = 1;

        output.append("Here are the matching tasks in your list:\n");
        for (Task t : matchingTasks) {
            output.append(index)
                    .append(".")
                    .append(t.toString())
                    .append("\n");
            index++;
        }

        return output.toString();
    }

    /**
     * Returns a confirmation message that the task list has been successfully saved.
     *
     * @return A string indicating a successful save.
     */
    public static String saveUi() {
        return "successfully saved!";
    }

    /**
     * Returns a message indicating that the user's request was not recognized and lists
     * the supported commands.
     *
     * @return A formatted string listing the supported commands.
     */
    public static String unknownUi() {
        return "I do not recognise that request. These are the "
                + "following requests that are supported:\n"
                + "-list\n"
                + "-mark\n"
                + "-unmark\n"
                + "-todo\n"
                + "-deadline\n"
                + "-recurring\n"
                + "-event\n"
                + "-delete\n"
                + "-find\n"
                + "-save\n";
    }
}
