import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "\t\t" + "_".repeat(50);
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greetUser() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHey there! I'm ChatterBox");
        System.out.println("\t\tWhat's on your plate today?");
        System.out.println(LINE_SEPARATOR);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHere's what you've got on your to-do list so far:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t\t\t" + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\t" + task.getDescription() + " is added to your list");
        System.out.println("\t\t" + task);
        System.out.println("\t\t" + "Now you have " + taskCount + " tasks in your list.");
        System.out.println(LINE_SEPARATOR);
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tSuper! Task marked as done:");
        System.out.println("\t\t" + task);
        System.out.println(LINE_SEPARATOR);
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tSure, task has been marked as not done:");
        System.out.println("\t\t" + task);
        System.out.println(LINE_SEPARATOR);
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\t\tNow you have " + taskCount + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    public void showErrorUnknownCommand() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE_SEPARATOR);
    }

    public void showErrorInvalidTaskNumber() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tInvalid task number.");
        System.out.println(LINE_SEPARATOR);
    }

    public void showErrorEmptyTodoDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of a todo cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    public void showErrorEmptyDeadlineDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of a deadline cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    public void showErrorEmptyEventDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of an event cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    public void showGoodbye() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tTake care! Looking forward to helping you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    public void showError(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\t" +message);
        System.out.println(LINE_SEPARATOR);
    }
}
