import java.util.Scanner;

public class Hien {
    private static final String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Hien");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(input);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private static void addTask(String task) {
        if (taskCount < 100) {
            tasks[taskCount++] = task;
            System.out.println(" added: " + task);
        } else {
            System.out.println(" Task list is full. Cannot add more tasks.");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println(" The task list is empty.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
    }
}