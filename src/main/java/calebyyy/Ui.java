package calebyyy;
import java.util.ArrayList;

import calebyyy.tasks.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
    }

    /**
     * Prints a message indicating that a task has been added.
     * @param task
     * @param taskCount
     */
    public void addTaskMessage(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void byeMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message indicating that a task has been deleted.
     * @param task
     * @param taskCount
     */
    public void deleteTaskMessage(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints list of tasks.
     * @param taskList
     */
    public void listTasksMessage(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTasks().get(i));
        }
    }

    /**
     * Prints list of tasks with keyword.
     * @param tasks
     */
    public void listTasksWithKeyword(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }
}
