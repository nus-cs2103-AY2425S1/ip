import java.util.Scanner;

public class Ollie {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void messageWrapper(String message) {
        String border = "*-".repeat(30);
        System.out.println(border);
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println(border);
    }

    public static void greeting() {
        messageWrapper("Hello there! I'm OLLIE :) \nWhat can I do for you?");
    }

    public static void exit() {
        messageWrapper("Bye. Have a great day. Hope to see you again soon! :) ");
    }

    public static void commandWrapper(String message) {
        String border = "--".repeat(30);
        System.out.println(border);
        System.out.println("added: " + message);
        System.out.println(border);
    }

    public static void echo(String userCommand) {
        if (userCommand.equals("bye")) {
            exit();
        } else if (userCommand.equals("list")) {
            listTasks();
        } else {
            addTask(userCommand);
            commandWrapper(userCommand);
        }
    }

    public static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
        } else {
            System.out.println("There are 100 tasks already. The task list is full. Cannot add more tasks. :(");
        }
    }

    public static void listTasks() {
        String border = "--".repeat(30);
        System.out.println(border);
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(border);
    }

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