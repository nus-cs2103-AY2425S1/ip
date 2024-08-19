import java.util.Scanner;

public class MrIncredible {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greeting = "What can I do for you?";
        String byeText = "Bye. Hope to see you again soon!";
        // Initial greeting
        System.out.println("    ____________________________________________________________");
        System.out.println("Hello! I'm MrIncredible");
        System.out.println(greeting);
        System.out.println("    ____________________________________________________________");

        // Start an infinite loop to handle user input
        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit condition
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println(byeText);
                System.out.println("    ____________________________________________________________");
                break;
            }

            // Echo the user's input
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + input.toUpperCase());
            System.out.println("    ____________________________________________________________");
        }

        // Close the scanner
        scanner.close();
    }
}
