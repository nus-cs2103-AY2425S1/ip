import java.util.Scanner;
public class Nuffle {

    private static void printLine() {
        // This method will be used to print the border
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
    }
    public static void main(String[] args) {
        // This will be starting point of the application

        //Variable to store user inputs
        String userInput;

        // Create a scanner for user input
        Scanner user_s = new Scanner(System.in);

        // Greeting the users
        printLine();
        System.out.println("Nuffle > Good day! I'm Nuffle.");
        System.out.println("Nuffle > What can I do for you today?");
        printLine();

        // Read the user input until the command "bye" is provided by the user
        while(true) {
            // Get the user input from the scanner
            userInput = user_s.nextLine();

            // Check if the user input provided is "bye", has to be lowercase
            if (userInput.equals("bye")) {
                // Program will exit
                printLine();
                System.out.println("Nuffle > Bye. Hope to see you again soon!");
                printLine();
                break;
            } else {
                // Echo the user command
                printLine();
                System.out.println("Nuffle > " + userInput);
                printLine();
            }
        }
        // Once loop breaks, terminate/close the scanner
        user_s.close();
    }
}
