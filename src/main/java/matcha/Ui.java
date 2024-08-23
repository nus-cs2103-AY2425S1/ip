package matcha;
import java.util.Scanner;
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readInput() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void greet() {
        printDivider();
        System.out.println("Hi there! I am Matcha, your personal chatbot.");
        System.out.println("How can I help you today?");
        printDivider();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void showLoadingError() {
        printDivider();
        System.out.println("There was an error loading the file. Generating a new task list.");
        printDivider();
    }
}
