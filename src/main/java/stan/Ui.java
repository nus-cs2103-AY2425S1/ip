package stan;

import stan.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Stan");
        System.out.println(" What can I do for you today?");
        showLine();
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays the tasks that match the find keyword.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showFindResults(List<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        showLine();
    }
}

