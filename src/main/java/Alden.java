import java.util.Scanner;

public class Alden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Array to store up to 100 tasks
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alden");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isRunning) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    if (taskCount == 0) {
                        System.out.println(" Your task list is empty.");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + "." + tasks[i]);
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new AldenException("The description of a todo cannot be empty.");
                    }
                    String description = userInput.substring(5).trim();
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split("/by", 2);
                    if (parts.length < 2) {
                        throw new AldenException("The deadline task must have a description and a /by clause.");
                    }
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
                    String[] parts = userInput.split("/from|/to", 3);
                    if (parts.length < 3) {
                        throw new AldenException("The event task must have a description, /from clause, and /to clause.");
                    }
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
                    // Mark task as done
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new AldenException("Invalid task number. Please enter a valid task number.");
                    }
                } else if (userInput.startsWith("unmark")) {
                    // Unmark task as not done
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].unmarkAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new AldenException("Invalid task number. Please enter a valid task number.");
                    }
                } else {
                    throw new AldenException("Invalid input.");
                }
            } catch (AldenException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Error: " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
