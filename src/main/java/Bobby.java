import java.util.Scanner;

public class Bobby {

    /**
     * This function greets the user.
     */
    private static void greet() {
        String greeting = "Hello I'm Bobby\n"
                + "What can I do for you today?";
        System.out.println(greeting);
    }

    /**
     * This function exits the chat app with a default message.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This function takes in the user input and prints out the input.
     * @param String input
     */
    private static void echo(String input) {
        System.out.println(input);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            // Ask the user for input
            System.out.print("Enter something (or type 'bye' to quit): ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exit();
                break;
            }
            echo(userInput);
        }


    }
}
