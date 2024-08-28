package tick.ui;

import java.util.Scanner;

import tick.tasks.Task;
import tick.storage.TaskList;

public class Ui {
    public static final String separator = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLine() {
        System.out.println(Ui.separator);
    }

    public void showWelcome() {
        System.out.println(Ui.separator);
        System.out.println("System starting up...");
        System.out.println("Brrt brrt! I'm Tick!");
        System.out.println("What can I do for you?");
        System.out.println(Ui.separator);
    }

    public void showGoodbye() {
        System.out.println("System shutting down...");
        System.out.println("Bye bye, see you next time!");
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading data from file.");
    }

    public void showLoadingSuccess() {
        System.out.println("File loaded successfully!");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Ding ding! I've marked this task as done:");
        System.out.println(task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as undone:");
        System.out.println(task);
    }

    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.printf("%d.%s\n", i + 1, tasks.getTask(i));
            }
        }
    }

    public void showError(String message) {
        System.out.println("ERROR ERROR! " + message);
    }
}
