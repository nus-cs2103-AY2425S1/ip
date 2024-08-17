
import java.util.Scanner;
public class Xizi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ActionList actions = new ActionList();


        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Xizi");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");


    while(true) {
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("list")) {
            System.out.println("____________________________________________________________");
            actions.printActions();
            System.out.println("____________________________________________________________");
            continue;
        }
        // Exit the loop if the user types "bye"
        if (userInput.equalsIgnoreCase("bye")) {
            break;
        }

        // Echo the input back to the user
        System.out.println("____________________________________________________________");
        System.out.println("added: " + userInput);
        System.out.println("____________________________________________________________");

        actions.addAction(userInput);

    }

    // Print the goodbye message
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    // Close the scanner
        scanner.close();
    }
}

