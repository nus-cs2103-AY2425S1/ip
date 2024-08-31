package muller.ui;

import muller.task.TaskList;
import muller.command.MullerException;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "____________________________________________________________";
        System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n" + logo);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        try {
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ": " + tasks.get(i - 1));
                }
            }
        } catch (MullerException e) {
            showError(e.getMessage());
        }
    }
}
