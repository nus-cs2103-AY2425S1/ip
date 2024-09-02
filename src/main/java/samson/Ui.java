package samson;

import samson.task.Task;
import samson.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Samson.Samson");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void goodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        // System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    public void showTaskAdded(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        showLine();
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
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

    public void showTaskNumInvalid() {
        showLine();
        System.out.println(" Task number out of range.");
        showLine();
    }
}

