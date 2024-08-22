import java.util.Scanner;

public class James {
    public static void main(String[] args) {
        String greeting = "Hello Big Boy! I'm James \n"
                + "How can I assist you today? \n";
        String exitMessage = "Goodbye. Come back anytime! \n";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.println(command);
        }

        System.out.println(exitMessage);

        scanner.close();
    }
}

