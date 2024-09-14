package mysutong;

import mysutong.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm mysutong.MySutong");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
        }
        showLine();
    }

    public void showSearchResults(List<Task> tasks) {
        showLine();
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        showLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
