import java.util.Scanner;

public class Ollie {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    /**
     * Prints a message within a decorative border.
     *
     * @param message the message to print
     */
    public static void messageWrapper(String message) {
        String border = "*-".repeat(30);
        System.out.println(border);
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println(border);
    }

    /**
     * Greets the user with a welcome message.
     */
    public static void greeting() {
        messageWrapper("Hello there! I'm OLLIE :) \nWhat can I do for you?");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public static void exit() {
        messageWrapper("Bye. Have a great day. Hope to see you again soon! :) ");
    }

    /**
     * Prints a message indicating that a command has been added.
     *
     * @param message the message to print
     */
    public static void commandWrapper(String message) {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("added: " + message);
        System.out.println(border);
    }

    /**
     * Handles the user's input command.
     *
     * @param userCommand the command entered by the user
     */
    public static void echo(String userCommand) {
        if (userCommand.equals("bye")) {
            exit();
        } else if (userCommand.equals("list")) {
            listTasks();
        } else if (userCommand.startsWith("mark ")) {
            markTaskAsDone(userCommand);
        } else if (userCommand.startsWith("unmark ")) {
            unmarkTaskAsDone(userCommand);
        } else {
            addTask(userCommand);
            commandWrapper(userCommand);
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param taskDescription the description of the task to add
     */
    public static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
        } else {
            System.out.println("There are 100 tasks already. The task list is full. Cannot add more tasks. :(");
        }
    }

    /**
     * Lists all the tasks along with their statuses.
     */
    public static void listTasks() {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("Here are the tasks in your list :) :");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(border);
    }

    /**
     * Marks a specific task as done based on the user's input.
     *
     * @param command the command string containing the task number to mark as done
     */
    public static void markTaskAsDone(String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                tasks[taskNumber].markAsDone(true);
                messageWrapper("Nice! I've marked this task as done :) :\n  " + tasks[taskNumber]);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        }
    }

    /**
     * Unmarks a specific task as not done based on the user's input.
     *
     * @param command the command string containing the task number to unmark as done
     */
    public static void unmarkTaskAsDone(String command) {
        try {
            int taskNumber = Integer.parseInt(command.substring(7)) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                tasks[taskNumber].markAsDone(false);
                messageWrapper("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        }
    }

    /**
     * The main method that runs the Ollie task manager.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        greeting();
        System.out.println();

        String command;
        Scanner input = new Scanner(System.in);

        do {
            command = input.nextLine();
            echo(command);
        } while (!command.equals("bye"));

        input.close();
    }
}