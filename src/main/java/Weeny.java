import java.util.Scanner;


public class Weeny {
    public static void main(String[] args) {
        boolean farewell = false;
        Scanner user_input = new Scanner(System.in);

        // Print the initial greeting
        line();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        line();

        // Main loop for handling user input
        while (!farewell) {
            String input = user_input.nextLine();

            // Print the user's input between lines
            line();
            System.out.println(input);
            line();

            // Handle the 'bye' command
            if (input.equals("bye")) {
                farewell = true;
                line();
                System.out.println("Bye. Hope to see you again soon!");
                line();
            }
        }

        user_input.close();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}