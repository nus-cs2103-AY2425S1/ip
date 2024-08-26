
import java.util.Scanner;

public class Ui {
    private String name;
    private Scanner scanner;

    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.println("Hello! I'm " + this.name);
        System.out.println("What can I do for you?");
    }

    void showError(String error) {
        System.err.println(error);
    }

    void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void showResult(String result) {
        System.out.println(result);
    }

    String readInput() {
        String nextLine = this.scanner.nextLine();
        return nextLine;
    }

    boolean isOpen() {
        return this.scanner.hasNext();
    }
}
