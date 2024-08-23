import java.util.Scanner;

// Basic list with option to mark/unmark individual tasks
public class Pebble {
    public static void main(String[] args) {
        Task[] taskList = new Task[100]; // Array to store Task objects
        int totalTasks = 0;

        Scanner scanner = new Scanner(System.in);

        // Introduction text
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();

            // Exit if "bye" is typed
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            }

            // List all tasks
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < totalTasks; i++) {
                    System.out.println("    " + (i + 1) + "." + "[" +  taskList[i].getStatusIcon() + "] " + taskList[i].toString());
                }
                System.out.println("    ____________________________________________________________");
                continue;
            }

            // Mark task as done
            if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < totalTasks) {
                    taskList[taskNumber].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + "[" + taskList[taskNumber].getStatusIcon() + "] "
                            + taskList[taskNumber]);
                    System.out.println("    ____________________________________________________________");
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Invalid task number.");
                    System.out.println("    ____________________________________________________________");
                }
                continue;
            }

            // Unmark task as not done
            if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < totalTasks) {
                    taskList[taskNumber].unmarkAsNotDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + "[" + taskList[taskNumber].getStatusIcon() + "] "
                            + taskList[taskNumber]);
                    System.out.println("    ____________________________________________________________");
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Invalid task number.");
                    System.out.println("    ____________________________________________________________");
                }
                continue;
            }

            // Add a new task
            Task newTask = new Task(input);
            taskList[totalTasks] = newTask;
            totalTasks++;

            System.out.println("    ____________________________________________________________");
            System.out.println("    Added: " + newTask);
            System.out.println("    ____________________________________________________________");
        }

        scanner.close();
    }
}
