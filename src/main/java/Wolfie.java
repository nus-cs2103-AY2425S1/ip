import java.util.Scanner;

public class Wolfie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100]; // store up to 100 tasks
        int taskCount = 0; // track the number of tasks

        System.out.println("____________________________________________________________");
        System.out.println(" Hello Dean's Lister! I'm Wolfie");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye Dean's Lister! Hope to see you again soon :-)");
                    System.out.println("____________________________________________________________");
                    break; // exit the loop
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]); // print all tasks
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1; // get the task index
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone(); // mark the task as done
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]); // print the task
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
                    }
                } else if (input.startsWith("unmark ")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsUndone(); // mark the task as not done
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK! I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]); // print the task
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new WolfieException("The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new Todo(description); // create a new task
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]); // print the task
                    System.out.println(" Now you have " + taskCount + " tasks in the list."); // print the number of tasks
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by "); // split by " /by "
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new WolfieException("The description and deadline of a deadline task cannot be empty.");
                    }
                    String description = parts[0].trim(); // description is before " /by "
                    String by = parts[1].trim(); // by is after " /by "
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(6).split(" /from | /to "); // split by " /from " or " /to "
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new WolfieException("The description, start time, and end time of an event cannot be empty.");
                    }
                    String description = parts[0].trim(); // description is before " /from "
                    String from = parts[1].trim(); // from is after " /from " and before " /to "
                    String to = parts[2].trim(); // to is after " /to "
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it! I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new WolfieException("I'm sorry Dean's Lister, but I don't know what that means :-(");
                }
            } catch (WolfieException e) { // catch and handle WolfieExceptions
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage()); // print the error message
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! Something went wrong Dean's Lister :-("); // print a generic error message
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close(); // close scanner and exit the program :)
    }
}