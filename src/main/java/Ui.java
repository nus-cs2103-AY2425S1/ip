import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "    _________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(Ui.LINE);
    }

    public void showLoadingError() {
        System.out.println("     An error occurred while reading from file!\nStarting with a new task list.");
    }

    public void showHello() {
        showLine();
        System.out.println("     Hello! I'm Spike\n     What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("     Your list is empty!");
        } else {
            System.out.println("     Here are the tasks in your list:\n");
            int counter = 0;
            for (Task item : tasks) {
                counter++;
                System.out.println("      " + counter + ". " + item.toString());
            }
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("     Got it. I've added this task:\n");
        System.out.println("      " + task.toString());
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("     Noted. I've removed this task:\n");
        System.out.println("      " + task.toString());
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(String taskString) {
        System.out.println("     Nice! I've marked this task as done:\n");
        System.out.println("      " + taskString);
    }

    public void showTaskUndone(String taskString) {
        System.out.println("     I've marked this task as not done yet:\n");
        System.out.println("      " + taskString);
    }

    public void showExceptionMessage(String message) {
        System.out.println("     " + "ERROR: " + message);
    }

    // Useful Strings
    public static final String TASKS_CLEARED = "     Noted. I've cleared all tasks in your list!";
    public static final String TASKS_SAVED = "     Your tasks have been saved!";
    public static final String TASKS_LOADED = "     Your tasks have been loaded!";
}
