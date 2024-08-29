import java.util.Scanner;

public class Ui {
    private static final String SPACER = "____________________________________________________________\n";
    private static final String INDENT = "    ";

    public void showWelcome() {
        System.out.println(SPACER + "Hello! I'm Moody!\nWhat can I do for you?\n" + SPACER);
    }

    public void showGoodbye() {
        System.out.println(SPACER + "Bye. Hope to see you again soon!\n" + SPACER);
    }

    public void showTaskList(TaskList tasks) {
        System.out.print(SPACER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(SPACER);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(SPACER + "Got it. I've added this task:\n"
                + INDENT + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println(SPACER + "Noted. I've removed this task:\n" + INDENT + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.\n" + SPACER);
    }
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showLoadingSuccess() {
        System.out.println("Tasks loaded successfully!");
    }

    public void showError(String message) {
        System.out.println(SPACER + message + SPACER);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(SPACER);
    }
}
