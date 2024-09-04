import java.util.Scanner;
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from Streams!");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("oops!!! no existing task file has been found :( starting with an empty task list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
