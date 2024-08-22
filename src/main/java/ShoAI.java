import java.util.Scanner;

public class ShoAI {
    // Array to store tasks
    private static String[] tasks = new String[100];
    // Counter to keep track of the number of tasks
    private static int taskCount = 0;

    public static void main(String[] args) {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm ShoAI");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Main loop: Process commands until user types "bye"
        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user typed "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;  // Exit the loop and end the program
            }

            // Check if the user typed "list"
            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                // Store the task
                addTask(userInput);
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Method to add a task
    private static void addTask(String task) {
        if (taskCount < 100) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Sorry, I can't store any more tasks.");
            System.out.println("____________________________________________________________");
        }
    }

    // Method to display all tasks
    private static void displayTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
}