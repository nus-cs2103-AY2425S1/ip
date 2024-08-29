import java.util.ArrayList;

public class Ui {
    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Pebble");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showTasksList(ArrayList<Task> tasksList) {
        showLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasksList.get(i).toString());
        }
        showLine();
    }

    public void showAddTask(Task task, int size) {
        showLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + size + " tasks in this list.");
        showLine();
    }

    public void showDeleteTask(Task task, int size) {
        showLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + size + " tasks in this list.");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    public void showMarkTask(Task task) {
        showLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
        showLine();
    }

    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
        showLine();
    }

    public void showInvalidTaskNumber() {
        showLine();
        System.out.println("    Invalid task number.");
        showLine();
    }

    public void showMessage(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }
}
