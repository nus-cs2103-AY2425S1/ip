import java.util.Scanner;

public class SecondMind {
    private static final String line = "____________________________________________________________";
    private static final String logo = "SecondMind";

    private static void printLineSeparator() {
        System.out.println(line);
    }

    private static void greetUser() {
        printLineSeparator();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void getInput() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String command = reader.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                printLineSeparator();
                System.out.println(command);
                printLineSeparator();
            }
        }
        reader.close();
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void main(String[] args) {
        greetUser();
        getInput();
        exitProgram();
    }
}
