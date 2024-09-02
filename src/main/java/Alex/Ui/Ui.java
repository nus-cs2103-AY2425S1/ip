package Alex.Ui;
import java.util.ArrayList;
import java.util.Scanner;

import Alex.Task.Task;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public Ui() {
        // No need to pass the task list
    }

    public void showWelcome() {
        printDividerWithMessage("Hello! I'm Alex, your friendly assistant!\nWhat can I do for you?");
    }

    public void showError(String message) {
        printDividerWithMessage(message);
    }

    public void showLoadingError() {
        printDividerWithMessage("Error loading tasks. Starting with an empty task list.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTaskDeleted(Task task) {
        System.out.println("Task deleted: " + task);
    }

    public void printJoke() {
        printDividerWithMessage("Why did the scarecrow win an award? Because he was outstanding in his field!");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void printTaskStatusChange(String message, int index) {
        System.out.println(message + " " + (index + 1));
    }

    private void printDividerWithMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
}
