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
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Print task added message.
     * @param task
     * @param taskCount
     */
    public void addTaskMessage(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Print goodbye message.
     */
    public void byeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Print task deleted message.
     * @param task
     * @param taskCount
     */
    public void deleteTaskMessage(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        assert taskCount >= 0 : "Task count cannot be negative";
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints list of tasks.
     * @param taskList
     */
    public void listTasksMessage(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTasks().get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints list of tasks with keyword.
     * @param tasks
     */
    public void listTasksWithKeyword(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Test assertions in Ui class.
     * @param args
     */
    public static void main(String[] args) {
        // Enable assertions
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);

        Ui ui = new Ui();
        Task validTask = new Task("Valid Task");
        int validTaskCount = 1;
        TaskList validTaskList = new TaskList(ui);
        ArrayList<Task> validTasks = new ArrayList<>();
        validTasks.add(new Task("Valid Task"));

        // Test addTaskMessage
        try {
            ui.addTaskMessage(validTask, validTaskCount);
            System.out.println("addTaskMessage with valid parameters passed.");
        } catch (AssertionError e) {
            System.out.println("addTaskMessage with valid parameters failed.");
        }

        try {
            ui.addTaskMessage(null, validTaskCount);
            System.out.println("addTaskMessage with null task failed.");
        } catch (AssertionError e) {
            System.out.println("addTaskMessage with null task passed.");
        }

        try {
            ui.addTaskMessage(validTask, -1);
            System.out.println("addTaskMessage with negative task count failed.");
        } catch (AssertionError e) {
            System.out.println("addTaskMessage with negative task count passed.");
        }

        // Test deleteTaskMessage
        try {
            ui.deleteTaskMessage(validTask, validTaskCount);
            System.out.println("deleteTaskMessage with valid parameters passed.");
        } catch (AssertionError e) {
            System.out.println("deleteTaskMessage with valid parameters failed.");
        }

        try {
            ui.deleteTaskMessage(null, validTaskCount);
            System.out.println("deleteTaskMessage with null task failed.");
        } catch (AssertionError e) {
            System.out.println("deleteTaskMessage with null task passed.");
        }

        try {
            ui.deleteTaskMessage(validTask, -1);
            System.out.println("deleteTaskMessage with negative task count failed.");
        } catch (AssertionError e) {
            System.out.println("deleteTaskMessage with negative task count passed.");
        }

        // Test listTasksMessage
        try {
            ui.listTasksMessage(validTaskList);
            System.out.println("listTasksMessage with valid parameters passed.");
        } catch (AssertionError e) {
            System.out.println("listTasksMessage with valid parameters failed.");
        }

        try {
            ui.listTasksMessage(null);
            System.out.println("listTasksMessage with null task list failed.");
        } catch (AssertionError e) {
            System.out.println("listTasksMessage with null task list passed.");
        }

        // Test listTasksWithKeyword
        try {
            ui.listTasksWithKeyword(validTasks);
            System.out.println("listTasksWithKeyword with valid parameters passed.");
        } catch (AssertionError e) {
            System.out.println("listTasksWithKeyword with valid parameters failed.");
        }

        try {
            ui.listTasksWithKeyword(null);
            System.out.println("listTasksWithKeyword with null tasks failed.");
        } catch (AssertionError e) {
            System.out.println("listTasksWithKeyword with null tasks passed.");
        }
    }
}
