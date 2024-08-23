import java.util.Scanner;
public class Yap {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Print logo and introductions
//        String logo = "_    _  __     ______\n"
//                    + " \\  // //\\    ||__| |\n"
//                    + "  \\// //__\\   ||____/\n"
//                    + "  || //____\\  ||\n"
//                    + "  ||//      \\ ||\n";
        String logo = "Yap";
        String separator = "_____________________________________";
        System.out.println("Hello from " + logo);
        System.out.println("What would you like me to do for you?");
        System.out.println(separator);

        // Infinite loop to get user input
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (true) {
            String userInput = scanner.nextLine();

            // Bye functionality
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! It was really nice talking to you, see you soon :)");
                break;
            }

            // Mark functionality
            if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("I've marked this task as done:");
                System.out.println(tasks[taskIndex]);
                System.out.println(separator);
                continue;
            }

            // Unmark functionality
            if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsUndone();
                System.out.println("I've marked this task as not done:");
                System.out.println(tasks[taskIndex]);
                System.out.println(separator);
                continue;
            }

            // If user adds a todo task
            if (userInput.startsWith("todo")) {
                tasks[taskCount] = InputParser.parseInputAsToDo(userInput);
                System.out.println("Added: " + tasks[taskCount++]);
                System.out.printf("You now have %d tasks in the list%n", taskCount);
                System.out.println(separator);
                continue;
            }

            // If user adds a deadline task
            if (userInput.startsWith("deadline")) {
                tasks[taskCount] = InputParser.parseInputAsDeadline(userInput);
                System.out.println("Added: " + tasks[taskCount++]);
                System.out.printf("You now have %d tasks in the list%n", taskCount);
                System.out.println(separator);
                continue;
            }

            // If user adds an event task
            if (userInput.startsWith("event")) {
                tasks[taskCount] = InputParser.parseInputAsEvent(userInput);
                System.out.println("Added: " + tasks[taskCount++]);
                System.out.printf("You now have %d tasks in the list%n", taskCount);
                System.out.println(separator);
                continue;
            }

            // List functionality
            if (userInput.startsWith("list")) {
                for (int input = 0; input < taskCount; ++input) {
                    System.out.println((input + 1) + ". " + tasks[input].toString());
                }
            }

            System.out.println(separator);
        }
    }
}
