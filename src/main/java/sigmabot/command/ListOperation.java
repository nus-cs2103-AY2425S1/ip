package sigmabot.command;

import sigmabot.task.Deadline;
import sigmabot.task.Event;
import sigmabot.task.Task;
import sigmabot.task.Todo;
import sigmabot.util.ListMapWriter;
import sigmabot.util.ListReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The ListOperation class provides functionality for managing a single task list.
 * It includes methods for creating new tasks, querying the list, marking tasks as done, and deleting tasks.
 */
public class ListOperation extends Command {
    private ListReader reader = new ListReader();
    private ListMapWriter writer = new ListMapWriter();
    private String filePath = "../../data/tasks.txt";  // Path to the single file for storing tasks
    private Map<String, Task> taskList = new HashMap<>();  // Single task list

    /**
     * Executes the main command loop, providing options to create, query, mark, or delete tasks.
     * Also loads existing tasks from the file at startup.
     */
    @Override
    public void execute(Scanner sc) {
        // Read tasks from the file to populate the taskList
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No tasks found. You can create a new task.");
        } else {
            taskList.putAll(reader.readTasksFromFile(filePath));
            System.out.println("Tasks loaded from file: " + filePath);
        }

        // Main command loop
        while (true) {
            System.out.println("Enter operation command (create, query, mark, delete, exit): ");
            String command = sc.nextLine();
            switch (command) {
            case "create":
                createNewTask(sc);
                break;
            case "query":
                queryTasks();
                break;
            case "mark":
                handleMarkDone(sc);
                break;
            case "delete":
                handleDeleteTask(sc);
                break;
            case "exit":
                System.out.println("Exiting List Operations...");
                return; // Exit the loop and end the method
            default:
                System.out.println("Invalid command. Please enter 'create', 'query', 'mark', 'delete', or 'exit'.");
            }
        }
    }

    /**
     * Creates a new task and adds it to the taskList.
     * Prompts the user to enter task details until they choose to exit.
     */
    public void createNewTask(Scanner sc) {
        String input;
        while (true) {
            System.out.println("Enter task type (todo, deadline, event) or '/exit' to finish: ");
            input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                break; // Exit the loop if the user types '/exit'
            }
            Task task = null;
            switch (input.toLowerCase()) {
            case "todo" -> task = Todo.createTodo(sc);
            case "deadline" -> task = Deadline.createDeadline(sc);
            case "event" -> task = Event.createEvent(sc);
            default -> {
                System.out.println("Invalid task type. Please enter 'todo', 'deadline', or 'event'.");
                continue;
            }
            }
            if (task != null) {
                taskList.put(task.getName(), task);
            }
        }
        System.out.println("New tasks added.");
        // Write the new task list to the file
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Queries the existing tasks and displays them to the user.
     * If no tasks are found, prompts the user to create a new one.
     */
    public void queryTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available. You can create a new task.");
        } else {
            System.out.println("Current tasks:");
            for (Task task : taskList.values()) {
                System.out.println(task.toString());
            }
        }
    }

    /**
     * Marks a specified task as done within the task list.
     * If the task is found, it is marked as done and the user is notified.
     * Otherwise, informs the user that the task was not found.
     */
    public void markDone(Scanner sc) {
        System.out.println("Enter the name of the task to mark as done: ");
        String taskName = sc.nextLine().trim();
        Task task = taskList.get(taskName);
        if (task != null) {
            task.markDone();
            System.out.println("Task '" + taskName + "' marked as done.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        // Rewrite the modified list to the file
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Deletes a specified task from the task list.
     * If the task is found and deleted, the user is notified.
     * Otherwise, informs the user that the task was not found.
     */
    public void deleteTask(Scanner sc) {
        System.out.println("Enter the name of the task to delete: ");
        String taskName = sc.nextLine().trim();

        if (taskList.remove(taskName) != null) {
            System.out.println("Task '" + taskName + "' deleted.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }

        // Rewrite the modified list to the file
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Handles the process of marking a task as done.
     * Prompts the user to select the task to be marked as done.
     */
    private void handleMarkDone(Scanner sc) {
        markDone(sc);
    }

    /**
     * Handles the process of deleting a task.
     * Prompts the user to select the task to be deleted.
     */
    private void handleDeleteTask(Scanner sc) {
        deleteTask(sc);
    }
}
