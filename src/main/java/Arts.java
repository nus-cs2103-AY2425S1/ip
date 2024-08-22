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
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            if (parts[0].equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (parts[0].equalsIgnoreCase("list")){
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
            } else if (parts[0].equalsIgnoreCase("mark")){
                int taskIndex = Integer.parseInt(parts[1]) -1;
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
                    tasks[taskCount] = new Todo(parts[1]);
                } else if (parts[0].equalsIgnoreCase("deadline")) {
                    String[] deadlineParts = parts[1].split(" /by ");
                    tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                } else if (parts[0].equalsIgnoreCase("event")) {
                    String[] eventParts = parts[1].split(" /from | /to ");
                    tasks[taskCount] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                } else {
                    tasks[taskCount] = new Task(input);
                }
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " " + (taskCount == 1 ? "task" : "tasks") +  " in the list.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}

