import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);

        String command;
        while (true) {
            command = sc.nextLine();  // Get next command
            if (command.equals("bye")) {
                break;
            }
            printCommand(command);
        }
        exit();
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to steal your credit card information.\n"
                + "What can I do for you?", NAME);
        System.out.println(greeting);
    }

    private static void printCommand(String command) {
        String line = "___________________________________";
        System.out.println(line);
        System.out.println(command);
        System.out.println(line);
    }

    private static void exit() {
        System.out.println("That's all folks! Hope to see you again soon!");
    }
}
