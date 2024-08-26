package calebyyy;
import calebyyy.Tasks.Task;
import java.util.ArrayList;

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
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

}
