package agave.Util;

import agave.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void makeLine() {
        System.out.println("____________________________________________________________");
    }

    public String showWelcome() {
        return "Hello! I am Agave. What can I do for you?\n";
    }

    public String showBye() {
        return "Bye! Hope to see you again soon!\n";
    }

    public String getUserInput(String input) {
        return scanner.nextLine();
    }

    public String showTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    public String showMarkedTask(Task task) {
        return "I've marked this task as completed:\n" + task.getStatus() + " " + task.getDescription() + "\n";
    }

    public String showUnmarkedTask(Task task) {
        return "I've unmarked this task:\n" + task.getStatus() + task.getDescription();

    }

    public String showError(String error) {
        return "Uh-oh: " + error + "\n";
    }

    public String showTaskAdded(Task task, int taskCount, ArrayList<Task> tasks) {
        return "Got it. I've added this task:\n" + task.getStatus() + " " + task.getDescription() + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n";
    }

    public void showLoadingError() {
        makeLine();
        System.out.println("Error loading tasks.");
        makeLine();
    }

    public String searchResult(ArrayList<Task> result) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            response.append((i + 1)).append(". ").append(result.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    public String showMessage(String response) {
        return response;
    }

    public String showSort() {
        return "Tasks sorted by deadline!";
    }
}