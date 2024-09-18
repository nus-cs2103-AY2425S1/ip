package utilities;

import tasks.Task;

/**
 * The Ui class handles interaction with the user by displaying messages and task information.
 */
public class Ui {

    /**
     * Constructs a Ui object to handle user interactions.
     */
    public Ui() {

    }

    /**
     * Displays a welcome message when the chatbot is initialized.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bigmouth\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     * @return The response of added task
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n " +
                task + "\n "+"Now you have " + taskCount
                + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks remaining in the list.
     * @return The response of removed task
     */
    public String showTaskRemoved(Task task, int taskCount) {
        return "Noted. I've removed this task:\n " +
                task + "\n " + "Now you have " + taskCount
                + " tasks in the list.";
    }


    /**
     * Returns all tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A string showing all lists in task
     */
    public String showTaskList(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += " " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return response;
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return task marked message
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n " + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as done.
     * @return task unmarked message
     */
    public String showTaskUnmarked(Task task) {
        return "Ok, I've unmarked this task:\n " + task;
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search.
     * @return string listing out matching tasks
     */
    public String showMatchingTasks(TaskList tasks, int mainListSize) {
        String response = "Here are the matching tasks in your list:\n";

        if (tasks.isEmpty()) {
            response = "There are no matching tasks in your list.";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                response += " " + (i + 1) + "." + tasks.get(i)
                + "\n";
            }
        }
        return response + "Now you have " + mainListSize
                + " tasks in the list.";
    }
}
