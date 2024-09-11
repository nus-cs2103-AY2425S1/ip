import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Naega");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(" Oops! " + message);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printTaskAdded(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int remainingTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + remainingTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}