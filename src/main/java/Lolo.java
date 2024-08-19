import java.util.Scanner;

public class Lolo {

    private static final int MAX_TASKS = 100;  // Maximum number of tasks
    private static String[] tasks = new String[MAX_TASKS];  // Array to store tasks
    private static int taskCount = 0;  // Counter for the number of tasks added

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Greet the user and introduce the chatbot
        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?\n");

        String userCommand;

        // Start a loop that continues until the user types "bye"
        do {
            // Prompt the user for input/command
            System.out.print("You: ");
            userCommand = scanner.nextLine();

            if (userCommand.equalsIgnoreCase("bye")) {
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userCommand);
            }

        } while (!userCommand.equalsIgnoreCase("bye")); // Loop ends when user types "bye"

        // Say goodbye to the user before exiting
        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");

        // Close the scanner
        scanner.close();
    }

    // Function to add a task to the list
    public static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("Added: " + task);
        } else {
            System.out.println("Sorry, the task list is full! You cannot add more tasks :( " +
                    "Consider completing the tasks in the list before adding new tasks!");
        }
    }

    // Function to list all the tasks
    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("You have not added any tasks yet. Get started now! :D");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}
