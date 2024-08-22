import java.util.Scanner;

public class Bigdog {
    public static void main(String[] args) {

        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Hello! I'm Bigdog!\n" + "What can I do for you?\n");

        while (true) {

            // Take in user input
            String userInput = scanner.nextLine();

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            }

            // Echo user input
            System.out.println(userInput);
        }

        scanner.close();
    }
}
