import java.util.Scanner;

public class Peter {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter\nWhat can I do for you?\n");

        // Init Globals
        String[] tasks = new String[100];
        int lastIndex = 0;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().strip();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < lastIndex; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[lastIndex++] = command;
                System.out.println("added: " + command);
            }

            command = scanner.nextLine();
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
