import java.util.Scanner;
public class GPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chatbotName = "GPT";
        String[] tasks = new String[100];  // Array to store up to 100 tasks
        int taskCount = 0;  // Counter for the number of tasks

        // Display the initial greeting
        System.out.println("Type 'list' to display saved tasks or 'bye' to exit.");
        printLine();
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        printLine();


        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            // List
            if (input.equalsIgnoreCase("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println("No tasks to show.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                printLine();
                continue;
            }

            // Add task
            if (taskCount < 100) {
                tasks[taskCount++] = input;
                printLine();
                System.out.println("Added: " + input);
                printLine();
            } else {
                printLine();
                System.out.println("Task limit reached. Cannot store more tasks.");
                printLine();
            }
        }
        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
