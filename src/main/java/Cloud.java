import java.util.Scanner;

public class Cloud {

    private static final String EXIT_COMMAND = "bye";

    private static void greet() {
        System.out.println(
            "Hello! I'm Cloud\n" +
            "What can I do for you?"
        );
    }

    private static void printHorizLine() {
        System.out.println(
            "____________________________________________________________"
        );
    }

    private static void echo(String message) {
        System.out.println(message);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printHorizLine();
        greet();
        while (true) {
            printHorizLine();
            String userInput = sc.nextLine().strip();
            if (userInput.equals(EXIT_COMMAND)) {
                exit();
                break;
            }
            System.out.println(userInput);
        }
        printHorizLine();
    }
}
