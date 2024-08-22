import java.util.Scanner;
public class Yap {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Print logo and introductions
        String logo = "_    _  __     ______\n"
                    + " \\  // //\\    ||__| |\n"
                    + "  \\// //__\\   ||____/\n"
                    + "  || //____\\  ||\n"
                    + "  ||//      \\ ||\n";
        String separator = "_____________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println("What would you like me to do for you? ");
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

            System.out.println(separator);

            // Mark functionality
            if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("I've marked this task as done: ");
                System.out.println(tasks[taskIndex]);
                System.out.println(separator);
                continue;
            }

            // Unmark functionality
            if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsUndone();
                System.out.println("I've marked this task as not done: ");
                System.out.println(tasks[taskIndex]);
                System.out.println(separator);
                continue;
            }

            // List functionality
            if (userInput.equalsIgnoreCase("list")) {
                for (int input = 0; input < taskCount; ++input) {
                    System.out.println((input + 1) + ". " + tasks[input].toString());
                }
            } else {
                tasks[taskCount] = new Task(userInput);
                System.out.println("Added: " + tasks[taskCount++]);
            }

            System.out.println(separator);
        }
    }
}
