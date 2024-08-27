import java.util.ArrayList;

public class Ui {
    static private String name;

    Ui(String name) {
        Ui.name = name;
    }

    private void printDash() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    public void showGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showAddedTask(Task task, int newSize) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    public void showRemovedTask(Task task, int newSize) {
        System.out.println("Noted. I've removed this task: " + task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    public void showNotDoneTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    public void notifyInvalidCommand(Task task) {
        System.out.println("Invalid Command");
    }
}
