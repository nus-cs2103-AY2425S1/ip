import java.util.Scanner;

public class ShoAI {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm ShoAI");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ", 2);
            String command = words[0];

            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
                System.out.println("____________________________________________________________");
            } else if (command.equals("todo")) {
                String description = words[1];
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.equals("deadline")) {
                String[] parts = words[1].split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.equals("event")) {
                String[] parts = words[1].split(" /from ");
                String[] timeParts = parts[1].split(" /to ");
                tasks[taskCount] = new Event(parts[0], timeParts[0], timeParts[1]);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry, I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
