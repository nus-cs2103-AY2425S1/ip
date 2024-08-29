package ollie;

import ollie.exception.OllieException;
import ollie.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }
    public void showGreeting() {
        Ui.printResponse("Hello! I'm Ollie!\nWhat can I do for you?");
    }

    public void showExit() {
        Ui.printResponse("Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        Ui.printResponse(tasks.toString());
    }

    public void showAddTask(Task task, int size) {
        Ui.printResponse("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }
    public void showRemoveTask(Task task, int size) {
        Ui.printResponse("Noted. I've removed this task:\n  "
                + task.toString()
                + "\nNow you have " +size + " tasks in the list.");
    }

    public void showMarkAsDone(Task task) {
        Ui.printResponse("Nice! I've marked this task as done:\n  " + task.toString());
    }

    public void showMarkAsUndone(Task task) {
        Ui.printResponse("OK, I've marked this task as not done yet:\n  " + task.toString());
    }

    public void showLoadingError(OllieException e) {
        System.out.println("Loading Error:\n" + e.getMessage());
    }

    public static void printResponse(String s) {
        System.out.println("____________________________________________________________\n"
                + s
                + "\n____________________________________________________________");
    }
}
