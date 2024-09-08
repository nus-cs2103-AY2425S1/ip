package cloud.util;

import java.util.Scanner;

/**
 * Handles the response formatting for Cloud replies
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        System.out.println(
                "Hello! I'm Cloud\n" +
                        "What can I do for you?"
        );
    }

    public void showLine() {
        System.out.println(
                "____________________________________________________________"
        );
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo(String message) {
        System.out.println(message);
    }

    public String showMarked(String taskStatus) {
        return String.format("Task marked as done!\n" + taskStatus);
    }

    public String showUnmarked(String taskStatus) {
        return String.format("Task marked as not done\n" + taskStatus);
    }

    public String showList(String list) {
        String response = String.format("Here is a list of all your tasks:\n" + list);
        return response;
    }

    public String showMatching(String listString) {
        if (listString.length() == 0) {
            return "There are no matching tasks found";
        }
        return String.format("Here are the matching tasks in your list:\n" + listString);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String showAddedTask(TaskList taskList) {
        String response = String.format(
                "Added the following task:\n\t%s\nNow you have %d task%s in the list\n",
                taskList.getLatestTask(),
                taskList.getTaskCount(),
                taskList.getTaskCount() != 1 ? "s" : ""
        );
        return response;
    }

    public String showDeletedTask(TaskList taskList, String deletedTaskStatus) {
        String response = String.format(
                "Removed the following task:\n\t%s\n%d task%s remaining\n",
                deletedTaskStatus,
                taskList.getTaskCount(),
                taskList.getTaskCount() != 1 ? "s" : ""
        );
        return response;
    }

    public String readCommand() {
        System.out.print(">> ");
        return scanner.nextLine().strip();
    }
}
