package susan.ui;

import susan.task.Task;
import susan.task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("~~~");
    }

    public void showWelcome() {
        System.out.println("Hi there, I'm Susan!");
        System.out.println("How may I help you be productive today?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Good bye, slay the day!");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(tasks.printList());
    }

    public void showAddTask(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" You have " + size + " task(s) in the list.");
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" You have " + size + " task(s) in the list.");
    }
}
