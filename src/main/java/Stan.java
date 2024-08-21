import java.util.Scanner;
public class Stan {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = "Stan";
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + logo);
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            String[] words = input.split(" ",2);;
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("_____________________________________________________________");
            } else if (words[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            } else if (words[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println("____________________________________________________________");
            } else if (words[0].equalsIgnoreCase("todo")) {
                tasks[taskCount] = new Todo(words[1]);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (words[0].equalsIgnoreCase("deadline")) {
                String[] parts = words[1].split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (words[0].equalsIgnoreCase("event")) {
                String[] parts = words[1].split(" /from ");
                String[] times = parts[1].split(" /to ");
                tasks[taskCount] = new Event(parts[0], times[0], times[1]);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println(" I'm sorry, I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();

    }
}
