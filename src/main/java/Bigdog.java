import java.util.Scanner;


public class Bigdog {

    // Array to store tasks
    static String[] toDoList = new String[100];
    // Counter to keep track of the number of tasks
    static int taskCounter = 0;

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
            } else if (userInput.equalsIgnoreCase("list")) {

                // Print the list
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i +1) + ". " + toDoList[i]);
                }
            } else {

                // Add and Echo user input
                toDoList[taskCounter] = userInput;
                taskCounter++;
                System.out.println("added: " + userInput);
            }

        }

        scanner.close();
    }
}
