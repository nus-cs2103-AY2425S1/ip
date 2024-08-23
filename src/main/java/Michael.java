import java.util.Scanner;

public class Michael {
    private static final String border = "--------------------------------------";
    public static void printer(String text) {
        System.out.println(border);
        System.out.println(text);
        System.out.println(border);
    }

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in); // scanner for user input

        // Greet user first
        printer("Hello! I'm Michael. \n" + "What can I do for you?");

        // Read user's input
        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) { // special bye command
                break;
            }
            printer(input);
        }

        // Exit
        printer("Bye. Hope to see you again soon!");
    }
}
