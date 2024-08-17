import java.util.Scanner;
public class Xizi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Xizi");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");


    while(true) {
        String userInput = scanner.nextLine();

        // Echo the input back to the user
        System.out.println("____________________________________________________________");
        System.out.println(" " + userInput);
        System.out.println("____________________________________________________________");

        // Exit the loop if the user types "bye"
        if (userInput.equalsIgnoreCase("bye")) {
            break;
        }
    }

    // Print the goodbye message
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    // Close the scanner
        scanner.close();
    }
}

