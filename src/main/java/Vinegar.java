import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vinegar {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vinegar\n" +
                "What Can I do for you?");
        System.out.println("____________________________________________________________");

        chatLoop:
        while(true) {
            try {
                String userInput = scanner.nextLine();
                String[] inputParts = userInput.split(" ", 2);
                String instruction = inputParts[0].toLowerCase();

                switch (instruction) {
                    case "bye":
                        System.out.println("____________________________________________________________");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");

                        break chatLoop;
                    case "list":
                        System.out.println("____________________________________________________________");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println("____________________________________________________________");

                        break;
                    case "mark": {
                        if (inputParts.length < 2) throw new VinegarException("Please specify which task to mark.");
                        int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(taskIndex));
                        System.out.println("____________________________________________________________");

                        break;
                    }
                    case "unmark": {
                        if (inputParts.length < 2) throw new VinegarException("Please specify which task to unmark.");
                        int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                        tasks.get(taskIndex).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(taskIndex));
                        System.out.println("____________________________________________________________");

                        break;
                    }
                    case "todo": {
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description.");
                        Task task = new Todo(inputParts[1]);
                        tasks.add(task);
                        printTaskAdded(task);

                        break;
                    }
                    case "deadline": {
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description and deadline.");
                        String[] parts = inputParts[1].split(" /by ");
                        if (parts.length < 2) throw new VinegarException("Please specify the deadline using /by.");
                        Task task = new Deadline(parts[0], parts[1]);
                        tasks.add(task);
                        printTaskAdded(task);

                        break;
                    }
                    case "event": {
                        if (inputParts.length < 2) throw new VinegarException("Please specify the description and event time.");
                        String[] parts = inputParts[1].split("/from | /to ");
                        if (parts.length < 3) throw new VinegarException("Please specify the event time using /from and /to.");
                        Task task = new Event(parts[0], parts[1], parts[2]);
                        tasks.add(task);
                        printTaskAdded(task);

                        break;
                    }
                    default:
                        throw new VinegarException("""
                                Please use these commands
                                 todo\s
                                 deadline\s
                                 event\s
                                 list\s
                                 mark\s
                                 unmark\s
                                 bye""");
                }
            } catch (VinegarException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The task number must be an integer.");
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}