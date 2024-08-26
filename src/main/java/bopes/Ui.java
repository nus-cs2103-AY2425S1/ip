package bopes;

import java.util.Scanner;
import bopes.task.Task;
import bopes.task.TaskList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Bopes is a personal assistant that helps you manage your tasks.");
    }

    public String readCommand() {
        System.out.print("What can I do for you? ");
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            System.out.println("No input detected. Exiting...");
            System.exit(0);
            return "";
        }
        
    }

    public void showLine() {
        System.out.println("____________________________________________\n");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from storage.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found in storage.");
        } else {
            System.out.println("Here are your tasks:");
            tasks.printTasks();
        }
    }

    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param matchingTasks the TaskList containing tasks that match the search keyword
     */
    public void showFoundTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks match your search.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.getTasks().get(i));
            }
        }
    }

    public void showGoodbye() {
        System.out.println("Goodbye!");
    }
}
