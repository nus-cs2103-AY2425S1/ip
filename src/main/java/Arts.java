import java.awt.*;
import java.util.Scanner;

public class Arts {
    public static void main(String[] args) {
        String logo = "     _    _____  _______  _____  \n"
                + "    / \\  |  __ \\|__   __|/ ____| \n"
                + "   / _ \\ | |__) |  | |  | (___   \n"
                + "  / ___ \\|  _  /   | |   \\___ \\  \n"
                + " /_/   \\_\\_| \\_\\   |_|   |_____/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Arts, your go-to Chatbot.");
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(" ", 2);

                if (parts[0].equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye! Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (parts[0].equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    if (taskCount == 0) {
                        System.out.println("No tasks yet! Why not add some?");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (parts[0].equalsIgnoreCase("mark")) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done.");
                    System.out.println(" " + tasks[taskIndex]);
                    System.out.println("____________________________________________________________");
                } else if (parts[0].equalsIgnoreCase("unmark")) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done.");
                    System.out.println(" " + tasks[taskIndex]);
                    System.out.println("____________________________________________________________");
                } else {
                    if (parts[0].equalsIgnoreCase("todo")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of a todo cannot be empty.");
                        }
                        tasks[taskCount] = new Todo(parts[1]);
                    } else if (parts[0].equalsIgnoreCase("deadline")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of a deadline cannot be empty.");
                        }
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length < 2) {
                            throw new ArtsException("The deadline must have a /by date.");
                        }
                        tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    } else if (parts[0].equalsIgnoreCase("event")) {
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new ArtsException("The description of an event cannot be empty.");
                        }
                        String[] eventParts = parts[1].split(" /from | /to ");
                        if (eventParts.length < 3) {
                            throw new ArtsException("The event must have /from and /to times.");
                        }
                        tasks[taskCount] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    } else {
                        throw new ArtsException("I'm sorry, but I don't know what that means.");
                    }
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") + " in the list.");
                    System.out.println("____________________________________________________________");
                }
            } catch (ArtsException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
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

