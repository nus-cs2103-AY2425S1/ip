import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Yapper");
        System.out.println(" What can I do for you Boss?");
        System.out.println("____________________________________________________________");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }

    public void printLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println(" Error loading tasks from file!");
        System.out.println("____________________________________________________________");
    }

    public void printGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye Boss!. Yapper wants to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it Boss! I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Okay bOSS ur task got removed");
        System.out.println("   " + task);
        System.out.println(" Okay Boss! Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list Boss!:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void printTaskMarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice Boss! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    public void printTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK Boss! I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }
}
