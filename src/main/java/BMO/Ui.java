package bmo;

import java.util.ArrayList;

import bmo.task.Task;

/**
 * Represents the user interface of BMO.
 */
public class Ui {

    /**
     * Prints the welcome message when the program starts.
     */
    public String printWelcome() {
        return  "Hello! I'm BMO!\nWhat can I do for you?\n";
    }

    /**
     * Prints the goodbye message when the program ends.
     */
    public String printGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the message when a task is added.
     *
     * @param task the task that was added
     * @param taskCount the number of tasks in the list
     */
    public String printTaskAdded(Task task, int taskCount) {
        assert task != null : "The task to be printed cannot be null";
        assert taskCount >= 0 : "The number of tasks cannot be negative";
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskCount
            + " tasks in the list.";
    }

    /**
     * Prints the message when a task is removed.
     *
     * @param task the task that was removed
     * @param taskCount the number of tasks in the list
     */
    public String printTaskRemoved(Task task, int taskCount) {
        assert task != null : "The task to be printed cannot be null";
        assert taskCount >= 0 : "The number of tasks cannot be negative";
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + taskCount
            + " tasks in the list.";
    }

    /**
     * Prints the message when a task is marked as completed.
     *
     * @param task the task that was marked as done
     */
    public String printTaskMarked(Task task) {
        assert task != null : "The task to be printed cannot be null";
        return "OK, I've marked this task as done:\n" + task;
    }

    /**
     * Prints the message when a task is unmarked as incomplete.
     *
     * @param task the task that was unmarked
     */
    public String printTaskUnmarked(Task task) {
        assert task != null : "The task to be printed cannot be null";
        return "Nice! I've unmarked this task:\n" + task;
    }

    /**
     * Prints the list of tasks in the task list.
     *
     * @param tasks the list of tasks
     */
    public String printList(ArrayList<Task> tasks) {
        assert tasks != null : "The task list to be printed cannot be null";
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            str += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Prints the message when a task is found.
     *
     * @param tasks the task that was found
     */
    public String printMatchingTasks(TaskList tasks) {
        assert tasks != null : "The task list to be printed cannot be null";
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            str += (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return str;
    }

    /*
     * Prints the message when the user tries to add a task that already exists in the list.
     */
    public String printUnsuccessfulAddTask() {
        return "Oh no! The task you specified already exists in the list:\n"
            + "Please add a different task.";
    }
}
