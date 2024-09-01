import java.util.List;
import java.util.Scanner;
public class Ui {
    Scanner detector;
    public Ui() {
        this.detector = new Scanner(System.in);
    }
    public void greet() {
        System.out.println("Hello! I'm Gary\nWhat can I do for you?");
    }
    public void goodbye() {
        this.detector.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }
    public void unmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }
    public void deleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task from the list:\n " + task.toString() +
                "\nNow you have " + size + " tasks in the list.\n");
    }
    public void addTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public void showTaskLists(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.getTask(i));
        }
    }
    public String read() {
        return detector.nextLine();
    }
    public void showError(String message) {
        System.out.println(message);
    }

}
