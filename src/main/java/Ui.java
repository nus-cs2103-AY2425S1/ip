import java.util.ArrayList;

public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n");
        printLine();
    }

    public void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printTaskAdded(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public void printTaskRemoved(Task task, int taskCount) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public void printTaskMarked(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    public void printTaskUnmarked(Task task) {
        printLine();
        System.out.println("Nice! I've unmarked this task:");
        System.out.println("  " + task);
        printLine();
    }

    public void printList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }
}