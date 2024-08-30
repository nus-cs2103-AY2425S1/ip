package myapp.ui;
import myapp.task.TaskList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Ruby\nWhat can I do for you?");
    }

    public void showResponse(String response) {
        System.out.println(response);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("An error occurred while loading tasks from the file.");
    }
}
