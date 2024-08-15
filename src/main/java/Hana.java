import java.util.Scanner;

public class Hana {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static String line = "___________________________________________";
    private static String name = "Hana";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        //greet
        System.out.println(line);
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5).trim());
                markTask(taskNumber, true);
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim());
                markTask(taskNumber, false);
            } else if (input.startsWith("todo ")) {
                addTask(new ToDo(input.substring(5).trim()));
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                addTask(new Deadline(parts[0].trim(), parts[1].trim()));
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(line);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println(line);
        } else {
            System.out.println("Task list is full!");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println(line);
            System.out.println("No tasks added yet.");
            System.out.println(line);
        } else {
            System.out.println(line);
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
            System.out.println(line);
        }
    }

    private static void markTask(int taskNumber, boolean isDone) {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].setDone(isDone);
            System.out.println(line);
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + tasks[taskNumber - 1]);
            System.out.println(line);
        } else {
            System.out.println("Invalid task number!");
        }
    }
}
