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
    public String printBanner() {
        return (
            "Hello! I'm " + this.name + "!\n"
            + "What can I do for you?\n"
        );
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
    private String printTask(int i) {
        return "  " + this.taskList.getTaskAtIndexAsString(i) + "\n";
    }

    /**
     * Print out the tasks in a task list with their corresponding indices.
     */
    public String printTaskList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.taskList.getNumberOfTasks(); i++) {
            output += (i + 1) + ". " + this.taskList.getTaskAtIndexAsString(i) + "\n";
        }

        return output;
    }

    /**
     * Print a message indicating the task has been marked as done, followed by the
     * details of the task.
     * 
     * @param i Index of the task marked as done.
     */
    public String printMarkMessage(int i) {
        return (
            "Nice! I've marked this task as done:\n"
            + this.printTask(i)
        );
    }

    /**
     * Print a message indicating the task has been marked as not done yet, followed by
     * the task details.
     * 
     * @param i Index of the task marked as not done yet.
     */
    public String printUnmarkMessage(int i) {
        return (
            "OK, I've marked this task as not done yet:\n"
            + this.printTask(i)
        );
    }
    
    /**
     * Print a message confirming the removal of a task and displays the
     * updated number of tasks in the list.
     * 
     * @param task The task being deleted.
     */
    public String printDeleteMessage(Task task) {
        return (
            "Noted. I've removed this task:" + "\n"
            + "  " + task + "\n"
            + "Now you have " + this.taskList.getNumberOfTasks() + " tasks in the list." + "\n"
        );
    }

    /**
     * Print a message confirming the addition of a task to a list along
     * with the updated number of tasks in the list.
     */
    public String printAddMessage() {
        return (
            "Got it. I've added this task:" + "\n"
            + "  " + this.taskList.getTaskAtIndexAsString(this.taskList.getNumberOfTasks() - 1) + "\n"
            + "Now you have " + this.taskList.getNumberOfTasks() + " tasks in the list." + "\n"
        );
    }

    /**
     * Print a list of tasks that match the search query with their corresponding index numbers.
     * 
     * @param tasks The list of tasks to print.
     */
    public String printFindMessage(ArrayList<Task> tasks) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i) + "\n";
        }

        return output;
    }
}
