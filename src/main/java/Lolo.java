import java.util.ArrayList;
import java.util.Scanner;

public class Lolo {

    private static final String FILE_PATH = "./data/lolo.txt";
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks;
    private static Storage storage;

    public static void main(String[] args) {
        storage = new Storage(FILE_PATH);
        try {
            tasks = storage.load();
        } catch (LoloException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Lolo, your friendly task manager. 😊");
        System.out.println("What do you want to do today?\n");

        String userCommand;

        do {
            System.out.print("You: ");
            userCommand = scanner.nextLine();

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
            } else if (userCommand.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(userCommand.split(" ")[1]);
                deleteTask(taskNumber);
            } else {
                System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        } while (!userCommand.equalsIgnoreCase("bye"));

        System.out.println("Lolo: Bye. Hope to see you again soon! 👋");

        scanner.close();
    }

    public static void addTask(Task task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + tasks.size() + " task(s) in the list.");
            saveTasks();
        } else {
            System.out.println("    Task list is full! Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskNumber - 1));
            saveTasks();
        } else {
            System.out.println("    OOPS!!! The task number is invalid.");
        }
    }

    public static void markTaskAsNotDone(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNumber - 1));
            saveTasks();
        } else {
            System.out.println("    OOPS!!! The task number is invalid.");
        }
    }

    public static void deleteTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + removedTask);
            System.out.println("    Now you have " + tasks.size() + " task(s) in the list.");
            saveTasks();
        } else {
            System.out.println("    OOPS!!! The task number is invalid.");
        }
    }

    private static void saveTasks() {
        try {
            storage.save(tasks);
        } catch (LoloException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}


