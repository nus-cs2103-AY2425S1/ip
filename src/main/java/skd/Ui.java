package skd;

import skd.task.Deadline;
import skd.task.Event;
import skd.task.Task;
import skd.task.ToDo;
import skd.task.TaskType;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    /**
     * Creates Ui instance and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays welcome message when the chatbot starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm skd.SKD");
        System.out.println("     What can I do for you?");
        showLine();
    }

    /**
     * Reads command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays bye message when the chatbot exits.
     */
    public void showByeMessage() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays current list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays message showing task has been added.
     *
     * @param task The task that was added.
     * @param taskCount Current number of tasks.
     */
    public void showAddedTask(Task task, int taskCount) {
        showLine();
        task.printTaskAddedMessage(taskCount);
        showLine();
    }

    /**
     * Displays error message.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    /**
     * Displays tasks that match given keyword.
     *
     * @param tasks List of tasks to search .
     * @param keyword Keyword to search for in task descriptions.
     */
    public void showFoundTasks(List<Task> tasks, String keyword) {
        showLine();
        System.out.println("     Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                System.out.println("     " + (count++) + "." + task);
            }
        }
        if (count == 1) {
            System.out.println("     No matching tasks found.");
        }
        showLine();
    }
}