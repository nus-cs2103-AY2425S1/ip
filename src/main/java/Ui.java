import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Assistinator");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showResponse(String response) {
        System.out.println("______________________________________________");
        System.out.println(response);
        System.out.println("______________________________________________");
    }

    public void showError(String message) {
        System.out.println("______________________________________________");
        System.out.println(message);
        System.out.println("______________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }
}
