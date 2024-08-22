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
                    int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");

                    break;
                }
                case "unmark": {
                    int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskIndex));
                    System.out.println("____________________________________________________________");

                    break;
                }
                case "todo": {
                    Task task = new Todo(inputParts[1]);
                    tasks.add(task);
                    printTaskAdded(task);

                    break;
                }
                case "deadline": {
                    String[] parts = inputParts[1].split(" /by ");
                    Task task = new Deadline(parts[0], parts[1]);
                    tasks.add(task);
                    printTaskAdded(task);

                    break;
                }
                case "event": {
                    String[] parts = inputParts[1].split("/from | /to ");
                    Task task = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(task);
                    printTaskAdded(task);

                    break;
                }
                default:
                    tasks.add(new Task(userInput));
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                    break;
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