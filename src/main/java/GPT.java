import java.util.ArrayList;
import java.util.Scanner;

public class GPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); // Use ArrayList to store tasks

        // Display the initial greeting
        System.out.println("Type 'list' to display saved tasks, 'mark <index>' to mark a task, 'unmark <index>' to unmark a task, or 'bye' to exit.");
        printLine();
        System.out.println("Hello! I'm GPT");
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

            // Mark task
            if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input format.");
                }
                continue;
            }

            // Unmark task
            if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println("Nice! I've marked this task as not done:");
                        System.out.println("  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input format.");
                }
                continue;
            }

            // Add tasks based on type
            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                System.out.println("Invalid input format.");
                continue;
            }

            String command = parts[0].toLowerCase();
            String description = parts[1].trim();

            Task task = null;
            if (command.equals("todo")) {
                task = new Todo(description);
            } else if (command.equals("deadline")) {
                // Extract the deadline time
                String[] deadlineParts = description.split("/by", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("Invalid deadline format.");
                    continue;
                }
                task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            } else if (command.equals("event")) {
                // Extract event times
                String[] eventParts = description.split("/from", 2);
                if (eventParts.length < 2) {
                    System.out.println("Invalid event format.");
                    continue;
                }
                String from = eventParts[1].split("/to", 2)[0].trim();
                String to = eventParts[1].split("/to", 2)[1].trim();
                task = new Event(eventParts[0].trim(), from, to);
            } else {
                System.out.println("Unknown task type.");
                continue;
            }

            tasks.add(task);
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        }
        scanner.close();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
