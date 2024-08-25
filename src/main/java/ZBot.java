import java.util.Scanner;
import java.util.ArrayList;

public class ZBot {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String SAVE_PATH = "../../../data/tasks.txt";

    public static void main(String[] args) {
        Storage storage = new Storage(SAVE_PATH);
        storage.createFileIfNotExists();

        greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            processInput(input);
            input = sc.nextLine();
        }

        sc.close();
        exit();
    }

    public static void greet() {
        System.out.println("Hello! I'm ZBot!");
        System.out.println("What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void processInput(String input) {
        if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark")) {
            markTask(input);
        } else if (input.startsWith("unmark")) {
            unmarkTask(input);
        } else if (input.startsWith("todo") ||
                input.startsWith("deadline") ||
                input.startsWith("event")) {
            storeTask(input);
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else {
            System.out.println("Invalid command.\n");
        }
    }

    public static void storeTask(String input) {
        Task task;
        String[] inputParts = input.split(" ", 2);

        try {
            if (inputParts[0].equals("deadline")) {
                String[] deadlineParts = inputParts[1].split(" /by ", 2);
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
            } else if (inputParts[0].equals("event")) {
                String[] eventParts = inputParts[1].split(" /from ", 2);
                String[] period = eventParts[1].split(" /to ", 2);
                task = new Event(eventParts[0], period[0], period[1]);
            } else {
                task = new ToDo(inputParts[1]);
            }

            tasks.add(task);

            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println(String.format("Now you have %d tasks in the list.\n", tasks.size()));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task format!\n");
        }
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        System.out.println();
    }

    public static void markTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber - 1));
            System.out.println();
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }

    public static void unmarkTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber - 1));
            System.out.println();
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }

    public static void deleteTask(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            System.out.println(String.format("Now you have %d tasks in the list.\n", tasks.size()));
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }
}
