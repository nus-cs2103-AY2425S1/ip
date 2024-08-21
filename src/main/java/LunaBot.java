import java.util.Scanner;

public class LunaBot {
    public static void main(String[] args) {
        // Greet the user
        System.out.println("___________________________________________________________________");
        System.out.println(" Hello! I'm LunaBot");
        System.out.println(" What can I do for you?");

        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // loop scanner to echo user input
        while (true) {
            input = scanner.nextLine();

            // exit if input is "bye"
            if (input.equals("bye")) {
                System.out.println("___________________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("___________________________________________________________________");
                break;
            }
            System.out.println("___________________________________________________________________");
            System.out.println(" " + input);
            System.out.println("___________________________________________________________________");
        }
        scanner.close();
    }
}
