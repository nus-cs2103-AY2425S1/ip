import java.util.Scanner;


/**
 * The {@code Gutti} class represents a simple chatbot that can add tasks, list them,
 * <p>
 * The chatbot greets the user, processes commands to add and list tasks, and provides
 * a farewell message before exiting when user decides to exit via "bye" command.
 * </p>
 */

public class Gutti {

    private static String[] tasks = new String[100];
    private static int noOfTasks = 0;

    /**
     * <p>
     * This method prints a welcome message and prompts the user to enter commands.
     * </p>
     */
    private static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * <p>
     * This method prints a goodbye message and indicates that the chatbot is closing.
     * </p>
     */
    private static void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * Handles user input after user initialize chatbot
     * <p>
     * This method continuously reads user input from the console.
     * When input is provided, it will be added to the list.
     * Lists all tasks when the user types "list", and exits the program
     * when the user types "bye".
     * </p>
     */
    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        // User's input
        String input;
        // Loops until user says bye
        while (true) {
            input = scanner.nextLine();
            // Stop when user types bye
            if(input.equalsIgnoreCase("bye")) {
                break;
            }
            // Lists out all the tasks
            if(input.equalsIgnoreCase("list")) {
                listsTask();
            }
            // Adds tasks
            else {
                System.out.println("____________________________________________________________");
                System.out.println("Gutti painstakingly added: " + input);
                System.out.println("____________________________________________________________");
                tasks[noOfTasks] = input;
                Gutti.noOfTasks++;
            }
        }
        scanner.close();
    }

    /**
     * Lists all the tasks currently stored.
     * <p>
     * This method prints all tasks stored in the array
     * It is only called when the user types "list".
     * </p>
     */
    private static void listsTask() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * The entry point of the program.
     * @param args (not used)
     */

    public static void main(String[] args) {

        // Display the greeting message
        greetings();
        // Echos
        echo();
        // Exit message
        goodBye();
    }
}
