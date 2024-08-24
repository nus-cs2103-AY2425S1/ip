import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void makeLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        makeLine();
        System.out.println("Hello! I am Agave.\n" +
                "What can I do for you?\n");
        makeLine();
    }

    public void showBye() {
        makeLine();
        System.out.println("Bye! Hope to see you again soon!\n");
        makeLine();
    }

    public String getUserInput(String input) {
        return scanner.nextLine();
    }

    public void showEcho(String echo) {
        makeLine();
        System.out.println(echo);
        makeLine();
    }

    public void showTasks(ArrayList<Task> tasks) {
        makeLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        makeLine();
    }

    public void showMarkedTask(Task task) {
        makeLine();
        System.out.println("I've marked this task as completed:\n" +
                task.getStatus() +
                task.getDescription());
        makeLine();
    }

    public void showUnmarkedTask(Task task) {
        makeLine();
        System.out.println("I've unmarked this task:\n" +
                task.getStatus() +
                task.getDescription());
        makeLine();
    }

    public void showError(String error) {
        makeLine();
        System.out.println("Uhoh: " + error);
        makeLine();
    }

    public void showTaskAdded(Task task, int taskCount, ArrayList<Task> tasks) {
        makeLine();
        System.out.println("Got it. I've added this task:\n" +
                task.getStatus() +
                task.getDescription());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        makeLine();
    }
}