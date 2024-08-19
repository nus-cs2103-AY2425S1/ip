import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chatbotName = "GPT";
        List<Task> tasks = new ArrayList<>();  // List to store Task objects

        // Display the initial greeting
        System.out.println("Type 'list' to display saved tasks, 'mark [i]' to mark a task as done, 'unmark [i]' to mark a task as not done, or 'bye' to exit.");
        printLine();
        System.out.println("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Exit
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            // List tasks
            if (input.equalsIgnoreCase("list")) {
                printLine();
                if (tasks.isEmpty()) {
                    System.out.println("No tasks to show.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
                printLine();
                continue;
            }

            // Add task
            if (!input.startsWith("mark ") && !input.startsWith("unmark ")) {
                tasks.add(new Task(input));
                printLine();
                System.out.println("Added: " + input);
                printLine();
                continue;
            }

            // Mark task as done
            if (input.startsWith("mark ")) {
                handleMarkUnmark(tasks, input, true);
                continue;
            }

            // Unmark task as not done
            if (input.startsWith("unmark ")) {
                handleMarkUnmark(tasks, input, false);
                continue;
            }

            // If command is not recognized
            printLine();
            System.out.println("Command not recognized. Please use 'list', 'mark [i]', 'unmark [i]', or 'bye'.");
            printLine();
        }
        scanner.close();
    }

    // Method to print a line of underscores
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    // Method to handle marking and unmarking tasks
    private static void handleMarkUnmark(List<Task> tasks, String input, boolean markAsDone) {
        try {
            // Extract index from the command
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (markAsDone) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    task.markAsNotDone();
                    System.out.println("Nice! I've marked this task as not done:");
                }
                // Display the updated task
                System.out.println(task);
            } else {
                System.out.println("Task index out of range.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task index format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No task index provided.");
        }
    }
}
