import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    private static final String NAME = "Bob";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(LINE);
        System.out.printf("Hello! I'm %s!\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void showGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(" Error: " + message);
    }

    public void showLoadingError() {
        System.out.println(" Error loading from file.");
    }
}