import java.util.Scanner;
import java.util.ArrayList;


public class Asta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Asta\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks.add(new ToDo(description));
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][ ] " + description);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                tasks.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task:");
                System.out.println("[D][ ] " + description + " (by: " + by + ")");
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                String description = parts[0];
                String from = parts[1];
                String to = parts[2];
                tasks.add(new Event(description, from, to));
                System.out.println("Got it. I've added this task:");
                System.out.println("[E][ ] " + description + " (from: " + from + " to: " + to + ")");
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }
}
