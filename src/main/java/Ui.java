
import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("There was an error in loading the chatbot");
    }

    public void showWelcomeMessage() {
        System.out.println("------------------------");
        System.out.println("Wassup! I'm LeBron");
        System.out.println("What can I do for you?");
        System.out.println("------------------------");
    }

    public String getUserCommand() {
        return scanner.nextLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! I'm leaving now.");
        System.out.println("------------------------");
    }
}
