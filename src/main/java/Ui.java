import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String NAME = "Rose";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showOpening() {
        System.out.println("    " + HORIZONTAL_LINE + "\n    Hi gorgeous! I'm " + NAME + "~~\n"
                + "    How can I help you today?\n    " + HORIZONTAL_LINE);
    }

    public void showClosing() {
        System.out.println("    See you~~ Don't forget to drink some water ^_^");
    }

    public void showLoadingError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    OOPS!!! There was an error loading the task list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }

    public void showLine() {
        System.out.println("    " + HORIZONTAL_LINE);
    }

    public void display(String message) {
        System.out.println("    " + message);
    }

    public void showMark(Task task) {
        this.display("Marked as done : ");
        this.display(task.toString());
    }

    public void showUnmark(Task task) {
        this.display("Marked as not done : ");
        this.display(task.toString());
    }

    public void showDelete(Task task, int size) {
        this.display("Noted. I've removed this task :");
        this.display(task.toString());
        this.display(String.format("Now you have %d task in the list.", size));
    }

    public void showAdd(Task task, int size) {
        this.display("Got it. I've added this task :");
        this.display("  " + task.toString());
        this.display(String.format("Now you have %d tasks in the list.", size));
    }
}
