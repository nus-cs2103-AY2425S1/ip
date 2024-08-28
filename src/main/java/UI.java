import java.util.Scanner;

public class UI {
    private final Scanner input;

    public UI() {
        input = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Bob\nWhat can I do for you?");
        System.out.println();
    }

    public void showSeparator() {
        System.out.println("--------------------------------------------------");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showNewLineSeparator() {
        System.out.println("--------------------------------------------------\n");
    }

    public String getUserInput() {
        return input.nextLine();
    }
}

