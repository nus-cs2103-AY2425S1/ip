import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {
    private static final ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! I'm Buddy");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit if user types "bye"
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                // List all tasks if user types "list"
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                // Store input text
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}
