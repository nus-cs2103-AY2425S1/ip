package skd;

import task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm skd.SKD");
        System.out.println("     What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showByeMessage() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    public void showTaskList(List<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showAddedTask(Task task, int taskCount) {
        showLine();
        task.printTaskAddedMessage(taskCount);
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }
}