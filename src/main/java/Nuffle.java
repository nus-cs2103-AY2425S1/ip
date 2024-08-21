import java.util.Scanner;
import java.util.ArrayList;
public class Nuffle {

    private static ArrayList<String> inputList = new ArrayList<>();

    private static void printLine() {
        // This method will be used to print the border
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
    }

    private static void outputList() {
        // If there is no user input in the list
        if (inputList.isEmpty()) {
            System.out.println("List is empty. No input added.");
        } else {
            // Iterate over the list to print out all the user inputs
            for (int index = 0; index < inputList.size(); index++) {
                System.out.println("" + (index + 1) + ". " + inputList.get(index));
            }
        }
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
            } else if (userInput.equals("list")) {
                // Program will list all the tasks that was input by the user
                printLine();
                outputList();
                printLine();
            }
            else {
                // Echo the user command
                printLine();
                System.out.println("Nuffle > added: " + userInput);
                // Add the user input into the list
                inputList.add(userInput);
                printLine();
            }
        }
        // Once loop breaks, terminate/close the scanner
        user_s.close();
    }
}
