import java.util.Scanner;

public class Evan {
    public static void main(String[] args) {
        String divider = "_".repeat(50);
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println(divider);
        System.out.println("Hello! I'm Evan!");
        System.out.println("What can I do for you?");

        while (true) {
            input = scanner.nextLine();
            System.out.println(divider);

            // Check for exit condition
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            }

            // Echo the user's input
            System.out.println(input);
            System.out.println(divider);
        }

        // Close the scanner before exiting
        scanner.close();
    }

}
