package echo;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim().toLowerCase();
    }

    public void showWelcomeMessage() {
        String logo = "____________________________________________________________\n";
        System.out.println("Hello, I'm Echo\n" + logo);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye bye...");
    }

    public void showErrorMessage(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            int count = 1;
            for (Task task : tasks) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.");
    }

    public void showTaskRemoved(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.");
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }


}

