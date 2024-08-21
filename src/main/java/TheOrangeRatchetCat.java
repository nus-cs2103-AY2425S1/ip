import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TheOrangeRatchetCat {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> items = new ArrayList<>();
        int index = 1;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task item : items) {
                    System.out.println(index + "." + item);
                    index++;
                }
                index = 1;
                input = scanner.nextLine(); // Reads the next line of input text again
                continue;
            }
            if (input.startsWith("mark")) {
                String numberPart = input.substring(4).trim();
                try {
                    int taskIndex = Integer.parseInt(numberPart) - 1;
                    Task markingTask = items.get(taskIndex);
                    markingTask.isDone = true;
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
                    System.out.println("____________________________________________________________");
                    input = scanner.nextLine(); // Reads the next line of input text again
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Might want to reconsider your action. Please Try Again");
                    input = scanner.nextLine(); // Reads the next line of input text again
                }
                continue;
            }
            if (input.startsWith("unmark")) {
                String numberPart = input.substring(6).trim();
                try {
                    int taskIndex = Integer.parseInt(numberPart) - 1;
                    Task markingTask = items.get(taskIndex);
                    markingTask.isDone = false;
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
                    System.out.println("____________________________________________________________");
                    input = scanner.nextLine(); // Reads the next line of input text again
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Might want to reconsider your action. Please Try Again");
                    input = scanner.nextLine(); // Reads the next line of input text again
                }
                continue;
            }
            if (input.startsWith("todo")) {
                String taskDescription = input.substring(4).trim();
                Task nextTask = new ToDo(taskDescription);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(nextTask);
                System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
                items.add(nextTask);
                input = scanner.nextLine(); // Reads the next line of input text again
                continue;
            }
            if (input.startsWith("deadline")) {
                // Split the input string by "/by"
                String[] parts = input.split("/by");
                // The description is the first part after removing the word "deadline"
                String taskDescription = parts[0].replace("deadline", "").trim();
                // The "by" part is the second part, if it exists
                String date = parts.length > 1 ? parts[1].trim() : "";
                Task nextTask = new Deadline(taskDescription, date);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(nextTask);
                System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
                items.add(nextTask);
                input = scanner.nextLine(); // Reads the next line of input text again
                continue;
            }
            // Make sure to handle incorrect string inputs for event (not just event, but for other tasks as well)
            if (input.startsWith("event")) {
                // Split the input string by "/from"
                String[] parts = input.split("/from");
                // The taskDescription is the first part after removing the word "event"
                String taskDescription = parts[0].replace("event", "").trim();
                // Further split the remaining part by "/to"
                String[] dateParts = parts[1].split("/to");
                // The "fromDate" is the first part
                String fromDate = dateParts[0].trim();
                // The "toDate" is the second part
                String toDate = dateParts.length > 1 ? dateParts[1].trim() : "";
                Task nextTask = new Event(taskDescription, fromDate, toDate);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(nextTask);
                System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
                items.add(nextTask);
                input = scanner.nextLine(); // Reads the next line of input text again
                continue;
            }
            System.out.println("____________________________________________________________");
            System.out.println("added " + input);
            System.out.println("____________________________________________________________");
            Task task = new Task(input);
            items.add(task);
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}
