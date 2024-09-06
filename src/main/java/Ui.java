import java.util.Scanner;

public class Ui {
    private static final String GREET = "Hello! I'm PacMan. How can I help you?";
    private static final String BYE = "Good bye. Hope to see you soon!";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showGreet() {
        System.out.println("  " + GREET);
    }

    public void showBye() {
        System.out.println("  " + BYE);
    }

    public void showResult(String result) {
        System.out.println("  " + result);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
