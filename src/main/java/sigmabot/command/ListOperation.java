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
 * The {@code ListOperation} class provides functionality for managing a task list.
 * It includes methods for creating, querying, finding, marking, unmarking, and deleting tasks.
 * The tasks are read from and written to a file specified by the {@code filePath}.
 */
public class ListOperation extends Command {
    private ListReader reader = new ListReader();
    private ListMapWriter writer = new ListMapWriter();
    private String filePath = "/Users/wxy/Desktop/ip/src/main/data/tasks.txt";
    private Map<String, Task> taskList = new HashMap<>();

    /**
     * Executes the main command loop, providing options to create, query, mark, unmark, or delete tasks.
     * Also loads existing tasks from the file at startup.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    @Override
    public void execute(Scanner sc) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No tasks file found. A new one will be created upon saving tasks.");
        } else if (file.length() == 0) {
            System.out.println("Tasks file is empty. You can create a new task.");
        } else {
            try {
                taskList.putAll(reader.readTasksFromFile(filePath));
                if (taskList.isEmpty()) {
                    System.out.println("No tasks found. The file might be corrupted or empty.");
                } else {
                    System.out.println("Tasks loaded from file: " + filePath);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while reading the tasks file: " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("Enter operation command (create, query, find, add, mark, unmark, delete, exit): ");
            if (!sc.hasNextLine()) {
                break;
            }
            String command = sc.nextLine();
            switch (command) {
            case "create":
                createNewTask(sc);
                break;
            case "query":
                queryTasks();
                break;
            case "find":
                findTasks(sc);
                break;
            case "add":
                addTask(sc);
                break;
            case "mark":
                handleMarkDone(sc);
                break;
            case "unmark":
                handleMarkUndone(sc);
                break;
            case "delete":
                handleDeleteTask(sc);
                break;
            case "exit":
                System.out.println("Exiting List Operations...");
                return;
            default:
                System.out.println("Invalid command. Please enter 'create', 'query', 'find', 'add', 'mark', 'unmark', 'delete', or 'exit'.");
            }
        }
    }

    /**
     * Creates a new task based on user input and adds it to the task list.
     * The task list is then saved to the file.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    public void createNewTask(Scanner sc) {
        while (true) {
            System.out.println("Enter task type (todo, deadline, event) or '/exit' to finish: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                break;
            }
            Task task = null;
            switch (input.toLowerCase()) {
            case "todo":
                task = Todo.createTodo(sc);
                break;
            case "deadline":
                task = Deadline.createDeadline(sc);
                break;
            case "event":
                task = Event.createEvent(sc);
                break;
            default:
                System.out.println("Invalid task type. Please enter 'todo', 'deadline', or 'event'.");
                continue;
            }
            if (task != null) {
                taskList.put(task.getName(), task);
            }
        }
        System.out.println("New tasks added.");
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Displays all tasks in the current task list.
     * If the task list is empty, prompts the user to create a new task.
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

     * Finds and displays tasks that contain a specific substring in their names.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    public void findTasks(Scanner sc) {
        System.out.println("Enter the substring to search for in task names: ");
        String substring = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Task task : taskList.values()) {
            if (task.getName().toLowerCase().contains(substring)) {
                System.out.println(task.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found containing the substring: " + substring);
        }
    }

    /**
     * Adds a new task to the existing task list based on user input.
     * The task list is then saved to the file.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    public void addTask(Scanner sc) {
        System.out.println("Enter task type to add (todo, deadline, event) or '/exit' to finish: ");
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                break;
            }
            Task task = null;
            switch (input.toLowerCase()) {
            case "todo":
                task = Todo.createTodo(sc);
                break;
            case "deadline":
                task = Deadline.createDeadline(sc);
                break;
            case "event":
                task = Event.createEvent(sc);
                break;
            default:
                System.out.println("Invalid task type. Please enter 'todo', 'deadline', 'event', or '/exit'.");
                continue;
            }
            if (task != null) {
                taskList.put(task.getName(), task);
                System.out.println("Task added: " + task.getName());
            }
            System.out.println("Enter next task type to add or '/exit' to finish: ");
        }
        System.out.println("Tasks added.");
        writer.writeMapToFile(taskList, filePath);
    }

   /**
     * Marks a specified task as done based on user input.
     * The task list is then saved to the file.
     *
     * @param sc The {@code Scanner} object for reading user input.
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
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Marks a specified task as undone based on user input.
     * The task list is then saved to the file.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    public void markUndone(Scanner sc) {
        System.out.println("Enter the name of the task to mark as undone: ");
        String taskName = sc.nextLine().trim();
        Task task = taskList.get(taskName);
        if (task != null) {
            task.markUndone();
            System.out.println("Task '" + taskName + "' marked as undone.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Deletes a specified task based on user input.
     * The task list is then saved to the file.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    public void deleteTask(Scanner sc) {
        System.out.println("Enter the name of the task to delete: ");
        String taskName = sc.nextLine().trim();
        if (taskList.remove(taskName) != null) {
            System.out.println("Task '" + taskName + "' deleted.");
        } else {
            System.out.println("Task '" + taskName + "' not found.");
        }
        writer.writeMapToFile(taskList, filePath);
    }

    /**
     * Handles the process of marking a task as done.
     * Prompts the user to select the task to be marked as done.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    private void handleMarkDone(Scanner sc) {
        markDone(sc);
    }

    /**
     * Handles the process of marking a task as undone.
     * Prompts the user to select the task to be marked as undone.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    private void handleMarkUndone(Scanner sc) {
        markUndone(sc);
    }

    /**
     * Handles the process of deleting a task.
     * Prompts the user to select the task to be deleted.
     *
     * @param sc The {@code Scanner} object for reading user input.
     */
    private void handleDeleteTask(Scanner sc) {
        deleteTask(sc);
    }
}
