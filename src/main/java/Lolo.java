import java.util.Scanner;

public class Lolo {

    private static final int MAX_TASKS = 100;  // Maximum number of tasks
    private static Task[] tasks = new Task[MAX_TASKS];  // Array to store tasks
    private static int taskCount = 0;  // Counter for the number of tasks added

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Greet the user and introduce the chatbot
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?");
        System.out.println("____________________________________________________________");

        String userCommand;

        // Start a loop that continues until the user types "bye"
        do {
            // Prompt the user for input/command
            System.out.print("You: ");
            userCommand = scanner.nextLine();

            System.out.println("____________________________________________________________");

            if (userCommand.equalsIgnoreCase("bye")) {
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                listTasks();
            } else if (userCommand.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userCommand.split(" ")[1]);
                markTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userCommand.split(" ")[1]);
                markTaskAsNotDone(taskNumber);
            } else {
                addTask(userCommand);
            }
            System.out.println("____________________________________________________________\n");

        } while (!userCommand.equalsIgnoreCase("bye")); // Loop ends when user types "bye
        // Say goodbye to the user before exiting
        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");
        System.out.println("____________________________________________________________");

        // Close the scanner
        scanner.close();
    }

    // Function to add a task to the list
    public static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("Lolo: Added: " + taskDescription);
        } else {
            System.out.println("Lolo: Task list is full! You cannot add more tasks:(");
        }
    }

    // Function to list all the tasks
    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("Lolo: You have not added anny tasks yet.");
        } else {
            System.out.println("Lolo: Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        }
    }

    // Function to mark a task as done
    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsDone();
            System.out.println("Lolo: Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            System.out.println("Lolo: Invalid task number.:(");
        }
    }

    // Function to mark a task as not done
    public static void markTaskAsNotDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= taskCount) {
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("Lolo: OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber - 1]);
        } else {
            System.out.println("Lolo: Invalid task number.:(");
        }
    }
}