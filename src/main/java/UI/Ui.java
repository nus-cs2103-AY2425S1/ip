package UI;

import java.util.Scanner;

import Main.TaskList;
import Tasks.Task;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Reads the next line of user input
    public String readCommand() {
        return scanner.nextLine();
    }

    // Shows welcome message
    public void showWelcome() {
        showMessage("Hello! I am Pro Yapper!\nWhat can I do for you?");
    }

    // Shows goodbye message
    public void showGoodbye() {
        showMessage("Hope to see you again soon!");
    }

    // Shows general message
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Shows error message
    public void showError(String message) {
        System.err.println(message);
    }

    // Shows the list of tasks
    public void showTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            showMessage("No tasks in your list.");
            return;
        }
        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int lstNum = i + 1;
            Task next = taskList.get(i);
            showMessage(lstNum + ". " + next.toString());
        }
    }
}
