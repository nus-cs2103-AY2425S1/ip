import java.util.ArrayList;
public class Ui {
    private static final String LINE = "----------------------------------------";

    public void printWelcome() {
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public void printTaskAdded(Task task, int totalTasks) {
        printLine();
        System.out.println("added: " + task);
        System.out.println(totalTasks + " task(s) in the list");
        printLine();
    }

    public void printTaskDeleted(Task task, int totalTasks) {
        printLine();
        System.out.println("deleted: " + task);
        System.out.println(totalTasks + " task(s) left in the list");
        printLine();
    }

    public void printTaskList(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printLine();
    }

    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Nice! I have marked this task as done:\n" + task);
        printLine();
    }

    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("OK, I have marked this task as not done yet\n" + task);
        printLine();
    }
}

