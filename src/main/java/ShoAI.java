import java.util.Scanner;

public class ShoAI {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm ShoAI");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                markTaskAsDone(taskNumber);
            } else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                unmarkTaskAsDone(taskNumber);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String taskDescription) {
        if (taskCount < 100) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + taskDescription);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Sorry, I can't store any more tasks.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void displayTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(int taskNumber) {
        if (taskNumber < taskCount && taskNumber >= 0) {
            tasks[taskNumber].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskNumber]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Task number does not exist.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber < taskCount && taskNumber >= 0) {
            tasks[taskNumber].unmarkAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskNumber]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Task number does not exist.");
            System.out.println("____________________________________________________________");
        }
    }
}
