import java.util.Scanner;
public class Alden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Print the greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alden");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isRunning) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
                // Print the exit message
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
