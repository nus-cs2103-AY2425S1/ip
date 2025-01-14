import java.util.ArrayList;
import java.util.Scanner;

public class Alden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Using ArrayList to store tasks
        ArrayList<Task> tasks = new ArrayList<>();

        // Start message
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
                    if (tasks.isEmpty()) {
                        System.out.println(" Your task list is empty.");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new AldenException("The description of a todo cannot be empty.");
                    }
                    String description = userInput.substring(5).trim();
                    Task newTask = new Todo(description);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split("/by", 2);
                    if (parts.length < 2) {
                        throw new AldenException("The deadline task must have a description and a /by clause.");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    Task newTask = new Deadline(description, by);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.split("/from|/to", 3);
                    if (parts.length < 3) {
                        throw new AldenException("The event task must have a description, /from clause, and /to clause.");
                    }
                    String description = parts[0].substring(6).trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    Task newTask = new Event(description, from, to);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(taskNumber));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new AldenException("Invalid task number. Please enter a valid task number.");
                    }
                } else if (userInput.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).unmarkAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(taskNumber));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new AldenException("Invalid task number. Please enter a valid task number.");
                    }
                } else if (userInput.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to 0-based index
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + removedTask);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new AldenException("Invalid task number. Please enter a valid task number.");
                    }
                } else {
                    throw new AldenException("Invalid input");
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
