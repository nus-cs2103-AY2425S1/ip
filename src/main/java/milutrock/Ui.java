package milutrock;

import java.util.ArrayList;

import milutrock.tasks.Task;

/**
 * A user interface with methods for printing messages and task lists.
 */
public class Ui {
    private String name;
    private TaskList taskList;

    public Ui(String name, TaskList taskList) {
        this.name = name;
        this.taskList = taskList;
    }


    /**
     * Display a greeting message with the name of the assistant.
     */
    public void printBanner() {
        System.out.println("Hello! I'm " + this.name + "!");
        System.out.println("What can I do for you?");
    }

    /**
     * Print a line of underscores as a visual break.
     */
    public void printLineBreak() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print a farewell message.
     */
    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the task at a specific index in the task list.
     * 
     * @param i Index of the task to print.
     */
    public void printTask(int i) {
        System.out.println("  " + this.taskList.getTaskAtIndexAsString(i));
    }

    /**
     * Print out the tasks in a task list with their corresponding indices.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.getNumberOfTasks(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.getTaskAtIndexAsString(i));
        }
    }

    /**
     * Print a message indicating the task has been marked as done, followed by the
     * details of the task.
     * 
     * @param i Index of the task marked as done.
     */
    public void printMarkMessage(int i) {
        System.out.println("Nice! I've marked this task as done:");
        this.printTask(i);
    }

    /**
     * Print a message indicating the task has been marked as not done yet, followed by
     * the task details.
     * 
     * @param i Index of the task marked as not done yet.
     */
    public void printUnmarkMessage(int i) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.printTask(i);
    }
    
    /**
     * Print a message confirming the removal of a task and displays the
     * updated number of tasks in the list.
     * 
     * @param task The task being deleted.
     */
    public void printDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.taskList.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Print a message confirming the addition of a task to a list along
     * with the updated number of tasks in the list.
     */
    public void printAddMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.taskList.getTaskAtIndexAsString(this.taskList.getNumberOfTasks() - 1));
        System.out.println("Now you have " + this.taskList.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Print a list of tasks that match the search query with their corresponding index numbers.
     * 
     * @param tasks The list of tasks to print.
     */
    public void printFindMessage(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
