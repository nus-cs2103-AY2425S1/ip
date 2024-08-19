import java.util.Scanner;

public class Lolo {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?");
        System.out.println("____________________________________________________________\n");

        String userCommand;

        do {
            System.out.print("You: ");
            userCommand = scanner.nextLine();

            System.out.println("   ____________________________________________________________");

            if (userCommand.equalsIgnoreCase("bye")) {
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                listTasks();
            } else if (userCommand.startsWith("todo ")) {
                addTask(new ToDo(userCommand.substring(5)));
            } else if (userCommand.startsWith("deadline ")) {
                String[] parts = userCommand.split(" /by ");
                addTask(new Deadline(parts[0].substring(9), parts[1]));
            } else if (userCommand.startsWith("event ")) {
                String[] parts = userCommand.split(" /from ");
                String[] fromTo = parts[1].split(" /to ");
                addTask(new Event(parts[0].substring(6), fromTo[0], fromTo[1]));
            } else if (userCommand.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userCommand.split(" ")[1]);
                markTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userCommand.split(" ")[1]);
                markTaskAsNotDone(taskNumber);
            } else {
                System.out.println("    Invalid command.");
            }

            System.out.println("   ____________________________________________________________\n");

        } while (!userCommand.equalsIgnoreCase("bye"));

        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");
        System.out.println("____________________________________________________________");

        scanner.close();
    }

    public static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + taskCount + " task(s) in the list.");
        } else {
            System.out.println("    Task list is full! Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println("    " + (i + 1) + "." + tasks[i]);
            }
        }
    }

    // Function to mark a task as done
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Function to mark a task as not done
    public static void markTaskAsNotDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
