import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Buddy {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Map<String, TaskType> taskTypeCommandsMap = Map.of(
            "todo", TaskType.TODO,
            "deadline", TaskType.DEADLINE,
            "event", TaskType.EVENT
    );

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! I'm Buddy");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Check if user input is empty
            if (input.isEmpty()) {
                System.out.println("Please enter a valid input!");
                continue;
            }

            // Exit if user types "bye"
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark")) {
                markTaskAsNotDone(input);
            } else if (taskTypeCommandsMap.containsKey(input.split(" ", 2)[0])) {
                // Store input text as new task
                addTask(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("Please enter a valid command!");
            }
        }

        scanner.close();
    }

    /**
     * Lists all tasks in the task list.
     */
    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks in your list!");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Adds a new task to the task list.
     *
     * @param input User input.
     */
    private static void addTask(String input) {
        String[] parts = input.split(" ", 2);

        if (parts.length < 2) {
            System.out.println("Please enter a valid task description!");
            return;
        }

        String type = parts[0];
        String description = parts[1];
        TaskType taskType = taskTypeCommandsMap.get(type);

        if (taskType == null) {
            System.out.println("Please enter a valid task type!");
            return;
        }

        switch (taskType) {
            case TODO:
                tasks.add(new ToDo(description));
                break;
            case DEADLINE:
                String[] deadlineParts = description.split(" /by ");

                if (deadlineParts.length < 2) {
                    System.out.println("Please enter a valid deadline description!");
                    return;
                }

                tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                break;
            case EVENT:
                String[] eventParts = description.split(" /from | /to ");

                if (eventParts.length < 3) {
                    System.out.println("Please enter a valid event description!");
                    return;
                }

                tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                break;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done.
     *
     * @param input User input.
     */
    private static void markTaskAsDone(String input) {
        // Check if input task number is a valid integer
        try {
            Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
            return;
        }

        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        int taskIndex = taskNumber - 1;

        try {
            tasks.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
            return;
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    /**
     * Marks a task as not done.
     *
     * @param input User input.
     */
    private static void markTaskAsNotDone(String input) {
        // Check if input task number is a valid integer
        try {
            Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
            return;
        }

        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        int taskIndex = taskNumber - 1;

        try {
            tasks.get(taskIndex).markAsNotDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
            return;
        }

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input User input.
     */
    private static void deleteTask(String input) {
        // Check if input task number is a valid integer
        try {
            Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
            return;
        }

        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        int taskIndex = taskNumber - 1;

        try {
            Task task = tasks.remove(taskIndex);

            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
        }
    }
}
