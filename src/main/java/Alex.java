import java.util.Scanner;

public class Alex {

    // Array to store tasks
    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alex, your friendly assistant!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            // Command to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }

            // Command to tell a joke
            else if (userInput.equalsIgnoreCase("tell me a joke")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Why did the scarecrow win an award? Because he was outstanding in his field!");
                System.out.println("____________________________________________________________");
            }

            // Command to exit
            else if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // For all other inputs, display "added: {input}"
            else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = userInput; // Store the user input as a task
                    taskCount++;
                }
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
