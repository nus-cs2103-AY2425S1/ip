package carter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a response handler for various messages in the application.
 */
public class Response {

    /**
     * Returns a welcome message.
     *
     * @return A welcome message string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Carter" + "\n"
                + "What can I do for you";
    }

    /**
     * Returns an ending message.
     *
     * @return An ending message string.
     */
    public String showEndingMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a task when the task added to the list.
     *
     * @param description The task that was added.
     * @param numOfTasks The current number of tasks in the list.
     * @return A message confirming the task addition.
     * @throws IllegalArgumentException if description is null or numOfTasks is negative.
     */
    public String showTask(Task description, int numOfTasks) {
        assert description != null : "description should not be null";
        assert numOfTasks >= 0 : "number of tasks should be non-negative";

        return "Got it. I've added this task:" + "\n"
                + "    " + description.toString()
                + " Now you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return A message confirming the task has been marked as done.
     * @throws IllegalArgumentException if task is null.
     */
    public String showMarkTaskMessage(Task task) {
        assert task != null : "task should not be null";

        return "Nice! I've marked this task as done:"
                + "    " + task.toString();
    }

    /**
     * Returns a message when a task id marked as not done.
     *
     * @param task The task that was marked as not done yet.
     * @return A message confirming the task has been marked as not done yet.
     * @throws IllegalArgumentException if task is null.
     */
    public String showUnmarkTaskMessage(Task task) {
        assert task != null : "task should not be null";

        return "OK, I've marked this task as not done yet:" + "\n"
                + "    " + task.toString();
    }

    /**
     * Returns a list of task currently in the list.
     *
     * @param list The list of tasks to be displayed.
     * @return A message listing all tasks.
     * @throws IllegalArgumentException if list is null.
     */
    public String showList(Task... list) {
        assert list != null : "task list should not be null";

        StringBuilder s = new StringBuilder("Here are the tasks in your list:");
        s.append("\n").append(
                IntStream.range(0, list.length)
                        .mapToObj(i -> (i + 1) + "." + list[i].toString())
                        .collect(Collectors.joining("\n"))
        );
        return s.toString();
    }

    /**
     * Returns an error message when an error occur.
     *
     * @param message The error message to be displayed.
     * @return The formatted error message.
     * @throws IllegalArgumentException if message is null.
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";

        return "OOPS!! " + message;
    }


    /**
     * Returns a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     * @return A message confirming the task deletion.
     * @throws IllegalArgumentException if task is null or size is negative.
     */
    public String showTaskDeleted(Task task, int size) {
        assert task != null : "task should not be null";
        assert size >= 0 : "size should be non-negative";

        return " Noted. I've removed this task:" + "\n"
                + "    " + task + "\n"
                + " Now you have " + size + " tasks in the list.";
    }


    /**
     * Returns a message listing all tasks that match the keyword in the provided list.
     *
     * @param list The list of matching tasks to be displayed.
     * @return A message listing all matching tasks.
     * @throws IllegalArgumentException if list is null.
     */
    public String showMatchingTask(Task... list) {
        assert list != null : "task list should not be null";

        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:");

        s.append("\n").append(
                IntStream.range(0, list.length)
                        .mapToObj(i -> (i + 1) + "." + list[i].toString())
                        .collect(Collectors.joining("\n"))
        );
        return s.toString();
    }

    /**
     * Returns a message listing all archived tasks.
     * @param list task that have archived.
     * @return A message listing all archived tasks.
     */
    public String showArchiveTask(Task... list) {
        StringBuilder s = new StringBuilder("Here is your archived tasks:");

        s.append("\n").append(
                IntStream.range(0, list.length)
                        .mapToObj(i -> (i + 1) + "." + list[i].toString())
                        .collect(Collectors.joining("\n"))
        );

        return s.toString();
    }
}
