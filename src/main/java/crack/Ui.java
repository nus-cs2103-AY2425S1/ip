package crack;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Print welcome message
    public void showWelcome() {
        System.out.println(DIVIDER
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + DIVIDER);
    }

    // Print the divider line
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    // Print the goodbye message
    public void showGoodbye() {
        System.out.println("Goodbye!");
    }

    // Read user input
    public String readCommand() {
        System.out.print("You: ");
        return scanner.nextLine().trim();
    }

    // Display error message
    public void showError(String message) {
        System.out.println(DIVIDER + " Error: " + message + "\n" + DIVIDER);
    }

    // Show a general message
    public void showMessage(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    // Print task added message
    public void showTaskAdded(Task task, int size) {
        System.out.println(DIVIDER + " Got it. I've added this task:\n   " + task + "\n"
                + " Now you have " + size + " tasks in the list.\n" + DIVIDER);
    }

    // Close the scanner
    public void close() {
        scanner.close();
    }
}
