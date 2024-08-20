import java.util.Scanner;

public class Nimbus {
    private static String name = "Nimbus";
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

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(!(line = scanner.nextLine()).equals("bye")) {
            System.out.println(line);
        }

        printGoodbyeMessage();
    }
}
