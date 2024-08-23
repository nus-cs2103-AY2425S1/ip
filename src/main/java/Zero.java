import java.util.Scanner;

public class Zero {
    public static void main(String[] args) {
        // store tasks in this array
        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Zero");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                // go through the task list and display tasks
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark")) {
                String[] strArr = input.split(" ", 2);
                int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
                tasks[choice].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[choice]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark")) {
                String[] strArr = input.split(" ", 2);
                int choice = Integer.parseInt(strArr[1]) - 1;  // convert to zero-based index
                tasks[choice].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[choice]);
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);  // extract description
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split("/by ", 2);
                String description = parts[0].substring(9);  // extract description
                String by = parts[1];  // extract deadline
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event")) {
                String[] parts = input.split("/from | /to ");
                String description = parts[0].substring(6);  // extract description
                String from = parts[1];  // extract start time
                String to = parts[2];  // extract end time
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" 分かりません");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
