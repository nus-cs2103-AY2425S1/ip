import java.util.Scanner;

public class Max {
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public void runMax() {
        printHello();

        boolean running = true;

        while (running) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                running = false;
            } else {
                echo(text);
            }
        }
        printBye();
    }

    public void printMessage(String message) {
        System.out.println("\t" + message);
        printLine();
    }

    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\tWhat can I do for you?");
    }

    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void echo(String text) {
        printLine();
        printMessage(text);
    }
}
