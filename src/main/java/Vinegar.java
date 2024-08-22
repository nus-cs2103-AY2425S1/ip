import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vinegar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Vinegar\n" +
                "What Can I do for you?");
        System.out.println("____________________________________________________________");

        label:
        while(true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ", 2);
            String instruction = inputParts[0].toLowerCase();

            switch (instruction) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ".lol" + tasks.get(i));
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
}