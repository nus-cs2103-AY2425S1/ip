import java.util.Scanner;

public class Orion {
    static Scanner scanner = new Scanner(System.in);
    static String horizontalLine = "────────────────────────────────────────";
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println("\n");

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);

            if (parts[0].equals("bye")) {
                break;
            } else if (parts[0].equals("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else if (parts[0].equals("mark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(horizontalLine);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println(horizontalLine);
            } else if (parts[0].equals("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println(horizontalLine);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println(horizontalLine);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
