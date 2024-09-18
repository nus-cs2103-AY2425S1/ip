package pixy.ui;

import java.util.List;

import pixy.tasks.Task;

/**
 * Handles the user interaction with the chatbot.
 */
public class Ui {

    /**
     * Shows the welcome message from chatbot at the beginning.
     *
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Pixy.\nWhat can I do for you?\n");
    }

    /**
     * Displays the task after it has been added to the list in the desired format.
     *
     * @param task The task that has been added.
     * @param size The size of the task list.
     * @return Task added message.
     */
    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays message when task list is empty.
     *
     * @return List empty message.
     */
    public String showListEmpty() {
        return "List is Empty! Add tasks to list.";
    }

    /**
     * Prints the tasks sequentially from the task list.
     *
     * @param list The Task list.
     * @return List of tasks message.
     */
    public String showTasks(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            sb.append((i + 1) + ". " + task.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Displays the task with appropriate message after it has been marked.
     *
     * @param task The task to mark.
     * @return Task marked message.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Displays the task with appropriate message after it has been unmarked.
     *
     * @param task The task to unmark.
     * @return Task unmarked message.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Displays the task with appropriate message after it has been deleted.
     *
     * @param task The task to delete.
     * @param size The size of the task list.
     * @return Task deleted message.
     */
    public String showTaskRemoved(Task task, int size) {
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays the tasks which match the keyword inputted by user.
     *
     * @param matchedTasks List of matched Tasks.
     */
    public String showMatchedTasks(List<Task> matchedTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchedTasks.isEmpty()) {
            sb.append("No tasks found with matching description.\n");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task task = matchedTasks.get(i);
                sb.append((i + 1) + ". " + task.toString() + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * Displays the goodbye message.
     *
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the appropriate message when an error is encountered.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
