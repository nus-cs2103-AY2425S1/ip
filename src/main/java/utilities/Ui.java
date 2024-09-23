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
     * Returns a message indicating that a task has been added.
     *
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     * @return The response of added task
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it, girl. I've added this task\u2728:\n " +
                task + "\n "+"Now you have " + taskCount
                + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @return The response of added task
     */
    public String helloResponse() {
        return "Aww... no one ever really greets me!" +
                " Thanks honey, you're too sweet. Now let's get stuff done, girl\u2728!";
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @return The response of added task
     */
    public String rudeResponse() {
        return "Girl! That's kinda mean :(." +
                " I just wanted to help...";
    }

    /**
     * Displays a message indicating that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks remaining in the list.
     * @return The response of removed task
     */
    public String showTaskRemoved(Task task, int taskCount) {
        return "Gotcha, girl. I've removed this task\u2728:\n " +
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
        StringBuilder response = new StringBuilder("Here's your list, honey\u2728:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            response.append(" ").append(i + 1).append(".").append(currentTask).append("\n");
        }
        return response.toString();
    }


    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return task marked message
     */
    public String showTaskMarked(Task task) {
        return "Good job, girboss! I've marked this task as done\u2728:\n " + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as done.
     * @return task unmarked message
     */
    public String showTaskUnmarked(Task task) {
        return "Aww, I've unmarked this task\u2728:\n " + task;
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param tasks The list of tasks that match the search.
     * @return string listing out matching tasks
     */
    public String showMatchingTasks(TaskList tasks, int mainListSize) {
        String response = "Here's the matching tasks in your list, girl\u2728:\n";

        if (tasks.isEmpty()) {
            response = "Honey, there's no task like that in your list :/ \u2728.";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                response += " " + (i + 1) + "." + tasks.get(i)
                + "\n";
            }
        }
        return response + "Now you have " + mainListSize
                + " tasks in the list.";
    }

    /**
     * Returns a help message showing all available commands and their format.
     *
     * @return The help message.
     */
    public String showHelp() {
        return "Here are the available commands, honey:\n" +
                "\u2728 list - Display all your tasks.\n" +
                "\u2728 mark <task number> - Mark a task as done.\n" +
                "\u2728 unmark <task number> - Unmark a task as not done.\n" +
                "\u2728 todo <description> - Add a new todo task.\n" +
                "\u2728 deadline <description> /by <M/d/yyyy HHmm> - Add a new deadline task.\n" +
                "\u2728 do <description> /after <M/d/yyyy HHmm> - Add a task to do after a certain time.\n" +
                "\u2728 event <description> /from <M/d/yyyy HHmm> /to <M/d/yyyy HHmm> - Add a new event.\n" +
                "\u2728 delete <task number> - Delete a task.\n" +
                "\u2728 find <keyword> - Find tasks with a keyword.\n" +
                "\u2728 bye - Exit the chatbot.";
    }

}
