import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private String logo = " ____    _    __     ______\n"
            + "|  _ \\  / \\   \\ \\   / / ___|\n"
            + "| | | |/ _ \\   \\ \\ / /|  _|\n"
            + "| |_| / ___ \\   \\ V / | |___\n"
            + "|____/_/   \\_\\   \\_/  |_____|\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
