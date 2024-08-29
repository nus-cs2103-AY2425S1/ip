import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?\n");
    }

    public void showLine() {
        System.out.println("______________________________________");
    }

    public void showGoodbye() {
        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println("    OOPS!!! " + message);
    }

    public void showAddedTask(Task task, int size) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + size + " task(s) in the list.");
    }

    public void showDeletedTask(Task task, int size) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + size + " task(s) in the list.");
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showMarkTaskAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showMarkTaskAsNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showTasksOnDate(String date, TaskList tasks) {
        System.out.println("    Here are the tasks on " + date + ":");
        for (Task task : tasks.getTasks()) {
            System.out.println("    " + task);
        }
    }
}
