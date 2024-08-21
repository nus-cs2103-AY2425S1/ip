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
        // Add some error checking in case index > len of list
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (Task item : items) {
                    System.out.println(index + ".[" + item.getStatusIcon() + "] " + item.description);
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
