import java.util.Scanner;

public class Alden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Array to store up to 100 tasks
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Print the greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alden");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isRunning) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
                // Print the exit message
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            } else if (userInput.equalsIgnoreCase("list")) {
                // Display the list of tasks
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("todo")) {
                // Add a todo task
                String description = userInput.substring(5).trim();
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("deadline")) {
                // Add a deadline task
                String[] parts = userInput.split("/by", 2);
                String description = parts[0].substring(9).trim();
                String by = parts[1].trim();
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("event")) {
                // Add an Event task
                String[] parts = userInput.split("/from|/to", 3);
                String description = parts[0].substring(6).trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // Extract task number and mark it as done
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this work as done: ");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please specify a valid task number.");
                }
            } else if (userInput.startsWith("unmark")) {
                // Extract task number and unmark it as not done
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].unmarkAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet: ");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please specify a valid task number.");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" I didn't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}