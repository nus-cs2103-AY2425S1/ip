import java.util.Scanner;

public class Ui {
    private final String divider = "____________________________________________________________\n";
    private final String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
    private final String exit = "Bye. Hope to see you again soon!\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printGreeting() {
        System.out.print(divider + greeting + divider);
    }

    public void printExit() {
        System.out.println(exit);
    }

    public void printDivider() {
        System.out.print(divider);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
