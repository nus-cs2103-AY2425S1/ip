import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm " + "Meow" + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
