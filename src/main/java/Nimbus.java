import java.util.Scanner;

public class Nimbus {
    private static String name = "Nimbus";
    private static String[] texts = new String[100];
    private static int textCount = 0;

    private static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    public static void printGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void logMessage(String Message) {
        printDash();
        texts[textCount++] = Message;
        System.out.println("added: " + Message);
        printDash();
    }

    public static void printAllMessage() {
        printDash();
        for (int i = 0; i < textCount; ++i) {
            System.out.println((i + 1) + ". " + texts[i]);
        }
        printDash();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(!(line = scanner.nextLine()).equals("bye")) {
            if (line.equals("list")) {
                printAllMessage();
            } else {
                logMessage(line);
            }
        }

        printGoodbyeMessage();
    }
}
