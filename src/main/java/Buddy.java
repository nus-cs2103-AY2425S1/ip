import java.util.Scanner;

public class Buddy {
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

            // Print user input
            System.out.println(input);
        }

        scanner.close();
    }
}
