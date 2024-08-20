import java.util.Scanner;

public class Max {
    private final Scanner scanner = new Scanner(System.in);
    private String[] storedTexts;
    private int storedTextsIndex;

    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public Max() {
        storedTexts = new String[100];
        storedTextsIndex = 0;
    }
    public void runMax() {
        printHello();

        boolean running = true;

        while (running) {
            String text = scanner.nextLine();

            if (text.equals("bye")) {
                running = false;
            } else if (text.equals("list")){
                list();
            } else {
                this.storedTexts[this.storedTextsIndex] = text;
                this.storedTextsIndex++;
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
        printMessage("added: " + text);
    }

    public void list() {
        printLine();
        for (int i = 0; i < this.storedTextsIndex; i++) {
            int count = i + 1;
            System.out.println("\t" + count + ". " + this.storedTexts[i]);
        }
        printLine();
    }
}
