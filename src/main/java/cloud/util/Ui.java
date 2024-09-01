package cloud.util;

import java.util.Scanner;

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

    public void showMarked(String taskStatus) {
        System.out.println("cloud.task.Task marked as done!\n" + taskStatus);
    }

    public void showUnmarked(String taskStatus) {
        System.out.println("cloud.task.Task marked as not done\n" + taskStatus);
    }

    public void showList(String list) {
        System.out.println("Here is a list of all your tasks:\n" + list);
    }

    public void showMatching(String list) {
        System.out.println("Here are the matching tasks in your list:\n" + list);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        System.out.print(">> ");
        return scanner.nextLine().strip();
    }
}
