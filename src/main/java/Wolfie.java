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
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye Dean's Lister. Hope to see you again soon!");
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
                }
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsUndone(); // mark the task as not done
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskIndex]); // print the task
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description); // create a new Todo task
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]); // print the task
                System.out.println(" Now you have " + taskCount + " tasks in the list."); // print the number of tasks
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by "); // split by " /by "
                String description = parts[0]; // description is before " /by "
                String by = parts[1]; // by is after " /by "
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to "); // split by " /from " or " /to "
                String description = parts[0]; // description is before " /from "
                String from = parts[1]; // from is after " /from " and before " /to "
                String to = parts[2]; // to is after " /to "
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}