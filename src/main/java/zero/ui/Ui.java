package zero.ui;

import java.util.Scanner;
import zero.exception.ZeroException;
import zero.task.Task;
import zero.task.TaskList;

public class Ui {
    private Scanner scanner;
    private boolean isDone = false;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm zero.Zero");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Baibai!");
        System.out.println("____________________________________________________________");
        this.isDone = true;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println(" oopsie, " + message);
        showLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showTaskDeleted(Task task, int taskCount) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        showLine();
    }

    public void showTaskMarked(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    public void listTasks(TaskList tasks) throws ZeroException {
        showLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        showLine();
    }

    public boolean isDone() {
        return this.isDone;
    }
}
