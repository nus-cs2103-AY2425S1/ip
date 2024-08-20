import java.util.Scanner;

public class Dumpling {

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final String TERMINAL_STRING = "bye";

    public static void greet() {
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?\n" +
                DIVIDER + "\n";
        System.out.println(greetingMessage);
    }

    public static void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    public static void echo(String message) {
        String updatedMessage = "    " + message + "\n" +
                DIVIDER + "\n";
        System.out.println(updatedMessage);
    }

    public static void main(String[] args) {
        System.out.println(DIVIDER);
        greet();
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();
        System.out.println(DIVIDER);
        while (!userCommand.equals(TERMINAL_STRING)) {
            echo(userCommand);
            userCommand = scanner.nextLine();
            System.out.println(DIVIDER);
        }
        exit();
    }
}
